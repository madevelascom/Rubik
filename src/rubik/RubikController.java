/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rubik;

import com.jpl.games.model.Rubik;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import java.net.URL;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import rubik.RubikMain;



/**
 *
 * @author Administrator
 */
public class RubikController extends RubikMain implements Initializable {
       
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
    private void customexit(){
         System.exit(0);
    }
    @FXML
    private void pos() throws IOException {
        Stage stage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("Puntaje.fxml"));   
        
        Image applicationIcon = new Image(getClass().getResourceAsStream("rubik_s_cube.png"));
        stage.getIcons().add(applicationIcon);
        stage.setTitle("Tabla de posiciones");
        Scene scene = new Scene(root);
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
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    
}
