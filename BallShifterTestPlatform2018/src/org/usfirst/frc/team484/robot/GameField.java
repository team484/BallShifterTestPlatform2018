package org.usfirst.frc.team484.robot;

import edu.wpi.first.wpilibj.DriverStation;

public class GameField {
	
	public boolean ourSwitchLeft;
	public boolean scaleLeft;
	public boolean theirSwitchLeft;
	
	static GameField getField() {
		String msg = DriverStation.getInstance().getGameSpecificMessage().toUpperCase();
		GameField field = new GameField();
		
		field.ourSwitchLeft = msg.charAt(0) == 'L';
		field.scaleLeft = msg.charAt(1) == 'L';
		field.theirSwitchLeft = msg.charAt(2) == 'L';
		
		return field;
	}
}
