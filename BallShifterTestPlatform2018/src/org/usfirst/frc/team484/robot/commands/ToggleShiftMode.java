package org.usfirst.frc.team484.robot.commands;

import org.usfirst.frc.team484.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 * Toggles the drivetrain between Auto, Low gear, and High gear modes. This command instantly terminates.
 */
public class ToggleShiftMode extends Command {

    public ToggleShiftMode() {
        requires(Robot.kdriveSS);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    		Robot.kdriveSS.toggleShiftingMode();
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() { return true; }

}
