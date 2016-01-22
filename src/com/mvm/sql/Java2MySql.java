/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mvm.sql;

import com.mvm.games.records.Record;
import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author Administrator
 */
public class Java2MySql {
    String url = "jdbc:mysql://localhost:3306/"; 
    String dbName = "demo"; 
    String driver = "com.mysql.jdbc.Driver"; 
    
    String userName = "root"; 
    String password = "mypasswd"; 
        
    public void connection(){
        
        try { 
            Class.forName(driver).newInstance(); 
            Connection conn = DriverManager.getConnection(url+dbName,userName,password); 
            conn.close(); 
        } catch (Exception e) { 
            e.printStackTrace(); }

    }
    
    public void data(Record rec){
        
    }
            
}
