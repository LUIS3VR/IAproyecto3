package robot;

import java.util.Stack;

import lejos.nxt.ColorSensor;
import lejos.nxt.Motor;
import lejos.nxt.NXTRegulatedMotor;
import lejos.nxt.SensorPort;

public class Solver {
	
	// ORIENTATION CONSTANTS
	private final String NORTH = "NORTH";
	private final String SOUTH = "SOUTH";
	private final String WEST = "WEST";
	private final String EAST = "EAST";
	
	// MOTORS
	private NXTRegulatedMotor leftMotor = Motor.A;
	private NXTRegulatedMotor rightMotor = Motor.C;
	
	// SENSORS
	private ColorSensor leftSensor;
	private ColorSensor rightSensor;
	
	// LECTURES
	int leftLecture;
	int rightLecture;
	
	// TRANSITIONS SET
	private Stack instructionSet;
	
	// PUSHES SET
	private Stack pushes;
	
	// ROBOT'S ORIENTATION
	private String orientation = NORTH;
	
	// ENVIRONMENT VARIABLES
	final float LINE = 130;
	final float FLOOR = 300;
	

	
	/*
	 *	CONSTRUCTOR
	 */
	public Solver(String[] instructions, String[] pushes){
		this.leftSensor = new ColorSensor(SensorPort.S1);
		this.rightSensor = new ColorSensor(SensorPort.S4);
		this.instructionSet = new Stack();
		for (int i = instructions.length-1; i >= 0; i--) {
			this.instructionSet.addElement(instructions[i]);
        }
		this.pushes = new Stack();
		for (int i = pushes.length-1; i >=0; i--) {
			this.pushes.addElement(pushes[i]);
		}
	}//end Solver

	public static void main(String[] args){
		String[] instruction = new String[]{"RIGHT", "DOWN", "LEFT" , "DOWN" , "DOWN" , "RIGHT", "RIGHT", "UP"   , "LEFT", "DOWN" , "LEFT" , "UP"  };
		String[] pushes = new String[]     {"false", "true", "false", "false", "false", "false", "false", "false", "true", "false", "false", "true"};
		
		Solver mySolver = new Solver(instruction, pushes);
		mySolver.solve();
	}//end main

	public void solve(){			
			while(!instructionSet.empty()){
				// WHEN FACING NORTH
				if(this.orientation.equals(NORTH) && instructionSet.peek().equals("UP")){
					forward(pushes.pop());
					instructionSet.pop();
				}
				if(this.orientation.equals(NORTH) && instructionSet.peek().equals("DOWN")){
					orientation = SOUTH;
					turnAround();
					forward(pushes.pop());
					instructionSet.pop();
				}
				if(this.orientation.equals(NORTH) && instructionSet.peek().equals("LEFT")){
					orientation = WEST;
					turnLeft();
					forward(pushes.pop());
					instructionSet.pop();
				}
				if(this.orientation.equals(NORTH) && instructionSet.peek().equals("RIGHT")){
					orientation = EAST;
					turnRight();
					forward(pushes.pop());
					instructionSet.pop();
				}
				
				// WHEN FACING SOUTH
				if(this.orientation.equals(SOUTH) && instructionSet.peek().equals("UP")){
					orientation = NORTH;
					turnAround();
					forward(pushes.pop());
					instructionSet.pop();
				}
				if(this.orientation.equals(SOUTH) && instructionSet.peek().equals("DOWN")){
					forward(pushes.pop());
					instructionSet.pop();
				}
				if(this.orientation.equals(SOUTH) && instructionSet.peek().equals("LEFT")){
					orientation = WEST;
					turnRight();
					forward(pushes.pop());
					instructionSet.pop();
				}
				if(this.orientation.equals(SOUTH) && instructionSet.peek().equals("RIGHT")){
					orientation = EAST;
					turnLeft();
					forward(pushes.pop());
					instructionSet.pop();
				}
				
				// WHEN FACING EAST ->
				if(this.orientation.equals(EAST) && instructionSet.peek().equals("UP")){
					orientation = NORTH;
					turnLeft();
					forward(pushes.pop());
					instructionSet.pop();
				}
				if(this.orientation.equals(EAST) && instructionSet.peek().equals("DOWN")){
					orientation = SOUTH;
					turnRight();
					forward(pushes.pop());
					instructionSet.pop();
				}
				if(this.orientation.equals(EAST) && instructionSet.peek().equals("LEFT")){
					orientation = WEST;
					turnAround();
					forward(pushes.pop());
					instructionSet.pop();
				}
				if(this.orientation.equals(EAST) && instructionSet.peek().equals("RIGHT")){
					forward(pushes.pop());
					instructionSet.pop();
				}
		
				// WHEN FACING WEST <-
				if(this.orientation.equals(WEST) && instructionSet.peek().equals("UP")){
					orientation = NORTH;
					turnRight();
					forward(pushes.pop());
					instructionSet.pop();
				}
				if(this.orientation.equals(WEST) && instructionSet.peek().equals("DOWN")){
					orientation = SOUTH;
					turnLeft();
					forward(pushes.pop());
					instructionSet.pop();
				}
				if(this.orientation.equals(WEST) && instructionSet.peek().equals("LEFT")){
					forward(pushes.pop());
					instructionSet.pop();
				}
				if(this.orientation.equals(WEST) && instructionSet.peek().equals("RIGHT")){
					orientation = EAST;
					turnAround();
					forward(pushes.pop());
					instructionSet.pop();
				}
			}//end while		
	}//end solve
	
