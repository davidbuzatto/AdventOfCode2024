package d01;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import utils.Utils;

/**
 *
 * @author Prof. Dr. David Buzatto
 */
public class Solver {
    
    public static void main( String[] args ) throws Exception {
        
        //solveEasy();
        //solveHard();
        
    }
    
    private static void solveEasy() throws Exception {
        
        Scanner scan = Utils.getScannerEasy( Solver.class );
        
        List<Integer> left = new ArrayList<>();
        List<Integer> right = new ArrayList<>();
        
        while ( scan.hasNextLine() ) {
            left.add( scan.nextInt() );
            right.add( scan.nextInt() );
        }
        
        Collections.sort( left );
        Collections.sort( right );
        long distance = 0;
        
        for ( int i = 0; i < left.size(); i++ ) {
            distance += Math.abs( left.get( i ) - right.get( i ) );
        }
        
        System.out.println( distance );
        
    }
    
    private static void solveHard() throws Exception {
        
        Scanner scan = Utils.getScannerHard( Solver.class );
        
        List<Integer> list = new ArrayList<>();
        Map<Integer, Integer> map = new HashMap<>();
        
        while ( scan.hasNextLine() ) {
            list.add( scan.nextInt() );
            int k = scan.nextInt();
            if ( !map.containsKey( k ) ) {
                map.put( k, 0 );
            }
            map.put( k, map.get( k ) + 1 );
        }
        
        Collections.sort( list );
        
        long similarity = 0;
        
        for ( int k : list ) {
            similarity += k * map.getOrDefault( k, 0 );
        }
        
        System.out.println( similarity );
        
    }
    
}
