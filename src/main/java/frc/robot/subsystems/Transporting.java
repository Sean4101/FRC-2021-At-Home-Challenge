package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Transporting extends SubsystemBase{
    private final WPI_VictorSPX m_ballsSlapper;
    public Transporting(int ballsSlapper){
        m_ballsSlapper = new WPI_VictorSPX(ballsSlapper);
    }

    public void Slap() {
        m_ballsSlapper.set(Constants.slapSpeed);
    }

    public void StopSlapping() {
        m_ballsSlapper.set(0);
    }
}