	public void turnLeft(){
		leftMotor.rotate(-320,true);
		rightMotor.rotate(320);	
	}//end turnLeft
	
	public void turnRight(){
		leftMotor.rotate(320,true);
		rightMotor.rotate(-340);	
	}//end turnRight
	
	public void turnAround(){
		leftMotor.rotate(620,true);
		rightMotor.rotate(-640);
	}//end turnAround
	
	public void forward(Object push){
		leftLecture = this.leftSensor.getRawLightValue();
		rightLecture = this.rightSensor.getRawLightValue();
		
		boolean moving = true;
		while(moving){
			// the first we do is read the ground
			leftLecture = this.leftSensor.getRawLightValue();
			rightLecture = this.rightSensor.getRawLightValue();
			
			// if we are getting off the road
			if (leftLecture <= FLOOR) {	
				leftMotor.rotate(30, true);
				rightMotor.rotate(30);
				if (rightLecture > FLOOR) {
					adjust("LEFT");					
				}
			}
			if (rightLecture <= FLOOR) {				
				leftMotor.rotate(30, true);
				rightMotor.rotate(30);
				if (leftLecture > FLOOR) {
					adjust("RIGHT");					
				}			
			}
			
			// we test if we are on a cross
			if (leftLecture <= FLOOR && rightLecture <= FLOOR) {
				//lejos.nxt.Sound.twoBeeps();
				//if we are pushing we need to go forward to the next cross
				if (push.equals("true")) {
					leftMotor.rotate(250,true);
					rightMotor.rotate(250);       
					push();
				} else {
					// move wheels to cross
					leftMotor.rotate(250,true);
					rightMotor.rotate(250);
					leftMotor.stop(true);
					rightMotor.stop();					
				}
				moving = false;
			}		
			
			leftMotor.forward();
			rightMotor.forward();	
		}//end while
	}//end forward

	private void push(){
		leftLecture = this.leftSensor.getRawLightValue();
		rightLecture = this.rightSensor.getRawLightValue();
		if (this.orientation.equals(NORTH)) {
			//lejos.nxt.Sound.beep();
			orientation = SOUTH;
		}
		if (this.orientation.equals(SOUTH)) {
			//lejos.nxt.Sound.beep();
			orientation = NORTH;
		}
		if (this.orientation.equals(EAST)) {
			lejos.nxt.Sound.beep();
			orientation = WEST;
		}
		if (this.orientation.equals(WEST)) {
			//lejos.nxt.Sound.beep();
			orientation = EAST;
		}
		boolean pushing = true;
		boolean returning = false;
		while(pushing){
			// the first we do is read the ground
			leftLecture = this.leftSensor.getRawLightValue();
			rightLecture = this.rightSensor.getRawLightValue();
			
			// if we are getting off the road
			if (leftLecture <= FLOOR) {	
				leftMotor.rotate(30, true);
				rightMotor.rotate(30);
				if (rightLecture > FLOOR) {
					adjust("LEFT");					
				}
			}
			if (rightLecture <= FLOOR) {				
				leftMotor.rotate(30, true);
				rightMotor.rotate(30);
				if (leftLecture > FLOOR) {
					adjust("RIGHT");					
				}			
			}
			
			// we test if we are on a cross
			if (leftLecture <= FLOOR && rightLecture <= FLOOR) {
				// reverse to 'turn around' position
				leftMotor.rotate(-240,true);
				rightMotor.rotate(-240);
				turnAround();
				pushing = false;
				returning = true;
			}
			leftMotor.forward();
			rightMotor.forward();			
		}//end pushing while
		
		while(returning){
			// the first we do is read the ground
			leftLecture = this.leftSensor.getRawLightValue();
			rightLecture = this.rightSensor.getRawLightValue();
			
			// if we are getting off the road
			if (leftLecture <= FLOOR) {	
				leftMotor.rotate(30, true);
				rightMotor.rotate(30);
				if (rightLecture > FLOOR) {
					adjust("LEFT");					
				}
			}
			if (rightLecture <= FLOOR) {				
				leftMotor.rotate(30, true);
				rightMotor.rotate(30);
				if (leftLecture > FLOOR) {
					adjust("RIGHT");					
				}			
			}
			
			// we test if we are on a cross
			if (leftLecture <= FLOOR && rightLecture <= FLOOR) {
				returning = false;
			}
			leftMotor.forward();
			rightMotor.forward();			
		}//end returning while
	}//end push
	
	private void adjust(String direction){
		if(direction.equals("LEFT")){
			leftMotor.rotate(-30,true);
			rightMotor.rotate(30);			
		}
		if (direction.equals("RIGHT")) {
			leftMotor.rotate(30,true);
			rightMotor.rotate(-30);		
		}
	}//end adjust
}//end class