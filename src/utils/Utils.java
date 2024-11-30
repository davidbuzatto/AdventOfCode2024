package utils;

import java.io.File;
import java.io.StringReader;
import java.util.Scanner;

/**
 *
 * @author Prof. Dr. David Buzatto
 */
public class Utils {
    
    public static Scanner getScannerSmall( Class klass ) throws Exception {
        return new Scanner( new File( "resources/testData/" + klass.getPackageName() + "/small.txt" ) );
    }
    
    public static Scanner getScannerLarge( Class klass ) throws Exception {
        return new Scanner( new File( "resources/testData/" + klass.getPackageName() + "/large.txt" ) );
    }
    
    public static Scanner getScannerForString( String inputString ) throws Exception {
        return new Scanner( new StringReader( inputString ) );
    }
    
}
