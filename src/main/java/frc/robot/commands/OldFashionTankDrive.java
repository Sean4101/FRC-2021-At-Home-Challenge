package frc.robot.commands;

import java.util.function.DoubleSupplier;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.BaseDrive;

public class OldFashionTankDrive extends CommandBase {
    private final BaseDrive m_drive;
    private final DoubleSupplier m_leftSpeed;
    private final DoubleSupplier m_rightSpeed;

    public OldFashionTankDrive(BaseDrive drive, DoubleSupplier leftSpeed, DoubleSupplier rightSpeed) {
        m_drive = drive;
        m_leftSpeed = leftSpeed;
        m_rightSpeed = rightSpeed;
    }

    @Override
    public void execute() {
        m_drive.TankDrive(m_leftSpeed.getAsDouble(), m_rightSpeed.getAsDouble());
    }
}
