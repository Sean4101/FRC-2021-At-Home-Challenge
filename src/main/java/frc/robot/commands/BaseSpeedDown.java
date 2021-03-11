package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.BaseDrive;

public class BaseSpeedDown extends CommandBase {
    private final BaseDrive m_drive;

    public BaseSpeedDown(BaseDrive drive) {
        m_drive = drive;
    }

    @Override
    public void initialize() {
        m_drive.SpeedDown();
    }

    @Override
    public boolean isFinished() {
        return true;
    }
}
