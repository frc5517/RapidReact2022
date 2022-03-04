// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.autonomous;

import edu.wpi.first.wpilibj2.command.ParallelDeadlineGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.commands.drivetrain.ArcadeDrive;
import frc.robot.subsystems.Drivetrain;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html
public class DriveOffTarmac extends ParallelDeadlineGroup {
  /** Creates a new DriveOffTarmac. */
  public DriveOffTarmac(Drivetrain drivetrain, double throttle) {
    // Add the deadline command in the super() call. Add other commands using
    // addCommands().
    super(new WaitCommand(5));
    addCommands(
      new ArcadeDrive(drivetrain, () -> throttle, () -> 0)
    );
  }
}
