/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rubik;

import com.jpl.games.model.Rubik;
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
import rubik.RubikMain;


/**
 *
 * @author Administrator
 */
public class RubikController extends RubikMain implements Initializable {
    
    /*@FXML
    private Rubik rubik;
    rubik = new getRubik();*/
    
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
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
