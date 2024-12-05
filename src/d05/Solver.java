package d05;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeMap;
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
        
        Map<Integer, Set<Integer>> before = new TreeMap<>();
        Map<Integer, Set<Integer>> after = new TreeMap<>();
        int sumCorrect = 0;
        while ( scan.hasNextLine() ) {
            
            String line = scan.nextLine();
            
            if ( line.contains( "|" ) ) {
                
                String[] v = line.split( "\\|" );
                int a = Integer.parseInt( v[0] );
                int b = Integer.parseInt( v[1] );
                
                Set<Integer> sBefore = before.get( a );
                if ( sBefore == null ) {
                    sBefore = new LinkedHashSet<>();
                    before.put( a, sBefore );
                }
                sBefore.add( b );
                
                Set<Integer> sAfter = after.get( b );
                if ( sAfter == null ) {
                    sAfter = new HashSet<>();
                    after.put( b, sAfter );
                }
                sAfter.add( a );
                
                //System.out.println( Arrays.toString( v ) );
                
            } else {
                if ( !line.trim().isEmpty() ) {
                    String[] v = line.split( "," );
                    sumCorrect += checkCorrectness( before, after, v );
                }
            }
            
        }
        
        System.out.println( sumCorrect );
        
    }
    
    private static void solveHard() throws Exception {
        
        Scanner scan = Utils.getScannerHard( Solver.class );
        
        Map<Integer, Set<Integer>> before = new TreeMap<>();
        Map<Integer, Set<Integer>> after = new TreeMap<>();
        List<Integer[]> incorrects = new ArrayList<>();
        
        while ( scan.hasNextLine() ) {
            
            String line = scan.nextLine();
            
            if ( line.contains( "|" ) ) {
                
                String[] v = line.split( "\\|" );
                int a = Integer.parseInt( v[0] );
                int b = Integer.parseInt( v[1] );
                
                Set<Integer> sBefore = before.get( a );
                if ( sBefore == null ) {
                    sBefore = new LinkedHashSet<>();
                    before.put( a, sBefore );
                }
                sBefore.add( b );
                
                Set<Integer> sAfter = after.get( b );
                if ( sAfter == null ) {
                    sAfter = new HashSet<>();
                    after.put( b, sAfter );
                }
                sAfter.add( a );
                
            } else {
                if ( !line.trim().isEmpty() ) {
                    String[] v = line.split( "," );
                    Integer[] inc = findIncorrect( before, after, v );
                    if ( inc != null ) {
                        incorrects.add( inc );
                    }
                }
            }
            
        }
        
        int sumIncorrect = 0;
        for ( Integer[] inc : incorrects ) {
            sumIncorrect += findMiddlePage( before, after, inc );
        }
        System.out.println( sumIncorrect );
        
    }
    
    private static int checkCorrectness( Map<Integer, Set<Integer>> before, Map<Integer, Set<Integer>> after, String[] pages ) {
        
        boolean correct = true;
        int[] nPages = new int[pages.length];
        //System.out.println( Arrays.toString( pages ) );
        
        for ( int i = 0; i < pages.length; i++ ) {
            nPages[i] = Integer.parseInt(pages[i]);
        }
        
        for ( int i = 0; i < nPages.length; i++ ) {
            int a = nPages[i];
            for ( int j = i+1; j < nPages.length; j++ ) {
                int b = nPages[j];
                //System.out.println( a + " " + b );
                Set<Integer> sBefore = before.get( a );
                if ( sBefore != null ) {
                    //System.out.println( a + " antes de " + b + "? " + sBefore.contains( b ) );
                    if ( !sBefore.contains( b ) ) {
                        correct = false;
                        break;
                    }
                }
            }
            if ( !correct ) {
                break;
            }
        }
        
        if ( correct ) {
            for ( int i = nPages.length-1; i >= 0; i-- ) {
                int a = nPages[i];
                for ( int j = i-1; j >= 0; j-- ) {
                    int b = nPages[j];
                    //System.out.println( a + " " + b );
                    Set<Integer> sAfter = after.get( a );
                    if ( sAfter != null ) {
                        //System.out.println( a + " antes de " + b + "? " + sAfter.contains( b ) );
                        if ( !sAfter.contains( b ) ) {
                            correct = false;
                            break;
                        }
                    }
                }
                if ( !correct ) {
                    break;
                }
            }
        }
        
        if ( correct ) {
            return nPages[nPages.length/2];
        }
        
        return 0;
        
    }
    
    private static Integer[] findIncorrect( Map<Integer, Set<Integer>> before, Map<Integer, Set<Integer>> after, String[] pages ) {
        
        boolean correct = true;
        Integer[] nPages = new Integer[pages.length];
        
        for ( int i = 0; i < pages.length; i++ ) {
            nPages[i] = Integer.valueOf(pages[i]);
        }
        
        for ( int i = 0; i < nPages.length; i++ ) {
            int a = nPages[i];
            for ( int j = i+1; j < nPages.length; j++ ) {
                int b = nPages[j];
                Set<Integer> sBefore = before.get( a );
                if ( sBefore != null ) {
                    if ( !sBefore.contains( b ) ) {
                        correct = false;
                        break;
                    }
                }
            }
            if ( !correct ) {
                break;
            }
        }
        
        if ( correct ) {
            for ( int i = nPages.length-1; i >= 0; i-- ) {
                int a = nPages[i];
                for ( int j = i-1; j >= 0; j-- ) {
                    int b = nPages[j];
                    Set<Integer> sAfter = after.get( a );
                    if ( sAfter != null ) {
                        if ( !sAfter.contains( b ) ) {
                            correct = false;
                            break;
                        }
                    }
                }
                if ( !correct ) {
                    break;
                }
            }
        }
        
        if ( correct ) {
            return null;
        }
        
        return nPages;
        
    }
    
    public static int findMiddlePage( Map<Integer, Set<Integer>> before, Map<Integer, Set<Integer>> after, Integer[] pages ) {
        
        int m = pages.length / 2;
        //System.out.println( "starting: " + Arrays.toString( pages ) );
        
        for ( int i = 0; i < pages.length; i++ ) {
            int count = 0;
            Set<Integer> sBefore = before.get( pages[i] );
            if ( sBefore != null ) {
                for ( int j = 0; j < pages.length; j++ ) {
                    if ( i != j ) {
                        if ( sBefore.contains( pages[j] ) ) {
                            count++;
                        }
                    }
                }
                if ( count == m ) {
                    return pages[i];
                }
            }
        }
        
        return 0;
        
    }
    
}
