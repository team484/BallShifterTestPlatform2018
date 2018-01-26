package org.usfirst.frc.team484.robot.commands;

import org.usfirst.frc.team484.robot.Robot;
import org.usfirst.frc.team484.robot.RobotIO;

import edu.wpi.first.wpilibj.command.PIDCommand;

/**
 *
 */
public class TurnDegrees extends PIDCommand {
	
	// TODO: does this turn left or right?
	// TODO: PID values
	// TODO: tolerance
    public TurnDegrees(double degrees) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	super(0, 0, 0);
    	requires(Robot.drive);
    	
    	// Clear the gyros so 45 degrees to the right is actually 45 degrees to the right
    	RobotIO.gyro.reset();
    	
    	getPIDController().setPercentTolerance(2);
    	setSetpoint(degrees);
    }

	@Override
	protected double returnPIDInput() {
		// Wrap the continuous angles around so they're always between 0 and 360, so we don't spin multiple times
		// trying to get to zero
		return RobotIO.gyro.getAngle() % 360;
	}

	@Override
	protected void usePIDOutput(double output) {
		Robot.drive.driveLinearInputs(0, output);
	}

	@Override
	protected boolean isFinished() {
		// TODO: same thing as DriveDistance
		boolean onTarget = getPIDController().onTarget();
		boolean rateLow = RobotIO.leftEncoder.getRate() < 5 && RobotIO.rightEncoder.getRate() < 5;
		
		return onTarget && rateLow;
	}

}
