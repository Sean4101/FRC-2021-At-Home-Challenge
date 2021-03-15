package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Taking;


public class take extends CommandBase{
    private final Taking m_taking;

    public take(Taking taking){
       m_taking = taking;
        addRequirements(m_taking);
    }

    @Override public void initialize(){
        m_taking.take();
    }
    @Override public void end(boolean interrupted) {
        m_taking.Stoptake();
    }
}