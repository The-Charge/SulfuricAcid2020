/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.kinematics.DifferentialDriveKinematics;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants.  This class should not be used for any other purpose.  All constants should be
 * declared globally (i.e. public static).  Do not put anything functional in this class.
 *
 * <p>It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 */
public final class Constants {
  public static final class ShooterConstants{
    public final static double SPEED_P_CONSTANT = 0.1;
    public final static double SPEED_I_CONSTANT = 0.0001;
    public final static double SPEED_D_CONSTANT = 0.0;
    public final static double SPEED_F_CONSTANT = 0.0;
    public final static int PID_SLOT_SPEED_MODE = 0;
    public final static int TIMEOUT_MS = 10;
    public static final double MAX_TICKS_PER_100mSEC = 20000.0;
    public final static int CAN_ID = 0;
    public final static double FRACTION_SPEED_TOLERANCE = 0.02;
    public final static double FRACTION_SPEED_DROP_WHEN_SHOOTING = 0.1;
  }
  public static final class IndexerConstants{
    public final static double SPEED_P_CONSTANT = 0.01;
    public final static double SPEED_I_CONSTANT = 0.00001;
    public final static double SPEED_D_CONSTANT = 0.0;
    public final static double SPEED_F_CONSTANT = 0.0;  
    public final static int PID_SLOT_SPEED_MODE = 0;
    public final static int TIMEOUT_MS = 10;
    public final static double MAX_TICKS_PER_100mSEC = 130000;
    public final static int LF_CAN_ID = 8;
    public final static int RF_CAN_ID = 10;
  }
  public static final class IntakeConstants{
    public final static double SPEED_P_CONSTANT = 0.05;
    public final static double SPEED_I_CONSTANT = 0.0003;
    public final static double SPEED_D_CONSTANT = 0.0;
    public final static double SPEED_F_CONSTANT = 0.0;
    public final static int PID_SLOT_SPEED_MODE = 0;
    public final static int TIMEOUT_MS = 10;
    public static final double MAX_TICKS_PER_100mSEC = 31000;
    public final static int CAN_ID = 9;
  }

  public static final class DriveConstants {
    public static final int kLeftMotor1Port = 1;
    public static final int kLeftMotor2Port = 2;
    public static final int kLeftMotor3Port = 3;
    public static final int kRightMotor1Port = 7;
    public static final int kRightMotor2Port = 0;
    public static final int kRightMotor3Port = 8;
    //public static final int[] kLeftEncoderPorts = new int[]{1,2,3};
    //public static final int[] kRightEncoderPorts = new int[]{7,0,8};
    public static final boolean kLeftEncoderReversed = false;
    public static final boolean kRightEncoderReversed = true;

    public static final double kTrackwidthMeters = 0.749676776;
    public static final DifferentialDriveKinematics kDriveKinematics =
        new DifferentialDriveKinematics(kTrackwidthMeters);

    public static final int kEncoderCPR = 14000;
    public static final double kWheelDiameterMeters = 0.152;
    public static final double kEncoderDistancePerPulse =
        // Assumes the encoders are directly mounted on the wheel shafts
        (kWheelDiameterMeters * Math.PI) / (double) kEncoderCPR;

    public static final boolean kGyroReversed = true;

    // These are example values only - DO NOT USE THESE FOR YOUR OWN ROBOT!
    // These characterization values MUST be determined either experimentally or theoretically
    // for *your* robot's drive.
    // The Robot Characterization Toolsuite provides a convenient tool for obtaining these
    // values for your robot.
    public static final double ksVolts = 0.501;
    public static final double kvVoltSecondsPerMeter = 1.6;
    public static final double kaVoltSecondsSquaredPerMeter = 0.176;

    // Example value only - as above, this must be tuned for your drive!
    public static final double kPDriveVel = 4;
  }

  public static final class OIConstants {
    public static final int kDriverControllerPort = 1;
  }

  public static final class AutoConstants {
    public static final double initElevation = 0.8;
    public static final double initShootSpeed = 0.65;
    
    public static final double indexFastSpeed = 0.6;
    public static final double kMaxSpeedMetersPerSecond = 1.5; //1.0
    public static final double kMaxAccelerationMetersPerSecondSquared = 1.0; //.5

    // Reasonable baseline values for a RAMSETE follower in units of meters and seconds
    public static final double kRamseteB = 2;
    public static final double kRamseteZeta = 0.7;
  }
}
