package robot;

import lejos.nxt.Button;
import lejos.nxt.ColorSensor;
import lejos.nxt.LCD;
import lejos.nxt.SensorPort;

public class Calibrator {

	public static void main(String[] args) {
		ColorSensor leftSensor = new ColorSensor(SensorPort.S1);
		ColorSensor rightSensor = new ColorSensor(SensorPort.S4);
		while (!Button.ESCAPE.isDown()){
			LCD.drawString("LEFT : "+leftSensor.getRawLightValue(), 0, 0);
			LCD.drawString("RIGHT : "+rightSensor.getRawLightValue(), 0, 1);
		}
	}
	
}