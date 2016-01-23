/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rubik;

import com.jpl.games.model.Move;
import com.mvm.games.records.Record;
import com.mvm.sql.Java2MySql;
import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneOffset;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextInputDialog;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import static rubik.RubikMain.actualDB;


public class RubikController extends RubikMain implements Initializable {
     
    @FXML
    private Label lTime = new Label(timer.toString());
    @FXML
    private Label lSolved = new Label("Solved");
    @FXML
    private Button solve;
   
    private AnchorPane puntajeLayout;
    
    @FXML
    private void reto() throws SQLException{
        
                
        rubik.doReset();
        ScrambleCube();
        
        LocalDateTime init = LocalDateTime.now();
        
        if(rubik.isSolved().getValue()){ 
            LocalDateTime end = LocalDateTime.now();
            int tiempo = (int) (end.toEpochSecond(ZoneOffset.UTC) - init.toEpochSecond(ZoneOffset.UTC));
            TextInputDialog dialog = new TextInputDialog("nombre");
            dialog.setTitle("!Felicidades!");
            dialog.setHeaderText("¡Has resuelto el cubo Rubik!");
            dialog.setContentText("Pon tu nombre:");
            
            Optional<String> result = dialog.showAndWait();
            if (result.isPresent()){
                Record rec;
                
                rec = new Record(null, result.get(), rubik.getCount().getValue(), tiempo);
                Java2MySql.insertData(actualDB, rec);
            }           
        }      
    }
    
    @FXML
    private void Scramble(){
        rubik.doReset();
        ScrambleCube();
    }

    @FXML
    private void Reset(){
        moves.getMoves().clear();
        rubik.doReset();
    }
    
    @FXML
    private void replay(){
        timer.stop();
        ReplayCube();
    }
    
    @FXML
    private void pos() throws IOException {
        
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("Puntaje.fxml"));
        puntajeLayout = (AnchorPane) loader.load();
        
        Stage stage = new Stage();
        //Parent root = FXMLLoader.load(getClass().getResource("Puntaje.fxml"));   
        
        Image applicationIcon = new Image(getClass().getResourceAsStream("rubik_s_cube.png"));
        stage.getIcons().add(applicationIcon);
        stage.setTitle("Tabla de posiciones");
        
        Scene scene = new Scene(puntajeLayout);
        stage.setScene(scene);
        stage.show();
    }
    
    @FXML
    private void acerca() throws IOException {
        Stage stage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("Acerca.fxml")); 
        
        Image applicationIcon = new Image(getClass().getResourceAsStream("rubik_s_cube.png"));
        stage.getIcons().add(applicationIcon);
        stage.setTitle("Acerca de");
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    
    @FXML
    private void comojugar() throws IOException, URISyntaxException {
        Desktop.getDesktop().browse(new URI("http://www.rubikaz.com/paso1.php"));
    }
    
    @FXML
    private void timer(){
        lTime.textProperty().bind(clock);
        timer=new Timeline(new KeyFrame(Duration.ZERO, e->{
            clock.set(LocalTime.now().minusNanos(time.toNanoOfDay()).format(fmt));
        }),new KeyFrame(Duration.seconds(1)));
        
         timer.setCycleCount(Animation.INDEFINITE);
        rubik.isSolved().addListener((ov,b,b1)->{
            if(b1){
                lSolved.setVisible(true);
                timer.stop();
                moves.setTimePlay(LocalTime.now().minusNanos(time.toNanoOfDay()).toNanoOfDay());
                System.out.println(moves);
            } else {
                lSolved.setVisible(false);
            }
        });
        
    }
    
    @FXML
    private void solve(){
        
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    
}
