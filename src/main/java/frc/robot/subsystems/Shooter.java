package frc.robot.subsystems;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

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
<<<<<<< HEAD
        /*m_lshoot.set(ControlMode.Velocity, 5000);
        m_rshoot.set(ControlMode.Velocity, -5000); */
        m_lshoot.set(.8);
        m_rshoot.set(-.7);
=======
        m_lshoot.set(Constants.l_shootSpeed);
        m_rshoot.set(Constants.r_shootSpeed);
>>>>>>> cd492c4436918a75fce720b08e6ff5103e6e88c3
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