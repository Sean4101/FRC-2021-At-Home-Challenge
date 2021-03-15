package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Taking extends SubsystemBase{
    private final WPI_VictorSPX m_intake;
    public Taking(int intake){
        m_intake = new WPI_VictorSPX(intake);
    }

    public void take(){
        m_intake.set(-.85);
    }

    public void Stoptake(){
        m_intake.set(0);
    }

}