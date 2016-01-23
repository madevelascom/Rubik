/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rubik;

import com.mvm.games.records.Record;
import java.sql.Date;
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
public abstract class PuntajeController implements Initializable {

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
    private void salir(ActionEvent event) {   
        System.exit(0);
    }
    
    @FXML
    public void initialize() {
        
        nombres.setCellValueFactory(cellData -> cellData.getValue().getName());
        tiempo.setCellValueFactory(cellData -> cellData.getValue().getDuration());
        movimientos.setCellValueFactory(cellData -> cellData.getValue().getMoves());
        fecha.setCellValueFactory(cellData -> cellData.getValue().getDate());
    }    
    
     public void setMainApp(RubikMain mainApp) {
        recordTable.setItems(mainApp.getRecordData());
    }
}
