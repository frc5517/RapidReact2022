// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.SparkMaxPIDController;
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

  // Create a RelativeEncoder object for the arm motor
  RelativeEncoder m_encoderArm;

  // Create a SparkMaxPIDController object for closed-loop control of the arm
  SparkMaxPIDController m_pidArm;

  // Create variables for PID control values
  // CHANGE VALUES AS FOLLOWS:
  // P: raise until the motor stops
  // I: raise until the motor stops smoothly
  double kP = 0.0,
         kI = 0.0,
         kD = 0.0,
         kIz = 0.0,
         kFF = 0.0,
         kMaxOutput = 1,
         kMinOutput = -1;

  /** Creates a new Manipulator. */
  public Manipulator() {
    // Restore factory default settings of the arm motor for safety
    m_motorArm.restoreFactoryDefaults();

    // Set the intake motors to Coast mode
    m_motorLeft.setNeutralMode(NeutralMode.Coast);
    m_motorRight.setNeutralMode(NeutralMode.Coast);

    // Set the arm motor to Brake mode
    m_motorArm.setIdleMode(IdleMode.kBrake);

    // Allow closed-loop control of the arm
    m_pidArm = m_motorArm.getPIDController();

    // Set the encoder to the correct motor
    m_encoderArm = m_motorArm.getEncoder();

    // Set the feedback device of the PID controller to the correct encoder
    m_pidArm.setFeedbackDevice(m_encoderArm);

    // Set PID coefficients
    m_pidArm.setP(kP);
    m_pidArm.setI(kI);
    m_pidArm.setD(kD);
    m_pidArm.setIZone(kIz);
    m_pidArm.setFF(kFF);
    m_pidArm.setOutputRange(kMinOutput, kMaxOutput);
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
   * moveToSetpoint - move the arm to the desired encoder value.
   * 
   * @param setpoint Desired encoder position
   * @param power Voltage being applied to the motor
   */
  public void moveToSetpoint(double setpoint, double power) {
    // Set power to the motor
    m_motorArm.set(power);

    // Check encoder value
    if(m_encoderArm.getPosition() >= setpoint) {
      // If the encoder is going to pass the setpoint, stop the motor
      m_motorArm.stopMotor();
    }
  }

  /**
   * stopArm - stop the arm motors.
   */
  public void stopArm() {
    m_motorArm.stopMotor();
  }

  public void resetArmEncoder() {
    // Set the position of the encoder to zero, effectively resetting it
    m_encoderArm.setPosition(0);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
