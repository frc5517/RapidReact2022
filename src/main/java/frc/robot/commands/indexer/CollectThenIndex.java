// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.indexer;

import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import frc.robot.commands.manipulator.CollectForTime;
import frc.robot.subsystems.Indexer;
import frc.robot.subsystems.Manipulator;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html
public class CollectThenIndex extends ParallelCommandGroup {
  /** Creates a new CollectThenIndex. */
  public CollectThenIndex(
    Manipulator manipulator, double manipulatorPower, int time,
    Indexer indexer, double indexerPower
  ) {
    // Add your commands in the addCommands() call, e.g.
    // addCommands(new FooCommand(), new BarCommand());
    addCommands(
      new CollectForTime(manipulator, () -> manipulatorPower, time),
      new Index(indexer, () -> indexerPower)
    );
  }
}
