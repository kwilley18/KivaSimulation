
/**
 * Write a description of KivaMoveTest here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import edu.duke.*;
import java.util.*;
import edu.duke.Point; 

public class KivaMoveTest {
    
    String defaultLayout = ""
                           + "-------------\n"
                           + "        P   *\n"
                           + "   **       *\n"
                           + "   **       *\n"
                           + "  K       D *\n"
                           + " * * * * * **\n"
                           + "-------------\n";
                        
    FloorMap defaultMap = new FloorMap(defaultLayout); 
    
    public void testForward(){
        Kiva kivaTest = new Kiva(defaultMap); 
        
        kivaTest.move(KivaCommand.FORWARD); 
        
        verifyKivaState("testForwardUP", kivaTest, new Point (2,3), FacingDirection.UP, false, false); 
        
        kivaTest.move(KivaCommand.FORWARD); 
        verifyKivaState("testForwardLEFT", kivaTest, new Point (2,3), FacingDirection.LEFT, false, false); 
        
        kivaTest.move(KivaCommand.FORWARD); 
        verifyKivaState("testForwardDOWN", kivaTest, new Point (2,3), FacingDirection.DOWN, false, false); 
        
        kivaTest.move(KivaCommand.FORWARD); 
        verifyKivaState("testForwardRIGHT", kivaTest, new Point (2,3), FacingDirection.RIGHT, false, false); 
        
    }
    
        
    public void testLeft(){
        Kiva kivaTest = new Kiva(defaultMap); 
        
        kivaTest.move(KivaCommand.FORWARD); 
        
        verifyKivaState("testLeft", kivaTest, new Point (2,3), FacingDirection.UP, false, false); 
        
        
    }
    
    public void testRight(){
    
    }
    
    public void testTake(){
    
    }
    
    public void testDrop(){
    
    }
    
        
    private boolean sameLocation(Point a, Point b){
        return a.getX() == b.getX() && a.getY() == b.getY(); 
    }
    
    private void verifyKivaState(String test, Kiva actual, Point expectLocation, FacingDirection expectingDirection, 
    boolean expectCarry, boolean expectDropped){
        
        Point actualLocation = actual.getCurrentLocation(); 
        
            //System.out.println(actualLocation); (1,3)
            //System.out.println(expectLocation); (2,3)
            if(sameLocation( actualLocation, expectLocation)){
                System.out.println( String.format("success", test)); 
            }
            else{
                System.out.println(test); 
                System.out.println( String.format("fail", test)); 
            }
            
            
        
    }

}
