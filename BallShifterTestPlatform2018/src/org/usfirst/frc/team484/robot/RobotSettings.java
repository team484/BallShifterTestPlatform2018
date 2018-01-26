package org.usfirst.frc.team484.robot;

import edu.wpi.first.wpilibj.CounterBase.EncodingType;

/**
 * Stores all the magic numbers that should be kept out of code. MAGINC NUMBERS ARE BAD (so is caps lock).
 * This class should include various settings used by the robot such as encoder settings and buttons.
 * (Basically anything that isn't a physical port on the robot)
 */
public class RobotSettings {
	public static final double LEFT_ENCODER_DISTANCE_PER_PULSE = -0.03337939375 / 3.0;
	public static final double RIGHT_ENCODER_DISTANCE_PER_PULSE = 0.03337939375 / 3.0;
	public static final double LOW_GEAR_ROTATIONS_PER_ENCODER_PULSE = 10.42/256.0/3.0;
	public static final double HIGH_GEAR_ROTATIONS_PER_ENCODER_PULSE = 2.83/256.0/3.0;
	public static final EncodingType ENCODER_ENCODING_TYPE = EncodingType.k1X;
	
	
	public static final boolean INVERT_LEFT_MOTORS = true;
	public static final boolean INVERT_RIGHT_MOTORS = false;
	
	//the speed of the robot (in inches per second) that the threshold for shifting occurs
	public static final double SHIFTING_SPEED = 50;
	//the speed past the set shifting speed the robot must go before it shifts
	public static final double SHIFTING_DEADBAND = 2;
	
	//-----Joystick button map-----
	public static final int SHIFTER_MODE_SWITCH_BUTTON = 1;
	
	//-----Ports everything is plugged into----
	public static final int[] LEFT_DRIVE_MOTOR_IDS = {1,2,3};
	public static final int[] RIGHT_DRIVE_MOTOR_IDS = {4,5,6};
	
	public static final int LEFT_ENCODER_A_CHANNEL = 0;
	public static final int LEFT_ENCODER_B_CHANNEL = 1;
	public static final int RIGHT_ENCODER_A_CHANNEL = 2;
	public static final int RIGHT_ENCODER_B_CHANNEL = 3;
	
	public static final int TOP_GYRO_CHANNEL = 0;
	public static final int BOTTOM_GYRO_CHANNEL = 1;
	
	public static final int SHIFTER_SOLENOID_LOW_GEAR_PORT = 1;
	public static final int SHIFTER_SOLENOID_HIGH_GEAR_PORT = 0;
	
	public static final int DRIVE_STICK_PORT = 0;
}
