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

    public void SpeedUp() {
        speedChannel = speedChannel < Constants.baseSpeedList.length - 1 ? speedChannel + 1 : Constants.baseSpeedList.length - 1;
        baseSpeed = Constants.baseSpeedList[speedChannel];
    }

    public void SpeedDown() {
        speedChannel = speedChannel > 0 ? speedChannel - 1 : 0;
        baseSpeed = Constants.baseSpeedList[speedChannel];
    }
}
