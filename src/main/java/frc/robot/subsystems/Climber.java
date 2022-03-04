// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.motorcontrol.Spark;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Climber extends SubsystemBase {
  // Create a Spark object to control the motor on the climber
  Spark m_motor = new Spark(Constants.CLIMBER);

  /** Creates a new Climber. */
  public Climber() {}

  /**
   * raise - raise the climber.
   * 
   * @param power Voltage being applied to the motor
   */
  public void raise(double power) {
    m_motor.set(power);
  }

  /**
   * lower - lower the climber.
   * 
   * @param power Voltage being applied to the motor
   */
  public void lower(double power) {
    m_motor.set(power);
  }

  /**
   * stop - stop the motor.
   */
  public void stop() {
    m_motor.set(0);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
