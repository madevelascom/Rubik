/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mvm.sql;

import com.mvm.games.records.Record;
import java.sql.Connection;
import java.sql.DriverManager;
import javax.naming.NamingException;

/**
 *
 * @author Administrator
 */
public class Java2MySql {
    private String url; 
    private String dbName; 
    private String driver;  
    private String userName; 
    private String password; 

    public Java2MySql() {
        this.url = "jdbc:mysql://localhost:3306/mysql"; 
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
}
