package d03;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
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
        StringBuilder sb = new StringBuilder();
        while ( scan.hasNextLine() ) {
            sb.append( scan.nextLine() );
        }
        
        Pattern p = Pattern.compile( "(mul\\(\\d+,\\d+\\))" );
        Matcher m = p.matcher( sb.toString() );
        int sum = 0;
        
        while ( m.find() ) {
            String e = m.group(1);
            Pattern pe = Pattern.compile( "(\\d+),(\\d+)" );
            Matcher me = pe.matcher( e );
            while ( me.find() ) {
                int n1 = Integer.parseInt( me.group(1) );
                int n2 = Integer.parseInt( me.group(2) );
                int mult = n1 * n2;
                sum += mult;
            }
        }
        
        System.out.println( sum );
        
    }
    
    private static void solveHard() throws Exception {
        
        Scanner scan = Utils.getScannerHard( Solver.class );
        
        StringBuilder sb = new StringBuilder();
        while ( scan.hasNextLine() ) {
            sb.append( scan.nextLine() );
        }
        
        Pattern p = Pattern.compile( "(mul\\(\\d+,\\d+\\))" );
        Matcher m = p.matcher( sb.toString() );
        int sum = 0;
        
        while ( m.find() ) {
            String e = m.group(1);
            Pattern pe = Pattern.compile( "(\\d+),(\\d+)" );
            Matcher me = pe.matcher( e );
            while ( me.find() ) {
                int n1 = Integer.parseInt( me.group(1) );
                int n2 = Integer.parseInt( me.group(2) );
                int mult = n1 * n2;
                sum += mult;
            }
        }
        
        System.out.println( sum );
        
    }
    
}
