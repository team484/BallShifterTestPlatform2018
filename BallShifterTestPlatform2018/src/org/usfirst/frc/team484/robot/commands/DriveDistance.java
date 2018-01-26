package org.usfirst.frc.team484.robot.commands;

import org.usfirst.frc.team484.robot.Robot;
import org.usfirst.frc.team484.robot.RobotIO;

import edu.wpi.first.wpilibj.command.PIDCommand;

/**
 *
 */
public class DriveDistance extends PIDCommand {

	// TODO: PID values
    public DriveDistance(double distance) {
    	super(0, 0, 0);
    	requires(Robot.drive);
    	
    	RobotIO.leftEncoder.reset();
    	RobotIO.rightEncoder.reset();
    	
    	setSetpoint(distance);
    	getPIDController().setPercentTolerance(2);
    }

	@Override
	protected double returnPIDInput() {
		// Average left and right for a more accurate measurement
		return (RobotIO.leftEncoder.pidGet() + RobotIO.rightEncoder.pidGet()) / 2;
	}

	@Override
	protected void usePIDOutput(double output) {
		Robot.drive.driveLinearInputs(output, 0);
	}

	@Override
	protected boolean isFinished() {
		// Command is finished when the PID loop is near the setpoint and the rates of the encoders are low
		// so that the command doesn't finish as soon as the threshold is met
		boolean onTarget = getPIDController().onTarget();
		// TODO
		boolean rateLow = RobotIO.leftEncoder.getRate() < 5 && RobotIO.rightEncoder.getRate() < 5;
		
		return onTarget && rateLow;
	}

}
