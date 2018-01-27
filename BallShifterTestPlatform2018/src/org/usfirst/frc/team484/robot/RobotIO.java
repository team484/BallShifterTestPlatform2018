package org.usfirst.frc.team484.robot;

import java.util.ArrayList;
import java.util.List;

import org.team484.api.motion.ShifterDrive;
import org.team484.api.motion.ShifterSolenoid;
import org.team484.api.motion.SpeedControllerGroup;
import org.team484.api.sensor.DoubleGyro;
import org.team484.api.sensor.ShifterEncoder;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.AnalogGyro;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.SpeedController;

public class RobotIO {
	public static SpeedControllerGroup leftMotors;
	public static SpeedControllerGroup rightMotors;
	
	public static ShifterSolenoid shifterSolenoid;

	public static ShifterEncoder leftEncoder;
	public static ShifterEncoder rightEncoder;
	
	public static DoubleGyro gyro;
	
	public static ShifterDrive drive;
	
	public static Joystick driveJoystick;
	
	public static void initialize() {
		List<WPI_TalonSRX> leftTalons = new ArrayList<>();
		List<WPI_TalonSRX> rightTalons = new ArrayList<>();
		for (int i : RobotSettings.LEFT_DRIVE_MOTOR_IDS) {
			leftTalons.add(new WPI_TalonSRX(i));
		}
		for (int i : RobotSettings.RIGHT_DRIVE_MOTOR_IDS) {
			rightTalons.add(new WPI_TalonSRX(i));
		}
		WPI_TalonSRX[] leftTals = new WPI_TalonSRX[] {leftTalons.get(0), leftTalons.get(1), leftTalons.get(2)};
		WPI_TalonSRX[] rightTals = new WPI_TalonSRX[] {rightTalons.get(0), rightTalons.get(1), rightTalons.get(2)};
		leftMotors = new SpeedControllerGroup(leftTals);
		rightMotors = new SpeedControllerGroup(rightTals);
		
		shifterSolenoid = new ShifterSolenoid(RobotSettings.SHIFTER_SOLENOID_LOW_GEAR_PORT,
				RobotSettings.SHIFTER_SOLENOID_HIGH_GEAR_PORT);
		
		leftEncoder = new ShifterEncoder(RobotSettings.LEFT_ENCODER_A_CHANNEL, RobotSettings.LEFT_ENCODER_B_CHANNEL,
				false, RobotSettings.ENCODER_ENCODING_TYPE, shifterSolenoid);
		rightEncoder = new ShifterEncoder(RobotSettings.RIGHT_ENCODER_A_CHANNEL, RobotSettings.RIGHT_ENCODER_B_CHANNEL,
				false, RobotSettings.ENCODER_ENCODING_TYPE, shifterSolenoid);
		
		AnalogGyro topGyro = new AnalogGyro(RobotSettings.TOP_GYRO_CHANNEL);
		AnalogGyro bottomGyro = new AnalogGyro(RobotSettings.BOTTOM_GYRO_CHANNEL);
		
		gyro = new DoubleGyro(topGyro, bottomGyro);
		
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
		setVoltageCompMode(true);
	}
	
	public static void setVoltageCompMode(boolean shouldComp) {
		for (SpeedController talon : leftMotors.getControllers()) {
			((WPI_TalonSRX) talon).configVoltageCompSaturation(10.0, 10);
			((WPI_TalonSRX) talon).enableVoltageCompensation(shouldComp);
		}
		for (SpeedController talon : rightMotors.getControllers()) {
			((WPI_TalonSRX) talon).configVoltageCompSaturation(RobotSettings.VOLTAGE_TARGET, 10);
			((WPI_TalonSRX) talon).enableVoltageCompensation(shouldComp);
		}
	}
}
