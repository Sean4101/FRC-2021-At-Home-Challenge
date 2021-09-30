package frc.robot.commands;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.BaseDrive;

public class AutoShoot extends CommandBase{
    private final BaseDrive m_autoshooter;

    boolean toggle = false;
    NetworkTableInstance inst;
    NetworkTable RPiTable;

    boolean inPosition = false;

    double centerX;
    double centerY;
    double area;

    public AutoShoot(BaseDrive autoshooter) {
        m_autoshooter = autoshooter;
        addRequirements(m_autoshooter);
        inst = NetworkTableInstance.getDefault();
        RPiTable = inst.getTable("RPi");
        
    }
    
    @Override
    public void initialize() { }

    @Override
    public void execute() {
        centerX = RPiTable.getEntry("center X").getDouble(0);
        centerY = RPiTable.getEntry("center Y").getDouble(0);
        area = RPiTable.getEntry("area").getDouble(0);
        
       
        
        if(area > 100){
            double speed = centerX;
            if (speed<0) speed = -speed;
            speed = Math.pow(speed, 0.5)*0.35;
            if(centerX >= .05){
                m_autoshooter.TurnRight(speed);
            }
            if(centerX <= -.05){
                m_autoshooter.TurnLeft(speed);
            }
        }
        else m_autoshooter.StopAuto();


    }
    
    
        

    @Override
    public void end(boolean interrupted){
        m_autoshooter.StopAuto();
    }
}
