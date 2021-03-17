package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Shooter extends SubsystemBase{
    private WPI_TalonSRX m_lshoot;
    private WPI_TalonSRX m_rshoot;
    public Shooter(int lshoot , int rshoot ){
        m_lshoot = new WPI_TalonSRX(lshoot);
        m_rshoot = new WPI_TalonSRX(rshoot);
    }
    public void Shooting(){
        m_lshoot.set(Constants.l_shootSpeed);
        m_rshoot.set(Constants.r_shootSpeed);
    }
    public void StopShooting(){
        m_lshoot.set(0);
        m_rshoot.set(0);
    }
}