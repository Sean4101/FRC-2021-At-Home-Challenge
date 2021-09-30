package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import frc.robot.Constants;

public class DifferentialBaseDrive extends BaseDrive {
    
    private final WPI_VictorSPX m_lf;
    private final WPI_VictorSPX m_lb;
    private final WPI_VictorSPX m_rf;
    private final WPI_VictorSPX m_rb;
    private final SpeedControllerGroup m_leftGroup;
    private final SpeedControllerGroup m_rightGroup;
    private final DifferentialDrive m_drive;

    public DifferentialBaseDrive(int left_front, int left_back, int right_front, int right_back) {
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
    }

    public void TankDrive(double left_speed, double right_speed) {
        m_drive.tankDrive(left_speed*baseSpeed, right_speed*baseSpeed);
    }

}
