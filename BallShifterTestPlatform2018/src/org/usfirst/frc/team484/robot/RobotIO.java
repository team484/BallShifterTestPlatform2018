package org.usfirst.frc.team484.robot;

import java.util.ArrayList;
import java.util.List;

import org.team484.api.motion.ShifterDrive;
import org.team484.api.motion.ShifterSolenoid;
import org.team484.api.motion.SpeedControllerGroup;
import org.team484.api.sensor.ShifterEncoder;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.Joystick;

public class RobotIO {
	public static SpeedControllerGroup leftMotors;
	public static SpeedControllerGroup rightMotors;
	
	public static ShifterSolenoid shifterSolenoid;

	public static ShifterEncoder leftEncoder;
	public static ShifterEncoder rightEncoder;
	
	public static ShifterDrive drive;
	
	public static Joystick driveJoystick;
	
	public RobotIO() {
		List<WPI_TalonSRX> leftTalons = new ArrayList<>();
		List<WPI_TalonSRX> rightTalons = new ArrayList<>();
		for (int i : RobotSettings.LEFT_DRIVE_MOTOR_IDS) {
			leftTalons.add(new WPI_TalonSRX(i));
		}
		for (int i : RobotSettings.RIGHT_DRIVE_MOTOR_IDS) {
			rightTalons.add(new WPI_TalonSRX(i));
		}
		leftMotors = new SpeedControllerGroup((WPI_TalonSRX[])leftTalons.toArray());
		rightMotors = new SpeedControllerGroup((WPI_TalonSRX[])rightTalons.toArray());
		
		shifterSolenoid = new ShifterSolenoid(RobotSettings.SHIFTER_SOLENOID_LOW_GEAR_PORT,
				RobotSettings.SHIFTER_SOLENOID_HIGH_GEAR_PORT);
		
		leftEncoder = new ShifterEncoder(RobotSettings.LEFT_ENCODER_A_CHANNEL, RobotSettings.LEFT_ENCODER_B_CHANNEL,
				false, RobotSettings.ENCODER_ENCODING_TYPE, shifterSolenoid);
		rightEncoder = new ShifterEncoder(RobotSettings.RIGHT_ENCODER_A_CHANNEL, RobotSettings.RIGHT_ENCODER_B_CHANNEL,
				false, RobotSettings.ENCODER_ENCODING_TYPE, shifterSolenoid);
		
		drive = new ShifterDrive(leftMotors, rightMotors, leftEncoder, rightEncoder);
				
		driveJoystick = new Joystick(RobotSettings.DRIVE_STICK_PORT);
		
		//-----------Apply Settings-----------
		leftEncoder.setDistancePerPulse(RobotSettings.LEFT_ENCODER_DISTANCE_PER_PULSE);
		rightEncoder.setDistancePerPulse(RobotSettings.RIGHT_ENCODER_DISTANCE_PER_PULSE);
		leftEncoder.setLowGearRPP(RobotSettings.LOW_GEAR_ROTATIONS_PER_ENCODER_PULSE);
		rightEncoder.setLowGearRPP(RobotSettings.LOW_GEAR_ROTATIONS_PER_ENCODER_PULSE);
		leftEncoder.setLowGearRPP(RobotSettings.HIGH_GEAR_ROTATIONS_PER_ENCODER_PULSE);
		rightEncoder.setLowGearRPP(RobotSettings.HIGH_GEAR_ROTATIONS_PER_ENCODER_PULSE);
		
		leftMotors.setInverted(RobotSettings.INVERT_LEFT_MOTORS);
		rightMotors.setInverted(RobotSettings.INVERT_LEFT_MOTORS);
		
		drive.setShiftingSpeed(RobotSettings.SHIFTING_SPEED);
		drive.setShiftingDeadband(RobotSettings.SHIFTING_DEADBAND);
	}
}
