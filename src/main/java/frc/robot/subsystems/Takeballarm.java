package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Takeballarm extends SubsystemBase{
    private final WPI_VictorSPX m_arm;

    public Takeballarm(int arm){
        m_arm = new WPI_VictorSPX(arm);
    }

    public void armup(){
        m_arm.set(.7);
    }

    public void armdown(){
        m_arm.set(-.8);
    }
    public void stoparm(){
        m_arm.set(0);
    }
}