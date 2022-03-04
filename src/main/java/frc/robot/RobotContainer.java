// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.XboxController.Button;
import frc.robot.commands.autonomous.DriveOffTarmac;
import frc.robot.commands.drivetrain.ArcadeDrive;
import frc.robot.subsystems.Climber;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.Indexer;
import frc.robot.subsystems.Manipulator;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;

/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems and commands are defined here...
  private final Climber m_climber = new Climber();
  private final Drivetrain m_drivetrain = new Drivetrain();
  private final Indexer m_indexer = new Indexer();
  private final Manipulator m_manipulator = new Manipulator();

  private final DriveOffTarmac m_autoDriveOffTarmac = new DriveOffTarmac(m_drivetrain, 0.5);

  // Define XboxController objects to control the robot with
  private final XboxController m_driveController = new XboxController(Constants.CONTROLLER_DRIVER);
  private final XboxController m_operatorController = new XboxController(Constants.CONTROLLER_OPERATOR);

  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {
    // Configure the button bindings
    configureButtonBindings();

    // Set the drive mode to Arcade
    m_drivetrain.setDefaultCommand(
      new ArcadeDrive(
        m_drivetrain, 
        () -> m_driveController.getLeftY(), 
        () -> m_driveController.getRightX()
      )
    );
  }

  /**
   * Use this method to define your button->command mappings. Buttons can be created by
   * instantiating a {@link GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a {@link
   * edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {
    // Raise the climber when the Start button on the operator controller is pressed
    new JoystickButton(m_operatorController, Button.kStart.value)
      .whenPressed(() -> m_climber.raise(0.5))
      .whenReleased(() -> m_climber.stop());

    // Lower the climber when the Back button on the operator controller is pressed
    new JoystickButton(m_operatorController, Button.kBack.value)
      .whenPressed(() -> m_climber.lower(0.5))
      .whenReleased(() -> m_climber.stop());

    // Raise the manipulator arm when the Right Bumper on the operator controller is pressed
    new JoystickButton(m_operatorController, Button.kRightBumper.value)
      .whenPressed(() -> m_manipulator.raise(0.5))
      .whenReleased(() -> m_manipulator.stopArm());

    // Lower the manipulator arm when the Left Bumper on the operator controller is pressed
    new JoystickButton(m_operatorController, Button.kLeftBumper.value)
      .whenPressed(() -> m_manipulator.lower(0.5))
      .whenReleased(() -> m_manipulator.stopArm());

    // Collect Cargo when the A button on the operator controller is pressed
    new JoystickButton(m_operatorController, Button.kA.value)
      .whenPressed(() -> gatherCargo())
      .whenReleased(() -> stopCollectorAndIndexer());

    // Eject Cargo slowly when the B button on the operator controller is pressed.
    // Use this when you want to get rid of cargo, not score
    new JoystickButton(m_operatorController, Button.kB.value)
      .whenPressed(() -> removeCargo())
      .whenReleased(() -> stopCollectorAndIndexer());

    // Eject Cargo quickly when the B button on the operator controller is pressed.
    // Use this to score in the low goal
    new JoystickButton(m_operatorController, Button.kB.value)
      .whenPressed(() -> scoreCargo())
      .whenReleased(() -> stopCollectorAndIndexer());
  }

  /**
   * gatherCargo - run the collect and index commands.
   */
  public void gatherCargo() {
    m_manipulator.collect(0.5);
    m_indexer.index(0.5);
  }

  /**
   * removeCargo - run the eject and outdex commands slowly.
   */
  public void removeCargo() {
    m_manipulator.eject(0.35);
    m_indexer.outdex(0.35);
  }

  /**
   * scoreCargo - run the eject and outdex commands quickly.
   */
  public void scoreCargo() {
    m_manipulator.eject(0.75);
    m_indexer.outdex(0.5);
  }

  /**
   * stopCollectorAndIndexer - stop the collector and indexer.
   */
  public void stopCollectorAndIndexer() {
    m_manipulator.stopCollector();
    m_indexer.stop();
  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    // An ExampleCommand will run in autonomous
    return m_autoDriveOffTarmac;
  }
}
