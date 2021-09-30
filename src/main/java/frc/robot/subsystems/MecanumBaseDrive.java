package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.drive.MecanumDrive;
import edu.wpi.first.wpilibj.geometry.Translation2d;
import edu.wpi.first.wpilibj.kinematics.MecanumDriveKinematics;
import edu.wpi.first.wpilibj.kinematics.MecanumDriveWheelSpeeds;
import frc.robot.Constants;

public class MecanumBaseDrive extends BaseDrive {
    
    private final WPI_TalonSRX m_lf;
    private final WPI_TalonSRX m_lb;
    private final WPI_TalonSRX m_rf;
    private final WPI_TalonSRX m_rb;
    private final MecanumDrive m_MecanumDrive;

    private final Translation2d m_frontLeftLocation;
    private final Translation2d m_frontRightLocation;
    private final Translation2d m_backLeftLocation;
    private final Translation2d m_backRightLocation;
    private final MecanumDriveKinematics m_kinematics;

    public MecanumBaseDrive(int left_front, int left_back, int right_front, int right_back) {
        m_lf = new WPI_TalonSRX(left_front);
        m_lb = new WPI_TalonSRX(left_back);
        m_rf = new WPI_TalonSRX(right_front);
        m_rb = new WPI_TalonSRX(right_back);
        m_MecanumDrive = new MecanumDrive(m_lf, m_lb, m_rf, m_rb);
        m_frontLeftLocation = new Translation2d(Constants.baseWheelXOffset, Constants.baseWheelYOffset);
        m_frontRightLocation = new Translation2d(Constants.baseWheelXOffset, -Constants.baseWheelYOffset);
        m_backLeftLocation = new Translation2d(-Constants.baseWheelXOffset, Constants.baseWheelYOffset);
        m_backRightLocation = new Translation2d(-Constants.baseWheelXOffset, -Constants.baseWheelYOffset);
        m_kinematics = new MecanumDriveKinematics(m_frontLeftLocation, m_frontRightLocation, 
                                                  m_backLeftLocation, m_backRightLocation);
    }

    public void Drive(MecanumDriveWheelSpeeds wheelSpeeds) {
        
    }
}
