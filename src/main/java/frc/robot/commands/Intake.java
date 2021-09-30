package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Intaking;

public class Intake extends CommandBase{
    private final Intaking m_intaking;

    public Intake(Intaking intaking){
       m_intaking = intaking;
        addRequirements(m_intaking);
    }

    @Override public void initialize(){
        m_intaking.Intake();
    }
    @Override public void end(boolean interrupted) {
        m_intaking.StopIntake();
    }
}