
package com.mvm.games.records;

import java.time.LocalDateTime;
import java.util.Comparator;

/**
 *
 * Para comparar por cantidad de movimientos
 */
public class ComparatorMoves extends Record implements Comparator<Record>{

    //really? (2)
    public ComparatorMoves(LocalDateTime date, String name, int moves, int duration) {
        super(date, name, moves, duration);
    }
    
    @Override
    public int compare(Record arg0, Record arg1) {
	int d1 = arg0.getMoves();
	int d2 = arg1.getMoves();
	if (d1>d2)
            return 1;
        else if (d1<d2)
            return -1;
        else
            return 0;
	}
}
