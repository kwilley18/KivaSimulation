import edu.duke.*;
import java.util.*;
import edu.duke.Point; 
/**
 * Write a description of KivaConstructorTest here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class KivaConstructorTest {
                String defaultLayout = "" // K, D, and P have coordinates. K is (0,4). Changes when moved around. 
                + "-------------\n"         
                + "          P *\n"
                + " **         *\n"
                + " **         *\n"
                + "K D         *\n"
                + " * * * * * **\n"
                + "-------------\n";

       /*String defaultLayout = ""+ "-------------------"
                               // +"|K *  P  *       D|"
                              //  +"|  *     *        |"
                              //  +"|  *     ******   |"
                              //  +"|     *           |"
                               // +"-------------------"; */ 
       
       FloorMap defaultMap = new FloorMap(defaultLayout); 
       
       public void testSingleArgumentConstructor(){
           Kiva kivaTester = new Kiva(defaultMap); 
           //kivaTester.currentLocation = new Point(2,4); 
           
           Point initialLocation = kivaTester.getCurrentLocation(); 
           Point expectedLocation = new Point(2,4); 
           
           System.out.println(initialLocation); //(1,4)
           System.out.println(expectedLocation); //(2,4)
           if(sameLocation(initialLocation, expectedLocation)){
               System.out.println("success"); 
            }
            else{
                System.out.println(String.format("fail", initialLocation, expectedLocation)); 
                
            }
        }
        
        private boolean sameLocation(Point a, Point b){
            return a.getX() == b.getX() && a.getY() == b.getY(); 
        }
        
        public void testDoubleArgumentConstructor(){
            // FloorMap map, Point currentLocation


            //Point currentLocation = new Point (5,6); 
            Point expectedLocation = new Point (5,6); 
            
            Kiva kivaTester; 
            
            kivaTester = new Kiva (defaultMap,new Point(5,6)); 
            
                System.out.println(kivaTester.getCurrentLocation()); 
                System.out.println(expectedLocation); 
                if(sameLocation( kivaTester.getCurrentLocation(), expectedLocation)){
                    System.out.println("success"); 
                }
                else{
                    System.out.println("fail"); 
                }
            
        
        }
}
