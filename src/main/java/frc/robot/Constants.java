// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants. This class should not be used for any other purpose. All constants should be declared
 * globally (i.e. public static). Do not put anything functional in this class.
 *
 * <p>It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 */
public final class Constants {
    // Define the port being used by the controllers
    public static final int CONTROLLER_DRIVER = 0;
    public static final int CONTROLLER_OPERATOR = 1;
    
    // Define the CAN IDs being used by the drivetrain motors
    public static final int DRIVE_FRONT_LEFT = 1;
    public static final int DRIVE_REAR_LEFT = 2;
    public static final int DRIVE_FRONT_RIGHT = 3;
    public static final int DRIVE_REAR_RIGHT = 4;

    // Define the CAN IDs being used by the manipulator motors
    public static final int MANIPULATOR_LEFT = 5;
    public static final int MANIPULATOR_RIGHT = 6;
    public static final int MANIPULATOR_ARM = 7;

    // Define the CAN IDs being used by the indexer motors
    public static final int INDEXER_LEFT = 8;
    public static final int INDEXER_RIGHT = 9;

    // Define the PWM port being used by the climber motor
    public static final int CLIMBER = 0;
}
