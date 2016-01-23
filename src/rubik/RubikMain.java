/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rubik;

import com.jpl.games.model.Move;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import com.jpl.games.model.Moves;
import com.jpl.games.model.Rubik;
import com.mvm.games.records.Record;
import com.mvm.sql.Java2MySql;
import javafx.scene.image.Image;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.function.Consumer;
import java.util.function.Predicate;
import javafx.animation.Timeline;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ChangeListener;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.layout.BorderPane;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ToolBar;
import javafx.scene.input.MouseEvent;
import javax.naming.NamingException;

/**
 *
 * @author Administrator
 */
public class RubikMain extends Application {
    
    private Stage primaryStage;
    private BorderPane rootLayout;
    private BorderPane RubikInterface;
    private Scene scene;
    
    public static Rubik rubik=new Rubik();
    public static Moves moves=new Moves();
    
    public LocalTime time=LocalTime.now();
    public static Timeline timer = new Timeline();
    
    public final StringProperty clock = new SimpleStringProperty("00:00:00");
    public final DateTimeFormatter fmt = DateTimeFormatter.ofPattern("HH:mm:ss").withZone(ZoneId.systemDefault());
    
    public static ObservableList<Record> recordData = FXCollections.observableArrayList();
    public static Java2MySql instanciaDB = new Java2MySql();

    @Override
    public void start(Stage primaryStage) throws Exception {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("Cubo Rubik");     
        
        Image applicationIcon = new Image(getClass().getResourceAsStream("rubik_s_cube.png"));
        this.primaryStage.getIcons().add(applicationIcon);
        initRootLayout();
        showRubikInterface();  
        ChangeListener<Number> clockLis=(ov,l,l1)->clock.set(LocalTime.ofNanoOfDay(l1.longValue()).format(fmt));

    }
  
    public void initRootLayout() {
        try {
            // Load root layout from fxml file.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(RubikMain.class.getResource("RootLayout.fxml"));
            rootLayout = (BorderPane) loader.load();

            // Show the scene containing the root layout.
            scene = new Scene(rootLayout);           
            
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void showRubikInterface() {
        try {
            // Load  overview.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(RubikMain.class.getResource("RubikInterface.fxml"));
            RubikInterface = (BorderPane) loader.load();
            
            //Load 3d model and buttonbar
            rootLayout.setCenter(RubikInterface);
            RubikInterface.setCenter(rubik.getSubScene());
            
            RubikInterface.getChildren().stream().filter(withMoveButtons())
                    .forEach(n->{
                        Button b=(Button)n;
                        b.setOnAction(e->rotateFace(b.getText()));
                        b.hoverProperty().addListener((ov,b0,b1)->updateArrow(b.getText(),b1));
                    });
            
            ChangeListener<Number> clockLis=(ov,l,l1)->clock.set(LocalTime.ofNanoOfDay(l1.longValue()).format(fmt));

            rubik.isOnReplaying().addListener((ov,b,b1)->{
            if(b&&!b1){
                rubik.getTimestamp().removeListener(clockLis);
                if(!rubik.isSolved().get()){
                    timer.play();
                }
            }
        });
            
            rubik.isOnRotation().addListener((b0,b1,b2)->{
                if(b2){
                // store the button hovered 
                RubikInterface.getChildren().stream().filter(withToolbars())
                    .forEach(tb->{
                        ((ToolBar)tb).getItems().stream().filter(withMoveButtons().and(isButtonHovered()))
                            .findFirst();
                    });
            } 
            });
            
            rubik.getLastRotation().addListener((ov,v,v1)->{
            if(!rubik.isOnReplaying().get() && !v1.isEmpty()){
                moves.addMove(new Move(v1, LocalTime.now().minusNanos(time.toNanoOfDay()).toNanoOfDay()));
            }
            });
            
            
            scene.addEventHandler(MouseEvent.ANY, rubik.eventHandler);
            scene.cursorProperty().bind(rubik.getCursor());
  
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public void rotateFace(final String btRot){
        RubikInterface.getChildren().stream()
            .filter(withToolbars())
            .forEach(tb->{
                ((ToolBar)tb).getItems().stream()
                    .filter(withMoveButtons().and(withButtonTextName(btRot)))
                    .findFirst().ifPresent(n->rubik.isHoveredOnClick().set(((Button)n).isHover()));
            });
        rubik.rotateFace(btRot);
    }
    
    public void ScrambleCube(){
        rubik.doScramble();
        rubik.isOnScrambling().addListener((ov,v,v1)->{
            if(v && !v1){
                System.out.println("Revuelto!");
                moves=new Moves();
            }
        });
    }
    
    public void ReplayCube(){
        rubik.doReplay(moves.getMoves());
        rubik.isOnReplaying().addListener((ov,v,v1)->{
            if(v && !v1){
                System.out.println("replayed!");
                RubikInterface.getChildren().stream().filter(withToolbars()).forEach(setDisable(false));
            }
        });
    }
    
    public ObservableList<Record> getRecordData(){
        return recordData;
    }
    private void updateArrow(String face, boolean hover){
        rubik.updateArrow(face,hover);
    }
  
    private static Predicate<Node> withToolbars(){
        return n -> (n instanceof ToolBar);
    }
    private static Predicate<Node> withMoveButtons(){
        return n -> (n instanceof Button) && ((Button)n).getText().length()<=2;
    }
    private static Predicate<Node> withButtonTextName(String text){
        return n -> ((Button)n).getText().equals(text);
    }
    private static Predicate<Node> isButtonHovered(){
        return n -> ((Button)n).isHover();
    }
    private static Consumer<Node> setDisable(boolean disable){
        return n -> n.setDisable(disable);
    }
    public Stage getPrimaryStage() {
        return primaryStage;
    }
    
    public Rubik getRubik(){
        return rubik;
    }
    public static void main(String[] args) throws NamingException, SQLException {
        
        Connection actualDB = instanciaDB.openConnection();
        
        
        if(actualDB != null){
            System.out.print("Successful");
            recordData = Java2MySql.loadData(actualDB);
         
        }
        
        launch(args);
    }
    
}
