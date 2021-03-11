package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class BaseDrive extends SubsystemBase {
    private WPI_VictorSPX m_lf;
    private WPI_VictorSPX m_lb;
    private WPI_VictorSPX m_rf;
    private WPI_VictorSPX m_rb;
    private SpeedControllerGroup m_leftGroup;
    private SpeedControllerGroup m_rightGroup;
    private DifferentialDrive m_drive;

    private double baseSpeed;
    private int speedChannel;

    public BaseDrive(int left_front, int left_back, int right_front, int right_back) {
        m_lf = new WPI_VictorSPX(left_front);
        m_lb = new WPI_VictorSPX(left_back);
        m_rf = new WPI_VictorSPX(right_front);
        m_rb = new WPI_VictorSPX(right_back);
        m_lf.setInverted(Constants.base_lf_reverse);
        m_lb.setInverted(Constants.base_lb_reverse);
        m_rf.setInverted(Constants.base_rf_reverse);
        m_rb.setInverted(Constants.base_rb_reverse);
        m_leftGroup = new SpeedControllerGroup(m_lf, m_lb);
        m_rightGroup = new SpeedControllerGroup(m_rf, m_rb);
        m_drive = new DifferentialDrive(m_leftGroup, m_rightGroup);

        speedChannel = 0;
        baseSpeed = Constants.baseSpeedList[speedChannel];
    }

    public void TankDrive(double left_speed, double right_speed) {
        m_drive.tankDrive(left_speed*baseSpeed, right_speed*baseSpeed);
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
