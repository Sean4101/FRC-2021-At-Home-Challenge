package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;

public class DoNothing extends CommandBase {
    public DoNothing() {}

    @Override
    public boolean isFinished() {
        return true;
    }
}
