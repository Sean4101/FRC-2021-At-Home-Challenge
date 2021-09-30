package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class BaseDrive extends SubsystemBase {
    protected double baseSpeed;
    protected int speedChannel;

    public BaseDrive() {
        speedChannel = 0;
        baseSpeed = Constants.baseSpeedList[speedChannel];
    }

<<<<<<< HEAD
    public void TankDrive(double left_speed, double right_speed) {
        m_drive.tankDrive(left_speed*baseSpeed, right_speed*baseSpeed);
    }
    public void TankDriveRaw(double left_speed, double right_speed) {
        m_drive.tankDrive(left_speed, right_speed);
    }

=======
>>>>>>> cd492c4436918a75fce720b08e6ff5103e6e88c3
    public void SpeedUp() {
        speedChannel = speedChannel < Constants.baseSpeedList.length - 1 ? speedChannel + 1 : Constants.baseSpeedList.length - 1;
        baseSpeed = Constants.baseSpeedList[speedChannel];
    }

    public void SpeedDown() {
        speedChannel = speedChannel > 0 ? speedChannel - 1 : 0;
        baseSpeed = Constants.baseSpeedList[speedChannel];
    }

    public void TurnLeft(double speed) {
        if (speed<0) speed = -speed;
        if (speed>0.3) speed = 0.3;
        m_leftGroup.set(speed);
        m_rightGroup.set(speed);
    }
    
    public void TurnRight(double speed) {
        if (speed<0) speed = -speed;
        if (speed>0.2) speed = 0.2;
        m_rightGroup.set(-speed);
        m_leftGroup.set(-speed);
    }

    public void StopAuto(){
        m_leftGroup.set(0);
        m_rightGroup.set(0);
    }
}
