package org.usfirst.frc.team484.robot.commands;

import org.team484.api.motion.ShifterDrive.ShifterMode;
import org.usfirst.frc.team484.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class SetShifterMode extends Command {
	
	ShifterMode mode;
	
	public SetShifterMode(ShifterMode mode) {
		requires(Robot.drive);
		
		this.mode = mode;
	}
	
	@Override
	protected void initialize() {
		Robot.drive.setShifterMode(mode);
	}

	@Override
	protected boolean isFinished() {
		return true;
	}

}
