package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Transporting;

public class Slap extends CommandBase {
    private final Transporting m_transporting;

    public Slap(Transporting transporting) {
        m_transporting = transporting;
        addRequirements(m_transporting);
    }

    @Override
    public void initialize() {
        m_transporting.Slap();
    }

    @Override
    public void end(boolean interrupted) {
        m_transporting.StopSlapping();
    }
}
