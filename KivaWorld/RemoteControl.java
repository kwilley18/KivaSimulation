import edu.duke.FileResource;
import java.util.Arrays; 

/**
 * This is the class that controls Kiva's actions. Implement the <code>run()</code>
 * method to deliver the pod and avoid the obstacles.
 *
 * This is starter code that may or may not work. You will need to update the code to
 * complete the project.
 */

public class RemoteControl {
    KeyboardResource keyboardResource;

    /**
     * Build a new RemoteControl.
     */
    public RemoteControl() {
        keyboardResource = new KeyboardResource();
    }

    /**
     * The controller that directs Kiva's activity. Prompts the user for the floor map
     * to load, displays the map, and asks the user for the commands for Kiva to execute.
     *
     * [Here's the method you'll execute from within BlueJ. It may or may not run successfully
     * as-is, but you'll definitely need to add more to complete the project.]
     */
    public void run() {
        System.out.println("Please select a map file.");
        FileResource fileResource = new FileResource("sample_floor_map3.txt");
        String inputMap = fileResource.asString();
        FloorMap floorMap = new FloorMap(inputMap);
        System.out.println(floorMap);
        // RFFFFFTFFFFFFFD RFFFFFFLFFFTRFFRFFFD RRFFFLFFFFLFFFRFTFRFFFLFFFFFFFFFFLFFFD
        // RFFFFFFLFFFTRFFRFFFDR   R  RRFFFLFFFFLFFFRFT  RFFFFFLFFFTRFFRFFFD 
        // RFFFFFFLFFFTRFFRFFD    F
        Kiva kivaRunner = new Kiva(floorMap);

        System.out.println("Current Kiva Robot location: " + kivaRunner.currentLocation); 
        System.out.println("Facing: " + kivaRunner.directionFacing); 
        System.out.println("Please enter the directions for the Kiva Robot to take.");
        String directions = keyboardResource.getLine();
        System.out.println("Directions that you typed in: " + directions);
        // RFFFFFTFFFFFFFD
        // RRFFFLFFFFLFFFRFT
        KivaCommand[] commandArray = convertToKivaCommands(directions);
     
         if(commandArray[commandArray.length - 1] == KivaCommand.TURN_RIGHT || commandArray[commandArray.length - 1] == KivaCommand.TAKE){
               System.out.println("I'm sorry. The Kiva Robot did not pick up the pod then drop it off in the right place. ");
               
        }
        else{
            for(int i = 0; i < commandArray.length; i++){
      
               kivaRunner.move(commandArray[i]);
           
            }
        }
        
       

    }
    public KivaCommand[] convertToKivaCommands(String commands){
        KivaCommand[] commandArray = new KivaCommand[commands.length()]; 
       
        for(int i = 0; i < commands.length(); i++){
            
            
          if(commands.charAt(i) == 'F'){
              commandArray[i] = KivaCommand.FORWARD; 
          }
          else if(commands.charAt(i) == 'R'){
               commandArray[i] = KivaCommand.TURN_RIGHT; 
          }
          else if(commands.charAt(i) == 'L'){
               commandArray[i] = KivaCommand.TURN_LEFT; 
          }
          else if(commands.charAt(i) == 'T'){
               commandArray[i] = KivaCommand.TAKE; 
          }
          else if(commands.charAt(i) == 'D'){
               commandArray[i] = KivaCommand.DROP; 
          }
        
          else{
                throw new IllegalArgumentException("Character " + "'" + commands.charAt(i) + "'" + " does not correspond to a command!"); 
           }
            

        }
        

        return commandArray; 
    }
}

