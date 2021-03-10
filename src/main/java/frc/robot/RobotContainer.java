// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.commands.CylinderPull;
import frc.robot.commands.CylinderPush;
import frc.robot.commands.DoNothing;
import frc.robot.subsystems.TheGreatCylinder;

/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
  private Joystick m_stick;
  private XboxController m_controller;

  private JoystickButton AButton;
  private JoystickButton BButton;

  private Compressor compressor;
  private DoubleSolenoid valve1;
  private DoubleSolenoid valve2;

  private TheGreatCylinder m_cylinder;

  private DoNothing m_doNothing;

  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {
    // Configure the button bindings
    m_stick = new Joystick(Constants.getCTRL("Joystick"));
    m_controller = new XboxController(Constants.getCTRL("Xbox Controller"));

    AButton = new JoystickButton(m_controller, 1);
    BButton = new JoystickButton(m_controller, 2);

    compressor = new Compressor(Constants.getCAN("PCM"));

    valve1 = new DoubleSolenoid(Constants.getCAN("PCM"), 
      Constants.getPCM("valve 1 forward"), Constants.getPCM("valve 1 reverse"));
    valve2 = new DoubleSolenoid(Constants.getCAN("PCM"), 
      Constants.getPCM("valve 2 forward"), Constants.getPCM("valve 2 reverse"));
    m_cylinder = new TheGreatCylinder(valve1, valve2);

    m_doNothing = new DoNothing();

    configureButtonBindings();
  }

  /**
   * Use this method to define your button->command mappings. Buttons can be created by
   * instantiating a {@link GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a {@link
   * edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {
    AButton.whenPressed(new CylinderPush(m_cylinder));
    BButton.whenPressed(new CylinderPull(m_cylinder));
  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    return m_doNothing;
  }
}
