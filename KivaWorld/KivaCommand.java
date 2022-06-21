import edu.duke.*;
import java.util.*;
import edu.duke.Point; 
/**
 * Write a description of KivaCommand here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public enum KivaCommand {
    FORWARD('F'),
    TURN_RIGHT('R'),
    TURN_LEFT('L'),
    TAKE('T'),
    DROP('D'); 
    
    public char directionKey; 
    
    private KivaCommand(char directionKey){
        this.directionKey = directionKey; 
    }
    public char getDirectionKey(){
        return directionKey; 
    }

}

