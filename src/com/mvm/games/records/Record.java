
package com.mvm.games.records;

import java.sql.Date;
import java.time.LocalDate;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;



public class Record {
    private ObjectProperty<Date> date;
    private StringProperty  name;
    private IntegerProperty moves;
    private IntegerProperty duration;

    public Record(Date date, String name, int moves, int duration) {
        this.date = null;
        this.name = new SimpleStringProperty("AAA");
        this.moves = new SimpleIntegerProperty(moves);
        this.duration = new SimpleIntegerProperty(duration);
    }

    public ObjectProperty<Date> getDate() {
        return date;
    }

    public void setDate(ObjectProperty<Date> date) {
        this.date = date;
    }

    public StringProperty getName() {
        return name;
    }

    public void setName(StringProperty name) {
        this.name = name;
    }

    public IntegerProperty getMoves() {
        return moves;
    }

    public void setMoves(IntegerProperty moves) {
        this.moves = moves;
    }
    
    public IntegerProperty getDuration() {
        return duration;
    }

    public void setDuration(IntegerProperty duration) {
        this.duration = duration;
    }
 
}
