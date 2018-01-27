/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team484.robot;


import org.usfirst.frc.team484.robot.commands.FloorItTest;
import org.usfirst.frc.team484.robot.commands.ToggleShiftMode;

import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

/**
 * This class is used to define joystick buttons and the actions the robot should take when any button is
 * pressed, held, or released.
 */
public class OI {
	Button toggleShiftModeButton = new JoystickButton(RobotIO.driveJoystick, RobotSettings.SHIFTER_MODE_SWITCH_BUTTON);
	Button floorItButton = new JoystickButton(RobotIO.driveJoystick, 2);
	
	public OI() {
		toggleShiftModeButton.whenPressed(new ToggleShiftMode());
		floorItButton.whenPressed(new FloorItTest(0.8, 120));
	}
}
