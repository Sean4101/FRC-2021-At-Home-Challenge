// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import javax.print.attribute.standard.Finishings;

import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj2.command.button.POVButton;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import frc.robot.commands.*;
import frc.robot.subsystems.*; 

/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {

  private final XboxController m_controller = new XboxController(Constants.getCTRL("Xbox Controller"));
  private final XboxController m_controller2 = new XboxController(Constants.getCTRL("Xbox Controller2"));
  private final JoystickButton AButton = new JoystickButton(m_controller, 1);
  private final JoystickButton BButton = new JoystickButton(m_controller, 2);
  private final JoystickButton LBumperButton = new JoystickButton(m_controller2, 5);
  private final JoystickButton RBumperButton = new JoystickButton(m_controller2, 6);
  //  private final JoystickButton LBumperButton1 = new JoystickButton(m_controller, 5);
  private final JoystickButton RBumperButton1 = new JoystickButton(m_controller, 6);
  private final JoystickButton xButton = new JoystickButton(m_controller, 3);
  private final JoystickButton yButton = new JoystickButton(m_controller, 4);
  private final POVButton povin = new POVButton(m_controller2, 90);
  private final POVButton povout = new POVButton(m_controller2, 270);
  private final POVButton povup = new POVButton(m_controller, 180);
  private final JoystickButton climb = new JoystickButton(m_controller2, 2);
  private final POVButton povin1 = new POVButton(m_controller, 90);
  private final POVButton povout1 = new POVButton(m_controller, 270);
  private final JoystickButton AutoButton = new JoystickButton(m_controller2, 1);
  private final POVButton rShoot = new POVButton(m_controller, 0);

  private final Compressor compressor = new Compressor(Constants.getCAN("PCM"));
  private final DoubleSolenoid valve1 = new DoubleSolenoid(Constants.getCAN("PCM"), 
  Constants.getPCM("valve 1 forward"), Constants.getPCM("valve 1 reverse"));
  private final DoubleSolenoid valve2 = new DoubleSolenoid(Constants.getCAN("PCM"), 
  Constants.getPCM("valve 2 forward"), Constants.getPCM("valve 2 reverse"));

  private final TheGreatCylinder m_cylinder = new TheGreatCylinder(valve1, valve2);
  private final BaseDrive m_drive = new BaseDrive(
    Constants.getCAN("drive lf"), 
    Constants.getCAN("drive lb"), 
    Constants.getCAN("drive rf"), 
    Constants.getCAN("drive rb")
  );

  private final Transporting m_transporting = new Transporting(Constants.getCAN("ballsSlapper"));
  private final Taking m_taking = new Taking(Constants.getCAN("intake"));
  private final Takeballarm m_arm = new Takeballarm(Constants.getCAN("arm"));
  private final StoreBall m_storeBall = new StoreBall(Constants.getCAN("lazySusan"));
  private final Shooter m_shooter = new Shooter(Constants.getCAN("lshoot"),Constants.getCAN("rshoot"));
  
  private final AutoDrive autoDrive = new AutoDrive(m_drive, m_shooter, m_storeBall, m_transporting, m_arm);
  private final DoNothing m_doNothing = new DoNothing();
  private final OldFashionTankDrive  m_tankDrive = new OldFashionTankDrive(
    m_drive, 
    () -> m_controller2.getY(Hand.kLeft), 
    () -> m_controller2.getY(Hand.kRight)
  );

  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {
    //Configure the button bindings
    configureButtonBindings();
    //configureTalonSRX_sensor(m_shooter.m_lshoot);
    //configureTalonSRX_sensor(m_shooter.m_rshoot);

  }

  /**
   * Use this method to define your button->command mappings. Buttons can be created by
   * instantiating a {@link GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a {@link
   * edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {
    AButton.toggleWhenPressed(new take(m_taking));
    // AButton.toggleWhenPressed(new AutoShoot(m_drive));
    BButton.toggleWhenPressed(new Slap(m_transporting));
    RBumperButton1.whenHeld(new Slap(m_transporting));
    AutoButton.toggleWhenPressed(new AutoShoot(m_drive));
    xButton.whenHeld(new Spin(m_storeBall));
    yButton.toggleWhenPressed(new Shooting(m_shooter));
    LBumperButton.whenPressed(new BaseSpeedDown(m_drive));
    RBumperButton.whenPressed(new BaseSpeedUp(m_drive));
    povin.whenHeld(new armup(m_arm));
    povout.whenHeld(new armdown(m_arm));
    povin1.whenHeld(new armup(m_arm));
    povout1.whenHeld(new armdown(m_arm));
    povup.whenHeld(new Reverse(m_storeBall));
    climb.toggleWhenPressed(new CylinderPush(m_cylinder));
    rShoot.toggleWhenPressed(new Rshooting(m_shooter));
  }

 /* private void configureTalonSRX_sensor(WPI_TalonSRX _talon)
 {
  _talon.configFactoryDefault();

  _talon.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative,
                                          Constants.kPIDLoopIdx, 
                                          Constants.kTimeoutMs);

  _talon.setSensorPhase(true);

  /* Config the peak and nominal outputs 
  _talon.configNominalOutputForward(0, Constants.kTimeoutMs);
  _talon.configNominalOutputReverse(0, Constants.kTimeoutMs);
  _talon.configPeakOutputForward(1, Constants.kTimeoutMs);
  _talon.configPeakOutputReverse(-1, Constants.kTimeoutMs);

   Config the Velocity closed loop gains in slot0 
  _talon.config_kF(Constants.kPIDLoopIdx, Constants.kGains_Velocit.kF, Constants.kTimeoutMs);
  _talon.config_kP(Constants.kPIDLoopIdx, Constants.kGains_Velocit.kP, Constants.kTimeoutMs);
  _talon.config_kI(Constants.kPIDLoopIdx, Constants.kGains_Velocit.kI, Constants.kTimeoutMs);
  _talon.config_kD(Constants.kPIDLoopIdx, Constants.kGains_Velocit.kD, Constants.kTimeoutMs);

 }*/
  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    return autoDrive;
  }

  public Command getBaseCommand() {
    return m_tankDrive;
  }
}
