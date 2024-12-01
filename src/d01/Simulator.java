package d01;

import br.com.davidbuzatto.jsge.core.engine.EngineFrame;
import br.com.davidbuzatto.jsge.core.utils.PaintUtils;
import br.com.davidbuzatto.jsge.math.Vector2;
import java.awt.Color;
import java.awt.Paint;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import utils.Utils;

/**
 * 
 * @author Prof. Dr. David Buzatto
 */
public class Simulator extends EngineFrame {
    
    private List<Integer> list;
    private Map<Integer, Integer> map;
    
    private long similarity;
    
    private double angleIncr;
    private double xCenter;
    private double yCenter;
    private double radius;
    private Map<Integer, Vector2> pointMap;
    
    private double updateCounter;
    private double updateTime;
    private int current;
    
    private Paint gradientPaint;
    
    public Simulator() {
        super ( 1300, 1300, "Simulation", 60, true );
    }
    
    @Override
    public void create() {
        
        try {
            
            Scanner scan = Utils.getScannerHard( Simulator.class );
            setDefaultFontSize( 60 );
            
            list = new ArrayList<>();
            map = new HashMap<>();     
            similarity = 0;

            while ( scan.hasNextLine() ) {
                list.add( scan.nextInt() );
                int k = scan.nextInt();
                if ( !map.containsKey( k ) ) {
                    map.put( k, 0 );
                }
                map.put( k, map.get( k ) + 1 );
            }
            
            angleIncr = 360.0 / map.size();
            xCenter  = getScreenWidth() / 2;
            yCenter  = getScreenHeight() / 2;
            radius = 450;
            pointMap = new HashMap<>();
            
            double angle = 0;
            for ( int k : map.keySet() ) {
                double x = xCenter + radius * Math.cos( Math.toRadians( angle ) );
                double y = yCenter + radius * Math.sin( Math.toRadians( angle ) );
                pointMap.put( k, new Vector2( x, y ) );
                angle += angleIncr;
            }
            
            current = 0;
            updateCounter = 0;
            updateTime = 0.005;
            
            gradientPaint = PaintUtils.getRadialGradientPaint( xCenter, yCenter, radius, new float[]{ .5f, 1f }, new Color[]{ YELLOW, ORANGE } );
            
        } catch ( Exception exc ) {
            exc.printStackTrace();
        }
        
    }
    
    @Override
    public void update( double delta ) {
        
        updateCounter += delta;
        
        if ( updateCounter > updateTime ) {
            updateCounter = 0;
            similarity += map.getOrDefault( list.get( current ), 0 );
            current = ( current + 1 ) % list.size();
        }
        
    }
    
    @Override
    public void draw() {
        
        clearBackground( BLACK );
        
        double angle = 0;
        
        for ( int k : map.keySet() ) {
            Vector2 p = pointMap.get( k );
            fillRectangle( p.x + 1, p.y, 3, k / 500, p.x, p.y, angle - 90, GOLD );
            fillRectangle( p.x + 1, p.y, 3, map.get( k ) * 3, p.x, p.y, angle - 90, RED );
            angle += angleIncr;
        }
        
        int currentVal = list.get( current );
        
        fillCircle( xCenter, yCenter, radius, gradientPaint );
        
        Vector2 currentPoint = pointMap.get( currentVal );
        if ( currentPoint != null ) {
            setStrokeLineWidth( 5 );
            drawLine( xCenter, yCenter, currentPoint.x, currentPoint.y, RED );
        }
        
        fillCircle( xCenter, yCenter, 200, YELLOW );
        //drawCircle( xCenter, yCenter, 200, BLACK );
        
        String valLabel = String.valueOf( currentVal );
        String similarityLabel = String.valueOf( similarity );
        int wLabel = measureText( valLabel );
        int wSimilarity = measureText( similarityLabel );
        drawText( valLabel, getScreenWidth() / 2 - wLabel / 2, getScreenHeight() / 2 - 40, ORANGE );
        drawText( similarityLabel, getScreenWidth() / 2 - wSimilarity / 2, getScreenHeight() / 2 + 20, RED );
    
    }
    
    public static void main( String[] args ) {
        new Simulator();
    }
    
}
