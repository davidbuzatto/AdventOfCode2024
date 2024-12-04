package d04;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import utils.Utils;

/**
 *
 * @author Prof. Dr. David Buzatto
 */
public class Solver {
    
    public static void main( String[] args ) throws Exception {
        
        //solveEasy();
        solveHard();
        
    }
    
    private static void solveEasy() throws Exception {
        
        Scanner scan = Utils.getScannerEasy( Solver.class );
        List<Character[]> list = new ArrayList<>();
        while ( scan.hasNextLine() ) {
            char[] as = scan.nextLine().toCharArray();
            Character[] array = new Character[as.length];
            for ( int i = 0; i < as.length; i++ ) {
                array[i] = as[i];
            }
            list.add( array );
        }
        
        int count = 0;
        
        for ( int i = 0; i < list.size(); i++ ) {
            Character[] line = list.get( i );
            for ( int j = 0; j < line.length; j++ ) {
                if ( line[j] == 'X' ) {
                    count += searchforXMAS( list, i, j );
                }
            }
        }
        
        System.out.println( count );
    }
    
    private static void solveHard() throws Exception {
        
        Scanner scan = Utils.getScannerHard( Solver.class );
        
        List<Character[]> list = new ArrayList<>();
        while ( scan.hasNextLine() ) {
            char[] as = scan.nextLine().toCharArray();
            Character[] array = new Character[as.length];
            for ( int i = 0; i < as.length; i++ ) {
                array[i] = as[i];
            }
            list.add( array );
        }
        
        int count = 0;
        
        for ( int i = 0; i < list.size(); i++ ) {
            Character[] line = list.get( i );
            for ( int j = 0; j < line.length; j++ ) {
                if ( line[j] == 'A' ) {
                    count += searchforMASX( list, i, j );
                }
            }
        }
        
        System.out.println( count );
        
    }
    
    private static int searchforXMAS( List<Character[]> list, int line, int column ) {
        
        Character[] aline = list.get( line );
        int lines = list.size();
        int columns = aline.length;
        int count = 0;
        
        
        // horizontal
        if ( column + 3 < columns ) {
            count += aline[column+1] == 'M' && aline[column+2] == 'A' && aline[column+3] == 'S' ? 1 : 0;
        }
        if ( column - 3 >= 0 ) {
            count += aline[column-1] == 'M' && aline[column-2] == 'A' && aline[column-3] == 'S' ? 1 : 0;
        }
        
        // vertical
        if ( line + 3 < lines ) {
            Character[] line1 = list.get( line+1 );
            Character[] line2 = list.get( line+2 );
            Character[] line3 = list.get( line+3 );
            count += line1[column] == 'M' && line2[column] == 'A' && line3[column] == 'S' ? 1 : 0 ;
        }
        if ( line - 3 >= 0 ) {
            Character[] line1 = list.get( line-1 );
            Character[] line2 = list.get( line-2 );
            Character[] line3 = list.get( line-3 );
            count += line1[column] == 'M' && line2[column] == 'A' && line3[column] == 'S' ? 1 : 0 ;
        }
        
        // diagonal principal
        if ( line + 3 < lines && column + 3 < columns  ) {
            Character[] line1 = list.get( line+1 );
            Character[] line2 = list.get( line+2 );
            Character[] line3 = list.get( line+3 );
            count += line1[column+1] == 'M' && line2[column+2] == 'A' && line3[column+3] == 'S' ? 1 : 0 ;
        }
        if ( line + 3 < lines && column - 3 >= 0  ) {
            Character[] line1 = list.get( line+1 );
            Character[] line2 = list.get( line+2 );
            Character[] line3 = list.get( line+3 );
            count += line1[column-1] == 'M' && line2[column-2] == 'A' && line3[column-3] == 'S' ? 1 : 0 ;
        }
        
        // diagonal secundária
        if ( line - 3 >= 0 && column + 3 < columns  ) {
            Character[] line1 = list.get( line-1 );
            Character[] line2 = list.get( line-2 );
            Character[] line3 = list.get( line-3 );
            count += line1[column+1] == 'M' && line2[column+2] == 'A' && line3[column+3] == 'S' ? 1 : 0 ;
        }
        if ( line - 3 >= 0 && column - 3 >= 0  ) {
            Character[] line1 = list.get( line-1 );
            Character[] line2 = list.get( line-2 );
            Character[] line3 = list.get( line-3 );
            count += line1[column-1] == 'M' && line2[column-2] == 'A' && line3[column-3] == 'S' ? 1 : 0 ;
        }
        
        return count;
        
    }
    
    private static int searchforMASX( List<Character[]> list, int line, int column ) {
        
        Character[] aline = list.get( line );
        int lines = list.size();
        int columns = aline.length;
        int count = 0;
        
        if ( line - 1 >= 0 && line + 1 < lines && column - 1 >= 0 && column + 1 < columns ) {
            Character[] line1 = list.get(line-1);
            Character[] line2 = list.get(line+1);
            count += line1[column-1] == 'M' && line2[column+1] == 'S' && line2[column-1] == 'M' && line1[column+1] == 'S' ? 1 : 0 ;
            count += line1[column-1] == 'S' && line2[column+1] == 'M' && line2[column-1] == 'S' && line1[column+1] == 'M' ? 1 : 0 ;
            count += line1[column-1] == 'M' && line2[column+1] == 'S' && line2[column-1] == 'S' && line1[column+1] == 'M' ? 1 : 0 ;
            count += line1[column-1] == 'S' && line2[column+1] == 'M' && line2[column-1] == 'M' && line1[column+1] == 'S' ? 1 : 0 ;
        }
        
        return count;
        
    }
    
}
