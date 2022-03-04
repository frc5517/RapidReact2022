// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Indexer extends SubsystemBase {
  // Create WPI_TalonSRX objects to control the motors on the indexer
  WPI_TalonSRX m_motorLeft = new WPI_TalonSRX(Constants.INDEXER_LEFT);
  WPI_TalonSRX m_motorRight = new WPI_TalonSRX(Constants.INDEXER_RIGHT);

  /** Creates a new Indexer. */
  public Indexer() {
    // Set the motors to Brake mode
    m_motorLeft.setNeutralMode(NeutralMode.Brake);
    m_motorRight.setNeutralMode(NeutralMode.Brake);
  }

  /**
   * index - spin all motors towards the middle of the robot.
   * 
   * @param power Voltage being applied to the motor
   */
  public void index(double power) {
    // Apply voltage to the motors
    m_motorLeft.set(ControlMode.PercentOutput, power);
    m_motorRight.set(ControlMode.PercentOutput, -power);
  }

  /**
   * outdex - spin all motors towards the manipulator wheels.
   * 
   * @param power Voltage being applied to the motor
   */
  public void outdex(double power) {
    // Apply voltage to the motors
    m_motorLeft.set(ControlMode.PercentOutput, -power);
    m_motorRight.set(ControlMode.PercentOutput, power);
  }

  /**
   * stop - stop the motors.
   */
  public void stop() {
    // Apply no voltage to the motor
    m_motorLeft.set(ControlMode.PercentOutput, 0);
    m_motorRight.set(ControlMode.PercentOutput, 0);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
