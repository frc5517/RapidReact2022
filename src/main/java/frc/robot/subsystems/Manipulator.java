// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Manipulator extends SubsystemBase {
  // Create WPI_TalonSRX objects to control the intake motors on the manipulator
  WPI_TalonSRX m_motorLeft = new WPI_TalonSRX(Constants.MANIPULATOR_LEFT);
  WPI_TalonSRX m_motorRight = new WPI_TalonSRX(Constants.MANIPULATOR_RIGHT);

  // Create a CANSparkMax object to control the arm motor on the manipulator
  CANSparkMax m_motorArm = new CANSparkMax(Constants.MANIPULATOR_ARM, MotorType.kBrushless);

  /** Creates a new Manipulator. */
  public Manipulator() {
    // Set the intake motors to Coast mode
    m_motorLeft.setNeutralMode(NeutralMode.Coast);
    m_motorRight.setNeutralMode(NeutralMode.Coast);

    // Set the arm motor to Brake mode
    m_motorArm.setIdleMode(IdleMode.kBrake);
  }

  /**
   * collect - gather Cargo.
   * 
   * @param power Voltage being applied to the motors
   */
  public void collect(double power) {
    // Apply voltage to the motor
    m_motorLeft.set(ControlMode.PercentOutput, power);
    m_motorRight.set(ControlMode.PercentOutput, -power);
  }

  /**
   * eject - remove Cargo.
   * 
   * @param power Voltage being applied to the motors
   */
  public void eject(double power) {
    // Apply voltage to the motor
    m_motorLeft.set(ControlMode.PercentOutput, -power);
    m_motorRight.set(ControlMode.PercentOutput, power);
  }

  /**
   * stopCollector - stop the collector motors.
   */
  public void stopCollector() {
    // Apply no voltage to the motor
    m_motorLeft.set(ControlMode.PercentOutput, 0);
    m_motorRight.set(ControlMode.PercentOutput, 0);
  }

  /**
   * raise - raise the arm.
   * 
   * @param power Voltage being applied to the motor
   */
  public void raise(double power) {
    m_motorArm.set(power);
  }

  /**
   * lower - lower the arm.
   * 
   * @param power Voltage being applied to the motor
   */
  public void lower(double power) {
    m_motorArm.set(-power);
  }

  /**
   * stopArm - stop the arm motors.
   */
  public void stopArm() {
    m_motorArm.stopMotor();
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
