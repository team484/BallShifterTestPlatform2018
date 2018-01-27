package org.usfirst.frc.team484.robot.commands;

import org.usfirst.frc.team484.robot.Robot;
import org.usfirst.frc.team484.robot.RobotIO;

import edu.wpi.first.wpilibj.command.Command;

public class DriveUntilRate extends Command {
	
	private double speed, rate;
	
	public DriveUntilRate(double speed, double rate) {
		requires(Robot.drive);
		
		this.speed = speed;
		this.rate = rate;
	}

	@Override
	protected void execute() {
		Robot.drive.driveLinearInputs(speed, 0);
	}
	
	@Override
	protected boolean isFinished() {
		return RobotIO.leftEncoder.getRate() + RobotIO.rightEncoder.getRate() / 2.0 > rate * Math.signum(speed);
	}

}
