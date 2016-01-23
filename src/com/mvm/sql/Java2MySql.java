/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mvm.sql;

import com.mvm.games.records.Record;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javax.naming.NamingException;

/**
 *
 * @author Administrator
 */
public class Java2MySql {
    private final String url; 
    private final String dbName; 
    private final String driver;  
    private final String userName; 
    private final String password; 

    public Java2MySql() {
        this.url = "jdbc:mysql://localhost:3306/rubik"; 
        this.dbName = "rubik"; 
        this.driver = "com.mysql.jdbc.Driver";  
        this.userName = "root"; 
        this.password = "rubik"; 
    }
    
    public Connection openConnection() throws NamingException{
        try{
            Class.forName(driver).newInstance(); 
            return DriverManager.getConnection(url,userName,password); 
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("error al conectar ");
        }
        return null;
    }

    public void closeConnection(Connection salida){
        try{
            salida.close();
        }catch(Exception e){
            e.printStackTrace();
            System.out.println("error al conectar ");
        }
    } 
    
    public static  ObservableList<Record> loadData(Connection conn) throws SQLException{
        ObservableList<Record> recordData = FXCollections.observableArrayList();
        Statement  stmt = conn.createStatement();
        String sql = "SELECT date, name, moves, duration FROM record";
        ResultSet rs = stmt.executeQuery(sql); 
        
        if(rs.next()){
            do{                      
            Record rec = new Record(rs.getDate("date"),rs.getString("name") , 
                    rs.getInt("moves"), rs.getInt("duration"));
            recordData.add(rec);
        }while(rs.next());
        }
        
         
        return recordData;
    }
    
    public static void insertData(Connection conn, Record rec) throws SQLException{
        String query = "INSERT INTO record(`name`, `moves`, `duration`)"+" VALUES (?, ?, ?); ";
        PreparedStatement preparedStmt = conn.prepareStatement(query);
        preparedStmt.setString(1, rec.getName().toString());
        preparedStmt.setInt(2, rec.getMoves().intValue());
        preparedStmt.setInt(3, rec.getDuration().intValue());
        
        preparedStmt.execute();
         
    }
}
