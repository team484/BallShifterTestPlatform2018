package org.usfirst.frc.team484.robot.commands;

import org.usfirst.frc.team484.robot.Robot;
import org.usfirst.frc.team484.robot.RobotIO;

import edu.wpi.first.wpilibj.command.Command;

/**
 * Drives the robot using input from the driver joystick.
 */
public class DriveWithJoystick extends Command {

    public DriveWithJoystick() {
        requires(Robot.drive);
    }

    // Called repeatedly when this Command is scheduled to run
    @Override
	protected void execute() {
    	Robot.drive.driveSquaredInputs(-RobotIO.driveJoystick.getY(), RobotIO.driveJoystick.getX());
    }

    // Make this return true when this Command no longer needs to run execute()
    @Override
	protected boolean isFinished() { return false; }

    // Called once after isFinished returns true
    @Override
	protected void end() {
    	Robot.drive.doNothing();
    }
}
