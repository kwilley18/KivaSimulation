
/**
 * Write a description of Kiva here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import edu.duke.*;
import java.util.*;
import edu.duke.Point; 

public class Kiva {
    
    public Point currentLocation; 
    public FacingDirection directionFacing;
    private long motorLifeTime; 
    FloorMap map; 
    private boolean carryingPod; 
    private boolean successfullyDropped; 
    private int motorLifetime; 
    
    // two constructors 
    public Kiva(FloorMap map){
        this.map = map; 
        this.currentLocation = map.getInitialKivaLocation(); 
        this.directionFacing = FacingDirection.UP; 
        this.carryingPod = false; 
        this.successfullyDropped = false; 
        this.motorLifetime = 0;  

    }
    public Kiva(FloorMap map, Point currentPoint){
        this.map = map; 
        this.currentLocation = currentPoint; 
        this.directionFacing = FacingDirection.UP; 
        this.carryingPod = false; 
        this.successfullyDropped = false; 
        this.motorLifetime = 0; 
    }
   
    
    // getters
    public long getMotorLifetime(){
        return this.motorLifeTime; 
    }
    public boolean isCarryingPod(){
          return this.carryingPod; 
    }
    public boolean isSuccessfullyDropped(){
        return this.successfullyDropped; 
   
    }
    public Point getCurrentLocation(){
        return this.currentLocation; 
    }
    
    // main methods 
    public void move(KivaCommand command){
        
        if(command.getDirectionKey() == 'F'){
            this.moveForward(); 
        }
        else  if(command.getDirectionKey() == 'L'){
            this.turnLeft(); 
        }
        else if(command.getDirectionKey() == 'R'){
            this.turnRight(); 
        }
        else if(command.getDirectionKey() == 'D'){
            this.dropPod(); 
            
        }
        else if(command.getDirectionKey() == 'T'){
            this.takePod(); 
        }

    }
    
    // helper methods 
    private boolean sameLocation(Point a, Point b) {
        return a.getX() == b.getX() && a.getY() == b.getY();
    }
    public void takePod(){
            /*System.out.println(this.currentLocation); 
            System.out.println(map.getPodLocation()); */ 
        if(sameLocation(this.currentLocation,map.getPodLocation())){
              this.carryingPod = true; 
              this.successfullyDropped = false; 
         }
        else{
             throw new NoPodException("Cannot take nonexsistent pod from location " + this.currentLocation); 
        }
    }
    public void dropPod(){
        if(sameLocation(this.currentLocation,map.getDropZoneLocation()) && this.successfullyDropped == false){
            this.successfullyDropped = true;
            this.carryingPod = false; 
            System.out.println(this.getMotorLifetime()); 
            System.out.println("Successfully picked up the pod and dropped it off. Thank you!"); 
            
        }

        else{
            throw new IllegalDropZoneException("Cannot drop pods willy-nilly at " + this.currentLocation); 
        }

    }
    public void moveForward(){
        //RFFFFFFLFFFTRFFRFFFD
        int x = this.currentLocation.getX(); 
        int y = this.currentLocation.getY(); 
        int maxRow = map.getMaxRowNum(); 
        int maxCol = map.getMaxColNum(); 
            /*System.out.println("Forward:"); 
           System.out.println(" Previous Location" + this.getCurrentLocation()); 
           System.out.println(this.directionFacing);  
            System.out.println("before x: "+ x); 
                System.out.println("before y: "+ y);*/ 
        if( this.directionFacing == FacingDirection.UP){
            y--; 

        }
        else if(this.directionFacing == FacingDirection.LEFT){
            x--; 
        }
        else if(this.directionFacing == FacingDirection.RIGHT){
            x++; 
        }
        else if(this.directionFacing == FacingDirection.DOWN){
            y++; 
        }
       // System.out.println(map.getObjectAtLocation(this.currentLocation)); 
       //System.out.println(map.getObjectAtLocation(this.currentLocation)); 
            // System.out.println(map.getObjectAtLocation(new Point (1,0))); 
        /*System.out.println("after x: "+ x); 
                System.out.println("after y: "+ y); 
                        System.out.println("col: "+ maxCol); 
                                System.out.println("row: "+ maxRow); */ 
        
        this.currentLocation = new Point (x,y); 
          if(map.getObjectAtLocation(this.currentLocation)==FloorMapObject.OBSTACLE){//if(map.getObjectAtLocation(this.currentLocation) == FloorMapObject.OBSTACLE){
            throw new IllegalMoveException("Can't move onto an obstacle at" + this.currentLocation); 
        }
        if( y < 0 || x < 0 || x > maxCol || y > maxRow)  
        throw new IllegalMoveException("Out of Bounds at " + this.currentLocation); 
   
        if(sameLocation(this.currentLocation,map.getPodLocation())){
            this.carryingPod = true; 
        }
        
        /*if(sameLocation(this.currentLocation, map.getDropZoneLocation())){
            this.successfullyDropped = true; 
        } */ 
    }
    
    public void turnLeft(){
            
        int x = this.currentLocation.getX(); 
        int y = this.currentLocation.getY(); 
        int maxRow = map.getMaxRowNum(); 
        int maxCol = map.getMaxColNum(); 
        /*System.out.println("Left"); 
           System.out.println("Previous Location" + this.getCurrentLocation()); 
           System.out.println(this.directionFacing); */ 
           /*RRFFFLFFFFLFFFRFT
            * R
            * */ 
            
        if(this.successfullyDropped == false){
            if( this.directionFacing == FacingDirection.UP){
              this.directionFacing = FacingDirection.LEFT;  
            }
            else if(this.directionFacing == FacingDirection.LEFT){
              this.directionFacing = FacingDirection.DOWN; //y++; 
            }
            else if(this.directionFacing == FacingDirection.RIGHT){
              this.directionFacing = FacingDirection.UP;// y--; 
            }
            else if(this.directionFacing == FacingDirection.DOWN){
              this.directionFacing = FacingDirection.RIGHT; //x++; 
        }
        

        }
        
         this.currentLocation = new Point (x,y); 
       this.motorLifeTime += 1000; 
        if( y < 0 || x < 0 || x > maxCol || y > maxRow)  throw new IllegalMoveException("Out of Bounds at " + 
        this.currentLocation); 
        
        
        if(sameLocation(this.currentLocation,map.getPodLocation())){
            this.carryingPod = true; 
        }
        
        if(sameLocation(this.currentLocation, map.getDropZoneLocation())){
            this.successfullyDropped = true; 
        } 
                  //System.out.println("After" + y); 

        //System.out.println("New Location" + this.getCurrentLocation()); 
    }
    
    public void turnRight(){
             
        int x = this.currentLocation.getX(); 
        int y = this.currentLocation.getY(); 
        int maxRow = map.getMaxRowNum(); 
        int maxCol = map.getMaxColNum(); 
        
        if(this.successfullyDropped == false){
            if( this.directionFacing == FacingDirection.UP){
            directionFacing = FacingDirection.RIGHT; 
            // x++; 
            }
            else if(this.directionFacing == FacingDirection.LEFT){
                directionFacing = FacingDirection.UP; 
               // y--; 
            }
            else if(this.directionFacing == FacingDirection.RIGHT){
                directionFacing = FacingDirection.DOWN; 
                //y++; 
            }
            else if(this.directionFacing == FacingDirection.DOWN){
                directionFacing = FacingDirection.LEFT; 
               // x--; 
            }
        }
     
       this.currentLocation = new Point (x,y); 
       this.motorLifeTime += 1000; 
        if( y < 0 || x < 0 || x > maxCol || y > maxRow)  throw new IllegalMoveException("Out of Bounds"); 
             
         
        if(sameLocation(this.currentLocation,map.getPodLocation())){
            this.carryingPod = true; 
        }
        
        if(sameLocation(this.currentLocation, map.getDropZoneLocation())){
            this.successfullyDropped = true; 
        } 
       
    }
}
