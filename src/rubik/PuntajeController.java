/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rubik;

import com.mvm.games.records.Record;
import java.net.URL;
import java.sql.Date;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

/**
 * FXML Controller class
 *
 * @author asus
 */
public class PuntajeController extends RubikMain implements Initializable {

    /**
     * Initializes the controller class.
     */
    
    @FXML
    private Label label;   
    @FXML
    private TableView<Record> recordTable;
    @FXML
    private TableColumn<Record, String> nombres;
    @FXML
    private TableColumn<Record, Number> tiempo;
    @FXML
    private TableColumn<Record, Number> movimientos;
    @FXML
    private TableColumn<Record, Date> fecha;

     
    @FXML
    public void initialize(URL url, ResourceBundle rb) {
        
        recordTable.setItems(RubikMain.getRecordData());
        
        nombres.setCellValueFactory(cellData -> cellData.getValue().getName());
        tiempo.setCellValueFactory(cellData -> cellData.getValue().getDuration());
        movimientos.setCellValueFactory(cellData -> cellData.getValue().getMoves());
        fecha.setCellValueFactory(cellData -> cellData.getValue().getDate());
    }    


}
