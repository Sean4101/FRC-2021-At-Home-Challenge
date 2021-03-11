package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.BaseDrive;

public class BaseSpeedUp extends CommandBase {
    private final BaseDrive m_drive;

    public BaseSpeedUp(BaseDrive drive) {
        m_drive = drive;
    }

    @Override
    public void initialize() {
        m_drive.SpeedUp();
    }

    @Override
    public boolean isFinished() {
        return true;
    }
}
