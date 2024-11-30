package d11;

import br.com.davidbuzatto.jsge.core.engine.EngineFrame;

/**
 * 
 * @author Prof. Dr. David Buzatto
 */
public class Simulator extends EngineFrame {
    
    public Simulator() {
        super ( 800, 450, "Simulation", 60, true );
    }
    
    @Override
    public void create() {
    }
    
    @Override
    public void update( double delta ) {
    }
    
    @Override
    public void draw() {
        
        clearBackground( WHITE );
    
    }
    
    public static void main( String[] args ) {
        new Simulator();
    }
    
}
