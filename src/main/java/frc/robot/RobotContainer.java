// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import javax.print.attribute.standard.Finishings;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj2.command.button.POVButton;
import frc.robot.commands.*;
import frc.robot.subsystems.*;

/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
  private final Joystick m_stick = new Joystick(Constants.getCTRL("Joystick"));
  private final XboxController m_controller = new XboxController(Constants.getCTRL("Xbox Controller"));

  private final JoystickButton AButton = new JoystickButton(m_controller, 1);
  private final JoystickButton BButton = new JoystickButton(m_controller, 2);
  private final JoystickButton LBumperButton = new JoystickButton(m_controller, 5);
  private final JoystickButton RBumperButton = new JoystickButton(m_controller, 6);
  private final JoystickButton xButton = new JoystickButton(m_controller, 3);
  private final JoystickButton yButton = new JoystickButton(m_controller, 4);
  private final POVButton povup = new POVButton(m_controller, 90);
  private final POVButton povdown = new POVButton(m_controller, 270);


  private final Compressor compressor = new Compressor(Constants.getCAN("PCM"));;
  private final DoubleSolenoid valve1 = new DoubleSolenoid(Constants.getCAN("PCM"), 
  Constants.getPCM("valve 1 forward"), Constants.getPCM("valve 1 reverse"));;
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
  private final DoNothing m_doNothing = new DoNothing();
  private final OldFashionTankDrive  m_tankDrive = new OldFashionTankDrive(
    m_drive, 
    () -> m_controller.getY(Hand.kLeft), 
    () -> m_controller.getY(Hand.kRight)
  );

  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {
    // Configure the button bindings
    configureButtonBindings();
  }

  /**
   * Use this method to define your button->command mappings. Buttons can be created by
   * instantiating a {@link GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a {@link
   * edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {
    AButton.toggleWhenPressed(new take(m_taking));
    BButton.toggleWhenPressed(new Slap(m_transporting));
    xButton.whenHeld(new Spin(m_storeBall));
    yButton.toggleWhenPressed(new Shooting(m_shooter));
    LBumperButton.whenPressed(new BaseSpeedDown(m_drive));
    RBumperButton.whenPressed(new BaseSpeedUp(m_drive));
    povup.whenHeld(new armup(m_arm));
    povdown.whenHeld(new armdown(m_arm));
  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    return m_doNothing;
  }

  public Command getBaseCommand() {
    return m_tankDrive;
  }
}
