package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class StoreBall extends SubsystemBase{
    private final WPI_VictorSPX m_lazySusan;
    public StoreBall(int lazySusan){
        m_lazySusan = new WPI_VictorSPX(lazySusan);
    }

    public void Spin(){
        m_lazySusan.set(.4);
    }

    public void StopSpin(){
        m_lazySusan.set(0);
    }

    public void Reverse(){
        m_lazySusan.set(-.4);
    }

}