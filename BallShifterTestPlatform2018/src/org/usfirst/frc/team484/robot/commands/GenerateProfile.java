package org.usfirst.frc.team484.robot.commands;

import org.team484.api.util.ProfileLogger;
import org.usfirst.frc.team484.robot.Robot;
import org.usfirst.frc.team484.robot.RobotIO;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class GenerateProfile extends Command {
	ProfileLogger logger;
	boolean isFinished = false;
	double output = 0;
	double targetDistance = 100;
    public GenerateProfile() {
        // Use requires() here to declare subsystem dependencies
        requires(Robot.drive);
        logger = new ProfileLogger(10, "80_percent") {
			
			@Override
			public double getRate() {
				return (RobotIO.leftEncoder.getRate() + RobotIO.rightEncoder.getRate()) / 2.0;
			}
			
			@Override
			public double getOutput() {
				return output;
			}
			
			@Override
			public double getDistance() {
				return (RobotIO.leftEncoder.getDistance() + RobotIO.rightEncoder.getDistance()) / 2.0;
			}
		};
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	output = 0;
    	isFinished = false;
    	logger.start();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    		double displacement = (RobotIO.leftEncoder.getDistance() + RobotIO.rightEncoder.getDistance()) / 2.0;
    		if (displacement < targetDistance) {
    			Robot.drive.driveLinearInputs(0.8, 0);
    		} else if ((RobotIO.leftEncoder.getRate() + RobotIO.rightEncoder.getRate()) / 2.0 > 0) {
    			Robot.drive.driveLinearInputs(-0.8, 0); 
    		} else {
    			isFinished = true;
    		}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	logger.interrupt();
        return isFinished;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
