package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.TheGreatCylinder;

public class CylinderPull extends CommandBase {
    private final TheGreatCylinder m_cylinder;

    public CylinderPull(TheGreatCylinder cylinder) {
        m_cylinder = cylinder;
        addRequirements(m_cylinder);
    }

    @Override
    public void initialize() {
        m_cylinder.Pull();
    }

    @Override
    public boolean isFinished() {
        return true;
    }
}
