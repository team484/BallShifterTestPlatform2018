package org.usfirst.frc.team484.robot.commands;

import org.usfirst.frc.team484.robot.Robot;
import org.usfirst.frc.team484.robot.RobotIO;

import edu.wpi.first.wpilibj.command.Command;

public class DriveUntilDistance extends Command {
	
	public static final double SETPOINT_EPSILON = 0.1;
	private double speed, distance;
	
	public DriveUntilDistance(double speed, double distance) {
		requires(Robot.drive);
		
		this.distance = distance;
		this.speed = Math.abs(speed);
	}
	
	@Override
	protected void execute() {
		double avg = RobotIO.leftEncoder.getDistance() + RobotIO.rightEncoder.getDistance() / 2.0;
		
		Robot.drive.driveLinearInputs(distance >= avg ? speed : -speed, 0);
	}

	@Override
	protected boolean isFinished() {
		double avg = RobotIO.leftEncoder.getDistance() + RobotIO.rightEncoder.getDistance() / 2.0;
		
		return avg >= distance - SETPOINT_EPSILON && avg <= distance + SETPOINT_EPSILON;
	}

}
