/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team484.robot;

import org.team484.api.motion.ShifterDrive.ShifterMode;
import org.team484.api.util.Localizer;
import org.team484.api.util.RobotLogger;
import org.usfirst.frc.team484.robot.subsystems.DriveSS;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the TimedRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.properties file in the
 * project.
 */
public class Robot extends TimedRobot {
	public static final DriveSS drive
			= new DriveSS();
	public static RobotIO robotIO;
	public static OI oi;
	public static GameField field;
	
	private static RobotLogger logger;

	@Override
	public void robotInit() {
		Localizer.getInstance();
		
		RobotIO.initialize();
		RobotIO.gyro.calibrate();
		RobotIO.drive.setShifterMode(ShifterMode.LOW);
		
		logger = new RobotLogger(10);
		logger.log("Left Encoder", RobotIO.leftEncoder);
		logger.log("Right Encoder", RobotIO.rightEncoder);
		logger.log("Left Speed Controller", RobotIO.leftMotors);
		logger.log("Right Speed Controller", RobotIO.rightMotors);
		logger.log("Timestamp", new Timer());
		
		oi = new OI();
		field = GameField.getField();
		setPeriod(0.01);
	}
	
	@Override
	public void autonomousInit() {
		RobotIO.gyro.reset();
	}
	
	@Override
	public void teleopInit() {
		RobotIO.gyro.reset();
		logger.start();
	}
	
	@Override
	public void disabledInit() {
		logger.interrupt();
	}

	@Override
	public void disabledPeriodic() {
		Scheduler.getInstance().run();
	}

	@Override
	public void autonomousPeriodic() {
		Scheduler.getInstance().run();
	}

	@Override
	public void teleopPeriodic() {
		Scheduler.getInstance().run();
	}
	
	@Override
	public void testInit() {
	}

	@Override
	public void testPeriodic() {
	}
	
	/**
	 * Logs robot state information to the smart dashboard. This should be called periodically at all times.
	 */
	private static void logRobotState() {
		SmartDashboard.putBoolean("Is Low Gear", RobotIO.shifterSolenoid.isLowGear());
		SmartDashboard.putNumber("Left Motor RPM", RobotIO.leftEncoder.getRPM());
		SmartDashboard.putNumber("Right Motor RPM", RobotIO.rightEncoder.getRPM());
		double speed = Math.max(Math.abs(RobotIO.leftEncoder.getRate()), Math.abs(RobotIO.rightEncoder.getRate()));
		SmartDashboard.putNumber("Speed IPS", speed); //Inches per Second
		SmartDashboard.putNumber("Speed FPS", speed / 12.0); //Feet per Second
		SmartDashboard.putNumber("Speed MPH", speed / 17.6); //Miles per Hour
		SmartDashboard.putNumber("Left Encoder Distance", RobotIO.leftEncoder.getDistance());
		SmartDashboard.putNumber("Right Encoder Distance", RobotIO.rightEncoder.getDistance());
		SmartDashboard.putNumber("X Pos", Localizer.getInstance().getState().x);
		SmartDashboard.putNumber("Y Pos", Localizer.getInstance().getState().y);
		SmartDashboard.putNumber("Rot", Localizer.getInstance().getState().rot);
	}
	
	@Override
	public void robotPeriodic() {
		logRobotState();
		Localizer.getInstance().run(RobotIO.leftEncoder, RobotIO.rightEncoder, RobotIO.gyro);
	}
}
