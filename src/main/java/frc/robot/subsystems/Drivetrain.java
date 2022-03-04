// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.InvertType;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Drivetrain extends SubsystemBase {
  // Create WPI_TalonSRX objects to control the motors on the drivetrain
  WPI_TalonSRX m_motorFrontLeft = new WPI_TalonSRX(Constants.DRIVE_FRONT_LEFT);
  WPI_TalonSRX m_motorRearLeft = new WPI_TalonSRX(Constants.DRIVE_REAR_LEFT);
  WPI_TalonSRX m_motorFrontRight = new WPI_TalonSRX(Constants.DRIVE_FRONT_RIGHT);
  WPI_TalonSRX m_motorRearRight = new WPI_TalonSRX(Constants.DRIVE_REAR_RIGHT);

  // Create a DifferentialDrive object using the motors with the rear motors
  // "following" the front motors
  DifferentialDrive m_drive = new DifferentialDrive(m_motorFrontLeft, m_motorFrontRight);

  /** Creates a new Drivetrain. */
  public Drivetrain() {
    // Set the motors to Coast mode
    m_motorFrontLeft.setNeutralMode(NeutralMode.Coast);
    m_motorRearLeft.setNeutralMode(NeutralMode.Coast);
    m_motorFrontRight.setNeutralMode(NeutralMode.Coast);
    m_motorRearRight.setNeutralMode(NeutralMode.Coast);

    // Set the rear motors to follow the front motors
    m_motorRearLeft.follow(m_motorFrontLeft);
    m_motorRearRight.follow(m_motorFrontRight);

    // Invert the motors on the right
    m_motorFrontRight.setInverted(true);
    m_motorRearRight.setInverted(InvertType.FollowMaster);

    // Do not invert the motors on the left. Set in code to guarantee it
    m_motorFrontLeft.setInverted(false);
    m_motorRearLeft.setInverted(InvertType.FollowMaster);
  }

  /**
   * arcadeDrive - drivetrain control with "arcade" controls.
   * 
   * Left Y-axis controls throttle, Right X-axis controls rotation.
   * 
   * @param throttle Forward/backward speed of the motors
   * @param rotation Rotational speed of the motors
   */
  public void arcadeDrive(double throttle, double rotation) {
    // Call DifferentialDrive's arcadeDrive method
    m_drive.arcadeDrive(throttle, rotation, true);
  }

  /**
   * stopDrive - stops all motors on the drivetrain.
   */
  public void stop() {
    // Call DifferentialDrive's stopMotor method
    m_drive.stopMotor();
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  @Override
  public void simulationPeriodic() {
    // This method will be called once per scheduler run during simulation
  }
}
