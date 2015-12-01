/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rubik;

import com.jpl.games.model.Moves;
import com.jpl.games.model.Rubik;
import java.io.IOException;
import java.net.URL;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import javafx.animation.Timeline;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;


/**
 *
 * @author Administrator
 */
public class RubikController implements Initializable {
    
    @FXML
    private Label label;
    @FXML
    private Rubik rubik;
    @FXML
    private LocalTime time=LocalTime.now();
    @FXML
    private Timeline timer;
    @FXML
    private final StringProperty clock = new SimpleStringProperty("00:00:00");
    @FXML
    private final DateTimeFormatter fmt = DateTimeFormatter.ofPattern("HH:mm:ss").withZone(ZoneId.systemDefault());
    @FXML
    private Moves moves=new Moves();
    
    @FXML 
    protected void handleSubmitNuevoCubo(ActionEvent event) {
       //rubik=new Rubik();
    }
    
    
    @FXML
    private void handleHowTo(ActionEvent event) {
        Parent root;
        try {
            root = FXMLLoader.load(getClass().getClassLoader().getResource("HowTo.fxml"));
            Stage stage = new Stage();
            stage.setTitle("¿Cómo jugar?");
            stage.setScene(new Scene(root, 600, 400));
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    @FXML
    private void doScramble(){
        rubik.doScramble();
        rubik.isOnScrambling().addListener((ov,v,v1)->{
            if(v && !v1){
                System.out.println("scrambled!");
                moves=new Moves();
                time=LocalTime.now();
                timer.playFromStart();
            }
        });
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
