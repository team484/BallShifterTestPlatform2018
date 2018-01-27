package org.usfirst.frc.team484.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 * yes
 */
public class FloorItTest extends CommandGroup {

    public FloorItTest(double speed, double distance) {
//        addSequential(new SetShifterMode(mode));
        addSequential(new DriveUntilDistance(speed, distance));
        addSequential(new DriveUntilRate(-speed, 0));
    }
}
