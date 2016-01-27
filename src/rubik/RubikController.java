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
import java.text.DateFormat;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneOffset;
import java.util.Calendar;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.Timer;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextInputDialog;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import static rubik.RubikMain.actualDB;
import static rubik.RubikMain.rubik;


public class RubikController extends RubikMain implements Initializable {
     
    @FXML
    private Label lTime;
    @FXML
    private Label lSolved;
    @FXML
    private Label lMov;
    @FXML
    private Button solve;
    
    @FXML 
    private Button B;
    @FXML 
    private Button Bi;
    @FXML 
    private Button D;
    @FXML 
    private Button Di;
    @FXML 
    private Button E;
    @FXML 
    private Button Ei;
    @FXML 
    private Button R;
    @FXML 
    private Button Ri;
    @FXML 
    private Button X;
    @FXML 
    private Button Xi;
    @FXML 
    private Button L;
    @FXML 
    private Button Li;
    @FXML 
    private Button M;
    @FXML 
    private Button Mi;
    @FXML 
    private Button S;
    @FXML 
    private Button Si;
    @FXML 
    private Button U;
    @FXML 
    private Button Ui;
    @FXML 
    private Button F;
    @FXML 
    private Button Fi;
    
    private AnchorPane puntajeLayout;
    
       
    
    @FXML
    private void reto() throws SQLException{
        
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Modo reto");
        alert.setContentText("¿Deseas continuar? Recuerda que ahora medirás el tiempo que te toma y la cantidad de movimiento que haces.");
        alert.setHeaderText("El cubo Rubik se va a reiniciar");
        
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK){
            rubik.doReset();  
        ScrambleCube();
        
        long nowEpoch = System.currentTimeMillis()/1000;
         
        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(1), (ActionEvent event) -> {
            lTime.setText((System.currentTimeMillis()/1000-nowEpoch)+" s");  
        })); 
        
        lMov.textProperty().bind(rubik.getCount().asString());
        
        timeline.setCycleCount(Animation.INDEFINITE);  
        timeline.play();
    
        
        lTime.setVisible(true);
        lMov.setVisible(true);
        
        if(rubik.isSolved().getValue()){ 
            
            timeline.stop();
            long last = System.currentTimeMillis()/1000;
            int duration = (int) (last - nowEpoch);
            
            TextInputDialog dialog = new TextInputDialog("nombre");
            dialog.setTitle("!Felicidades!");
            dialog.setHeaderText("¡Has resuelto el cubo Rubik!");
            dialog.setContentText("Pon tu nombre:");
            
            Optional<String> input = dialog.showAndWait();
            if (input.isPresent()){
                Record rec;
                rec = new Record(null, input.get(), rubik.getCount().getValue(), duration);
                Java2MySql.insertData(actualDB, rec);
            }           
        }      
        }
        
    }
    
    @FXML
    private void Scramble(){
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Revolver cubo");
        alert.setContentText("¿Deseas continuar?");
        alert.setHeaderText("El cubo Rubik se va a reiniciar y luego se va revolver");
        
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK){
            moves.getMoves().clear();
            rubik.doReset();
            ScrambleCube();
        }
        
    }

    @FXML
    private void Reset(){
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Reinicio");
        alert.setContentText("¿Deseas continuar?");
        alert.setHeaderText("El cubo Rubik se va a reiniciar");
        
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK){
            moves.getMoves().clear();
            rubik.doReset();
        }
        
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
    private void rotateR(){
        rotateFace("R");    
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
    private void solve(){
        
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    
}
