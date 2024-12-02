package d02;

import java.util.Arrays;
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
        boolean increasing;
        boolean decreasing;
        boolean safe;
        int safeCount = 0;
        
        while ( scan.hasNextLine() ) {
            
            String report = scan.nextLine();
            String[] levels = report.split( "\\s+");
            increasing = false;
            decreasing = false;
            safe = true;
            
            for ( int i = 0; i < levels.length - 1; i++ ) {
                int v1 = Integer.parseInt( levels[i] );
                int v2 = Integer.parseInt( levels[i+1] );
                int diff = v2 - v1;
                //System.out.println( v1 + " " + v2 + " " + diff );
                if ( diff < 0 ) {
                    decreasing = true;
                    if ( diff > -1 || diff < -3 ) {
                        safe = false;
                    }
                } else if ( diff > 0 ) {
                    increasing = true;
                    if ( diff < 1 || diff > 3 ) {
                        safe = false;
                    }
                } else {
                    safe = false;
                }
            }
            //System.out.println( " " + decreasing + " " + increasing + " " + safe );
            if ( safe ) {
                safe = ( ( increasing && !decreasing ) || ( !increasing && decreasing ) );
            }
            if ( safe ) {
                safeCount++;
            }
            System.out.println( Arrays.toString( levels ) + " " + safe );
        }
        
        System.out.println( safeCount );
        
        
    }
    
    private static void solveHard() throws Exception {
        
        Scanner scan = Utils.getScannerHard( Solver.class );
        
        boolean increasing;
        boolean decreasing;
        boolean safe;
        int safeCount = 0;
        int unsafeCountAfter = 0;
        
        while ( scan.hasNextLine() ) {
            
            String report = scan.nextLine();
            String[] levels = report.split( "\\s+");
            increasing = false;
            decreasing = false;
            safe = true;
            
            for ( int i = 0; i < levels.length - 1; i++ ) {
                int v1 = Integer.parseInt( levels[i] );
                int v2 = Integer.parseInt( levels[i+1] );
                int diff = v2 - v1;
                if ( diff < 0 ) {
                    decreasing = true;
                    if ( diff > -1 || diff < -3 ) {
                        safe = false;
                    }
                } else if ( diff > 0 ) {
                    increasing = true;
                    if ( diff < 1 || diff > 3 ) {
                        safe = false;
                    }
                } else {
                    safe = false;
                }
            }
            
            if ( safe ) {
                safe = ( ( increasing && !decreasing ) || ( !increasing && decreasing ) );
            }
            
            if ( !safe ) {
                
                //System.out.println( Arrays.toString( levels ) + " unsafe... doing further processing" );
                increasing = false;
                decreasing = false;
                
                for ( int k = 0; k < levels.length; k++ ) {
                    //System.out.println( "jumping " + k );
                    increasing = false;
                    decreasing = false;
                    safe = true;
                    for ( int i = 0; i < levels.length - 1; i++ ) {
                        if ( i != k ) {
                            int v1 = Integer.parseInt( levels[i] );
                            int v2;
                            if ( i == k - 1 ) {
                                if ( i + 2 >= levels.length ) {
                                    break;
                                }
                                v2 = Integer.parseInt( levels[i+2] );
                            } else {
                                v2 = Integer.parseInt( levels[i+1] );
                            }
                            int diff = v2 - v1;
                            //System.out.println( v1 + " " + v2 + " " + diff );
                            if ( diff < 0 ) {
                                decreasing = true;
                                if ( diff > -1 || diff < -3 ) {
                                    //System.out.println( "diff <" );
                                    safe = false;
                                }
                            } else if ( diff > 0 ) {
                                increasing = true;
                                if ( diff < 1 || diff > 3 ) {
                                    //System.out.println( "diff >" );
                                    safe = false;
                                }
                            } else {
                                //System.out.println( "equals" );
                                safe = false;
                            }
                        }
                    }
                    if ( safe ) {
                        safe = ( ( increasing && !decreasing ) || ( !increasing && decreasing ) );
                    }
                    //System.out.println( decreasing + " " + increasing + " " + safe );
                    if ( safe ) {
                        break;
                    }
                }
                
            }
            
            System.out.println( Arrays.toString( levels ) + " " + safe );
            if ( safe ) {
                safeCount++;
            }
            
        }
        
        System.out.println( safeCount );
        
    }
    
}
