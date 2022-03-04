// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.drivetrain;

import frc.robot.subsystems.Drivetrain;

import java.util.function.DoubleSupplier;

import edu.wpi.first.wpilibj2.command.CommandBase;

/** An example command that uses an example subsystem. */
public class ArcadeDrive extends CommandBase {
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
  // Create an object for the drivetrain
  private final Drivetrain m_drivetrain;

  // Create two DoubleSupplier objects for power being applied to motors
  private final DoubleSupplier m_throttle, m_rotation;

  /**
   * Creates a new ExampleCommand.
   *
   * @param drivetrain The subsystem used by this command.
   */
  public ArcadeDrive(Drivetrain drivetrain, DoubleSupplier throttle, DoubleSupplier rotation) {
    // Use the Drivetrain subsystem to gain access to its commands
    m_drivetrain = drivetrain;

    // Apply power to the motors
    m_throttle = throttle;
    m_rotation = rotation;

    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(drivetrain);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    // Use the arcadeDrive method from the Drivetrain subsystem
    m_drivetrain.arcadeDrive(m_throttle.getAsDouble(), m_rotation.getAsDouble());
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    // Use the stop method from the Drivetrain subsystem
    m_drivetrain.stop();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
