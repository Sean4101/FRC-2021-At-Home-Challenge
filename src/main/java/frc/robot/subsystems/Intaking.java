package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Intaking extends SubsystemBase{
    private final WPI_VictorSPX m_intake;
    private final WPI_VictorSPX m_arm;
    
    public Intaking(int intake, int arm){
        m_intake = new WPI_VictorSPX(intake);
        m_arm = new WPI_VictorSPX(arm);
    }

    public void Intake(){
        m_intake.set(Constants.intakeSpeed);
    }

    public void StopIntake(){
        m_intake.set(0);
    }

    public void ArmUp(){
        m_arm.set(Constants.armUpSpeed);
    }

    public void ArmDown(){
        m_arm.set(Constants.armDownSpeed);
    }
    public void StopArm(){
        m_arm.set(0);
    }
}