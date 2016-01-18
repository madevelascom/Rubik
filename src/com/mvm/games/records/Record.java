
package com.mvm.games.records;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;


public class Record {
    private LocalDateTime date;
    private String name;
    private int moves;
    private int duration;

    public Record(LocalDateTime date, String name, int moves, int duration) {
        this.date = date;
        this.name = name;
        this.moves = moves;
        this.duration = duration;
        
    }

    public LocalDateTime getDate() {
        return date;
    }

    public String getName() {
        return name;
    }

    public int getMoves() {
        return moves;
    }

    public int getDuration() {
        return duration;
    }
    
    

    public void setDate(LocalDateTime date) {
        this.date = LocalDateTime.now();
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setMoves(int moves) {
        this.moves = moves;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    
    
    /*Convierte a formato de fecha el string de la fecha*/
    public static LocalDateTime toDate(String fecha){
	LocalDateTime result = LocalDateTime.parse(fecha);
	return result;
		
    }
    
    public static HashMap<String, Record> cargarRecords() throws IOException {
        
        //TODO
        File file = new File("TBD");
        HashMap<String, Record> hm = new HashMap<String, Record>();
        
        if(file.exists()){
            URL url		= Record.class.getResource(file.getName());
            BufferedReader br 	= new BufferedReader(new FileReader(url.getPath()));

            try {
                String linea = br.readLine();
                linea= br.readLine();
                while (linea != null){
                    String []atributos = linea.split("\\|");
                    Record  rec = new Record (toDate(atributos[0]),atributos[1],
                        Integer.parseInt(atributos[2]),Integer.parseInt(atributos[3]));
				hm.put(rec.getName(), rec);
			linea=br.readLine();
			}					
            }catch(FileNotFoundException ex) {
                System.out.println("No se puede abrir el archivo");                
            }finally{
        	br.close();
            }
        
        }
        return hm;
    }

    public static void guardarCalificaciones(HashMap<String, Record> mp) throws IOException {
        /*TODO*/
        File file = new File("TBD");
        if (!file.exists()){
            file.createNewFile();
        }
	
        URL url			= Record.class.getResource(file.getName());
	FileWriter fl 		= new FileWriter(url.getPath());		
	BufferedWriter bw 	= new BufferedWriter(fl);
        
        try {
            bw.write("Date|Nombre|Movimientos|Tiempo");
			
		for (Record value : mp.values()) {
                    bw.newLine();
                    bw.write(value.getDate()+"|"+ value.getName()+"|"+value.getMoves()+"|"+value.getDuration());}
			
	}catch(IOException ex){
            ex.printStackTrace();
        }finally{
        	bw.close();
        }
        
    }    

    
}
    
