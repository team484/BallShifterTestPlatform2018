package org.usfirst.frc.team484.robot.subsystems;

import org.team484.api.motion.ShifterDrive.ShifterMode;
import org.usfirst.frc.team484.robot.RobotIO;
import org.usfirst.frc.team484.robot.commands.DriveWithJoystick;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class DriveSS extends Subsystem {
	
	   public void initDefaultCommand() {
	        // Sets the default command
	        setDefaultCommand(new DriveWithJoystick());
	    }
	    
	    /**
	     * Drive at a given speed at rotation rate and square the input values. Squaring inputs allows for better
	     * control of the robot at a wider range of speeds. arcadeDrive does this by default.
	     * @param y - The forward/backward speed of the robot. [-1.0 to 1.0]
	     * @param rot - The rate the robot should turn [-1.0 to 1.0]
	     */
	    public void driveSquaredInputs(double y, double rot) {
	    	RobotIO.drive.arcadeDrive(y, rot);
	    }
	    
	    /**
	     * Drive the robot at a given speed without squaring input valyes. This is useful for PID loops which
	     * operate best when working with linear outputs.
	     * @param y - The forward/backward speed of the robot. [-1.0 to 1.0]
	     * @param rot - The rate the robot should turn [-1.0 to 1.0]
	     */
	    public void driveLinearInputs(double y, double rot) {
	    	RobotIO.drive.linearDrive(y, rot);
	    }
	    
	    /**
	     * Sets all drive speed controllers to 0. Good to use as a safety measure when the robot should not move.
	     */
	    public void doNothing() {
	    	RobotIO.drive.linearDrive(0, 0);
	    }
	    
	    /**
	     * Toggles the shifting mode of the drivetrain between Auto, Low, and High.
	     */
	    public void toggleShiftingMode() {
	    	switch(RobotIO.drive.getShifterMode()) {
	    	case AUTO:
	    		setShifterMode(ShifterMode.LOW);
	    		break;
	    	case LOW:
	    		setShifterMode(ShifterMode.HIGH);
	    		break;
	    	case HIGH:
	    		setShifterMode(ShifterMode.LOW);
	    		break;
	    	default:
	    		break;
	    	}
	    }
	    
	    /**
	     * Sets the shifting mode of the drivetrain to either Auto, Low, or High.
	     * @param mode - The mode to switch to.
	     */
	    public void setShifterMode(ShifterMode mode) {
	    	RobotIO.drive.setShifterMode(mode);
	    	SmartDashboard.putString("SHIFTING MODE: ", mode.toString());
	    }

}

