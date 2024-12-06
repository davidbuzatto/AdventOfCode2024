package d06;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import utils.Utils;

/**
 *
 * @author Prof. Dr. David Buzatto
 */
public class Solver {
    
    public static void main( String[] args ) throws Exception {
        
        solveEasy();
        //solveHard();
        
    }
    
    private static void solveEasy() throws Exception {
        
        Scanner scan = Utils.getScannerEasy( Solver.class );
        List<Character[]> map = new ArrayList<>();
        
        int currLine = 0;
        int gx = 0;
        int gy = 0;
        
        while ( scan.hasNextLine() ) {
            
            char[] cline = scan.nextLine().toCharArray();
            Character[] Cline = new Character[cline.length];
            for ( int i = 0; i < cline.length; i++ ) {
                Cline[i] = cline[i];
                if ( cline[i] == '<' || cline[i] == '>' || cline[i] == '^' || cline[i] == 'v' ) {
                    gx = i;
                    gy = currLine;
                }
            }
            map.add( Cline );
            currLine++;
        }
        
        
        //printMap( map );
        //System.out.println( gx + " " + gy );
        traverseMap( map, gx, gy );
        //printMap( map );
        
        System.out.println( countMap( map ) );
        
        
    }
    
    private static void solveHard() throws Exception {
        Scanner scan = Utils.getScannerHard( Solver.class );
    }
    
    private static void traverseMap( List<Character[]> map, int x, int y ) {
        
        int ix = 0;
        int iy = 0;
        int xp = 0;
        int yp = 0;
        
        int lines = map.size();
        int columns = map.get(0).length;
        int currLine = 0;
        boolean stop = false;
        
        while ( !stop ) {
            
            currLine = 0;
            Character[] line = map.get( y );
            
            switch ( line[x] ) {
                case '<': ix = -1; iy = 0; break;
                case '>': ix = 1; iy = 0; break;
                case '^': ix = 0; iy = -1; break;
                case 'v': ix = 0; iy = 1; break;
            }
            
            xp = x;
            yp = y;
            x += ix;
            y += iy;
            //System.out.print( xp + " " + yp + " --> " + x + " " + y );
            
            if ( x < 0 || x >= line.length || y < 0 || y >= lines ) {
                line[xp] = 'X';
                stop = true;
            } else {
                Character[] nextLine = map.get( y );
                char p = line[xp];
                char pg = nextLine[x];
                //System.out.println(" " + p + " " + pg );
                if ( pg == '#' ) {
                    switch ( p ) {
                        case '<': line[xp] = '^'; x = xp; y = yp; break;
                        case '>': line[xp] = 'v'; x = xp; y = yp; break;
                        case '^': line[xp] = '>'; x = xp; y = yp; break;
                        case 'v': line[xp] = '<'; x = xp; y = yp; break;
                    }
                } else {
                    //System.out.println( "po" );
                    nextLine[x] = p;
                    line[xp] = 'X';
                }
                
            }
            
        }
        
    }
    
    private static void printMap( List<Character[]> map ) {
        for ( Character[] line : map ) {
            System.out.println( Arrays.toString( line ) );
        }
    }
    
    private static int countMap( List<Character[]> map ) {
        int count = 0;
        for ( Character[] line : map ) {
            for ( char c : line ) {
                if ( c == 'X' ) {
                    count++;
                }
            }
        }
        return count;
    }
    
}
