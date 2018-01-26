package org.team484.api.util;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.GyroBase;

public class Localizer {
	private State robotState;
	private static Localizer instance;
	
	private double leftEncLast, rightEncLast;
	
	public static Localizer getInstance() {
		if (instance == null) {
			instance = new Localizer();
		}
		return instance;
	}
	
	private Localizer() {
		robotState = new State();
	}
	
	public void reset() {
		robotState.reset();
	}
	
	public void run(Encoder leftEnc, Encoder rightEnc, GyroBase gyro) {
		double leftEncDistance = leftEnc.getDistance();
		double rightEncDistance = rightEnc.getDistance();
		double gyroAngle = Math.toRadians(gyro.getAngle() + 90.0);
		
		double leftEncDelta = leftEncDistance - leftEncLast;
		double rightEncDelta = rightEncDistance - rightEncLast;
		leftEncLast = leftEncDistance;
		rightEncLast = rightEncDistance;
		
		double delta = (leftEncDelta + rightEncDelta) / 2.0;
		
		double deltaX = delta * Math.cos(gyroAngle);
		double deltaY = delta * Math.sin(gyroAngle);
		
		robotState.x += deltaX;
		robotState.y += deltaY;
		robotState.rot = Math.toDegrees(gyroAngle);
		
	}
	
	public State getState() {
		return robotState.copy();
	}
	
	public static class State {
		public double x,y,rot;
		
		public State() { }
		public State(double x, double y, double rot) {
			this.x = x;
			this.y = y;
			this.rot = rot;
		}
		
		public void reset() {
			x = y = rot = 0;
		}
		
		public State copy() {
			return new State(x,y,rot);
		}
	}
}
