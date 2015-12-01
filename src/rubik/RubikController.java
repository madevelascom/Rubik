/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rubik;

import java.net.URL;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import javafx.animation.Timeline;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;


/**
 *
 * @author Administrator
 */
public class RubikController extends RubikMain implements Initializable {
    
    @FXML
    private LocalTime time=LocalTime.now();
    @FXML
    private Timeline timer;
    @FXML
    private final StringProperty clock = new SimpleStringProperty("00:00:00");
    @FXML
    private final DateTimeFormatter fmt = DateTimeFormatter.ofPattern("HH:mm:ss").withZone(ZoneId.systemDefault());
    
    //Al abrir el RootLayout en SceneBuilder, a la accion del Boton es la que llama la funcion
    //https://gyazo.com/891f77be7ef33d06d683708be4353066
    //Abre el link para que veas donde tienes que llamar la funcion. 
    @FXML
    private void Scramble(){
        rubik.doReset();
        ScrambleCube();
    }
    
    @FXML
    private void Reset(){
        getRubik().doReset();
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
