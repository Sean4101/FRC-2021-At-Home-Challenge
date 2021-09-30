package frc.robot.subsystems;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Shooter extends SubsystemBase{
    public WPI_TalonSRX m_lshoot;
    public WPI_TalonSRX m_rshoot;
    public Shooter(int lshoot , int rshoot ){
        m_lshoot = new WPI_TalonSRX(lshoot);
        m_rshoot = new WPI_TalonSRX(rshoot);
    }
    public void Shooting(){
        /*m_lshoot.set(ControlMode.Velocity, 5000);
        m_rshoot.set(ControlMode.Velocity, -5000); */
        m_lshoot.set(.8);
        m_rshoot.set(-.7);
    }
    public void StopShooting(){
        m_lshoot.set(0);
        m_rshoot.set(0);
    }

    public void Rshoot(){
        m_lshoot.set(-.8);
        m_rshoot.set(.7);
    }
}