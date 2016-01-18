package com.mvm.games.records;

import java.time.LocalDateTime;
import java.util.Comparator;

/**
 *
 * Para comparar por tiempo ocupado
 */
public class ComparatorTime extends Record implements Comparator<Record>{
    
    //Really?
    public ComparatorTime(LocalDateTime date, String name, int moves, int duration) {
        super(date, name, moves, duration);
    }

    
    @Override
    public int compare(Record arg0, Record arg1) {
	int d1 = arg0.getDuration();
	int d2 = arg1.getDuration();
	if (d1>d2)
            return 1;
        else if (d1<d2)
            return -1;
        else
            return 0;
	}
        
}
