// RobotBuilder Version: 2.0
//
// This file was generated by RobotBuilder. It contains sections of
// code that are automatically generated and assigned by robotbuilder.
// These sections will be updated in the future when you export to
// Java from RobotBuilder. Do not put any code or make any change in
// the blocks indicating autogenerated code or it will be lost on an
// update. Deleting the comments indicating the section will prevent
// it from being updated in the future.

package frc.robot.subsystems;

import frc.robot.MathUtil;
import edu.wpi.first.wpilibj.SPI.Port; //might change to I2C
import edu.wpi.first.wpilibj.controller.PIDController;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.kauailabs.navx.frc.AHRS;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.geometry.Pose2d;
import edu.wpi.first.wpilibj.geometry.Rotation2d;
import edu.wpi.first.wpilibj.kinematics.DifferentialDriveOdometry;
import edu.wpi.first.wpilibj.kinematics.DifferentialDriveWheelSpeeds;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.DriveConstants;
import frc.robot.commands.TankDrive;

public class Drivetrain extends SubsystemBase {


public WPI_TalonFX leftFrontMotor = new WPI_TalonFX(14);
public WPI_TalonFX leftMidMotor = new WPI_TalonFX(13);
private  WPI_TalonFX leftBackMotor = new WPI_TalonFX(15);
 
public WPI_TalonFX rightFrontMotor = new WPI_TalonFX(2);
private WPI_TalonFX rightMidMotor = new WPI_TalonFX(1);
private WPI_TalonFX rightBackMotor = new WPI_TalonFX(3);

private final SpeedControllerGroup m_leftMotors =
new SpeedControllerGroup(leftFrontMotor, leftMidMotor,
                           leftBackMotor);

private final SpeedControllerGroup m_rightMotors =
  new SpeedControllerGroup(rightFrontMotor, rightMidMotor,
                        rightBackMotor);


// The robot's drive
private final DifferentialDrive m_drive = new DifferentialDrive(m_leftMotors, m_rightMotors);

  // The gyro sensor
  private final AHRS m_gyro = new AHRS(Port.kMXP);

  // Odometry class for tracking robot pose
      public final DifferentialDriveOdometry m_odometry;

    //PID Constants (all values still need to be changed, these are values for plybot)
    private static final double SPEED_P_CONSTANT = 0.25;
    private static final double SPEED_I_CONSTANT = 0.0001;   //lowered
    private static final double SPEED_D_CONSTANT = 0.0;
    private static final double SPEED_F_CONSTANT = 0.12;

    private static final int TIMEOUT_MS = 10;
    private static final int MAX_TICKS_PER_SECOND = 9000;   //Plybot = 9000   Sulfuric = 200000
    private static final int TICKS_PER_FOOT = 5270;    //Plybot = 5270   Sulfuric = 9560

    //Motion Magic (all values still need to be changed, these are values for plybot)
    public double MotionMagicP = .8; 
    public double MotionMagicI = 0.0;   
    public double MotionMagicD = 0.001;
    public double MotionMagicF = 0.65;
    public int MotionMagicAcceleration = 2500;  
    public int MotionMagicVelocity = 5000;
    public int MotionMagicPIDIndex = 0;
    public int MotionMagicPIDSlot = 0;
    public double MotionMagicDistance;
    //public double correctionR = 1.02;

    public int smoothing = 4;

    /* Decent PID Values for Resistance:
            P: 0.05
            I: 0.00004
            D: 0.0025
    */
    //These need to be tuned
    private static double PIDTURN_P = 0.05;
    private static double PIDTURN_I = 0.00004;
    private static double PIDTURN_D = 0.0025;

    public PIDController pidController;

    private static final AHRS ahrs = new AHRS(Port.kMXP);

    private static boolean isReversed = false;

    public Drivetrain() {
        resetEncoders();
    m_odometry = new DifferentialDriveOdometry(Rotation2d.fromDegrees(getHeading()));
    pidController = new PIDController(PIDTURN_P, PIDTURN_I, PIDTURN_D);
    setDefaultCommand(new TankDrive(this));
    }

    @Override
    public void periodic() {
        // Put code here to be run every loop
        m_odometry.update(Rotation2d.fromDegrees(getHeading()),leftFrontMotor.getSelectedSensorPosition(0)*DriveConstants.kEncoderDistancePerPulse,
        -rightFrontMotor.getSelectedSensorPosition(0)*DriveConstants.kEncoderDistancePerPulse);
    }

    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void run(double l, double r)
    {
        double leftSpeed = l;
        double rightSpeed = r;

        if(isReversed){
            m_rightMotors.set(-1*rightSpeed);
            m_leftMotors.set(-1*leftSpeed);
        }
        m_rightMotors.set(rightSpeed);
        m_leftMotors.set(leftSpeed);
    }

    public void stop()
    {
        leftFrontMotor.set(0);
        rightFrontMotor.set(0);
    }

    public void setPercentVBus()
    {
     
        leftFrontMotor.set(ControlMode.PercentOutput, 0);
        rightFrontMotor.set(ControlMode.PercentOutput, 0);
    }

    public void initSpeedMode()
    {
		leftFrontMotor.set(ControlMode.Velocity, 0);
        rightFrontMotor.set(ControlMode.Velocity, 0);
    
        //Assigned PID constants to the motors.
        leftFrontMotor.config_kP(1, SPEED_P_CONSTANT, TIMEOUT_MS);
        leftFrontMotor.config_kI(1, SPEED_I_CONSTANT, TIMEOUT_MS);
        leftFrontMotor.config_kD(1, SPEED_D_CONSTANT, TIMEOUT_MS);
        leftFrontMotor.config_kF(1, SPEED_F_CONSTANT, TIMEOUT_MS);

        rightFrontMotor.config_kP(1, SPEED_P_CONSTANT, TIMEOUT_MS);
        rightFrontMotor.config_kI(1, SPEED_I_CONSTANT, TIMEOUT_MS);
        rightFrontMotor.config_kD(1, SPEED_D_CONSTANT, TIMEOUT_MS);
        rightFrontMotor.config_kF(1, SPEED_F_CONSTANT, TIMEOUT_MS);
        
	}
    public void setControlMode(ControlMode mode) {
 		leftFrontMotor.set(mode, 0);
        rightFrontMotor.set(mode, 0);
    }
	 public ControlMode getControlMode() {
        return leftFrontMotor.getControlMode();
    }
    public static AHRS getGyro() {
	return ahrs;
	}
		 public double getGyroYaw() {
	        return ahrs.getYaw();   
	 }

	 public static double getGyroPID(){
		return ahrs.pidGet();
		}
	 
	 public void setPercentSpeedPID (double setSpeedL, double setSpeedR)
    {
        setSpeedR = MathUtil.clamp(setSpeedR, -1, 1);
		 setSpeedL = MathUtil.clamp(setSpeedL, -1, 1);
        leftFrontMotor.set(ControlMode.Velocity, MAX_TICKS_PER_SECOND * setSpeedL);
        rightFrontMotor.set(ControlMode.Velocity, MAX_TICKS_PER_SECOND * setSpeedR);
    }
       
	public boolean getReversed(){
        return isReversed;
    }
    public void setReversed(boolean r){
        isReversed = r;
    }
	 public void writePIDs(double output){
		leftFrontMotor.pidWrite(output);
		rightFrontMotor.pidWrite(-output);
    }
        

    public void setCoastMode() {
    
		leftFrontMotor.setNeutralMode(NeutralMode.Coast);
        rightFrontMotor.setNeutralMode(NeutralMode.Coast);
    }


    //Motion Magic for DriveXFeet command
    public void MotionMagicInit(double distance) {
    	//rightFrontMotor.follow(leftFrontMotor);
    	
    	leftFrontMotor.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, MotionMagicPIDIndex, TIMEOUT_MS);
    	rightFrontMotor.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, MotionMagicPIDIndex, TIMEOUT_MS);
        

        //Setting min and max outputs (new code)
        leftFrontMotor.configNominalOutputForward(0, TIMEOUT_MS);
        leftFrontMotor.configNominalOutputReverse(0, TIMEOUT_MS);
        leftFrontMotor.configPeakOutputForward(1, TIMEOUT_MS);
        leftFrontMotor.configPeakOutputReverse(-1, TIMEOUT_MS);

        rightFrontMotor.configNominalOutputForward(0, TIMEOUT_MS);
        rightFrontMotor.configNominalOutputReverse(0, TIMEOUT_MS);
        rightFrontMotor.configPeakOutputForward(1, TIMEOUT_MS);
        rightFrontMotor.configPeakOutputReverse(-1, TIMEOUT_MS);

    	leftFrontMotor.selectProfileSlot(MotionMagicPIDSlot, MotionMagicPIDIndex);
    	rightFrontMotor.selectProfileSlot(MotionMagicPIDSlot, MotionMagicPIDIndex);
    	
    	leftFrontMotor.config_kP(0, MotionMagicP, TIMEOUT_MS);
    	leftFrontMotor.config_kI(0, MotionMagicI, TIMEOUT_MS);
    	leftFrontMotor.config_kD(0, MotionMagicD, TIMEOUT_MS);
    	leftFrontMotor.config_kF(0, MotionMagicF, TIMEOUT_MS);
    	
    	rightFrontMotor.config_kP(0, MotionMagicP, TIMEOUT_MS);
    	rightFrontMotor.config_kI(0, MotionMagicI, TIMEOUT_MS);
    	rightFrontMotor.config_kD(0, MotionMagicD, TIMEOUT_MS);
    	rightFrontMotor.config_kF(0, MotionMagicF, TIMEOUT_MS);
    	
    	leftFrontMotor.configMotionAcceleration(MotionMagicAcceleration, TIMEOUT_MS);
        leftFrontMotor.configMotionCruiseVelocity(MotionMagicVelocity, TIMEOUT_MS);
        
    	//rightFrontMotor.configMotionAcceleration((int)(correctionR*MotionMagicAcceleration), TIMEOUT_MS);
    	rightFrontMotor.configMotionAcceleration(MotionMagicAcceleration, TIMEOUT_MS);
    	rightFrontMotor.configMotionCruiseVelocity(MotionMagicVelocity, TIMEOUT_MS);
        
    	leftFrontMotor.setSelectedSensorPosition(0, MotionMagicPIDIndex, TIMEOUT_MS);
    	rightFrontMotor.setSelectedSensorPosition(0, MotionMagicPIDIndex, TIMEOUT_MS);


        //Smoothing factor
        leftFrontMotor.configMotionSCurveStrength(smoothing);
        rightFrontMotor.configMotionSCurveStrength(smoothing);

    	MotionMagicDistance = distance * TICKS_PER_FOOT;
        leftFrontMotor.set(ControlMode.MotionMagic, MotionMagicDistance);
        rightFrontMotor.set(ControlMode.MotionMagic, MotionMagicDistance);

    	//rightFrontMotor.set(ControlMode.MotionMagic, correctionR*MotionMagicDistance);
    }

    public boolean isAtPIDDestination() {
		return (Math.abs(this.leftFrontMotor.getSelectedSensorPosition(MotionMagicPIDIndex) - MotionMagicDistance) < 500) || (Math.abs(this.rightFrontMotor.getSelectedSensorPosition(MotionMagicPIDIndex) - MotionMagicDistance) < 500);// || this.leftFrontMotor.getSelectedSensorPosition(MotionMagicPIDIndex) < -MotionMagicDistance + 6000)
    }
    
    
    public void ResestEncoder()
    {
        leftFrontMotor.setSelectedSensorPosition(0, 0, TIMEOUT_MS);
    	rightFrontMotor.setSelectedSensorPosition(0, 0, TIMEOUT_MS);
    }

/*
    public void MotionMagicInit(double distance, int backVelocity, int backAcceleration) {
    	MotionMagicDistance = distance;
    	leftFrontMotor.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, MotionMagicPIDIndex, TIMEOUT_MS);
    	rightFrontMotor.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, MotionMagicPIDIndex, TIMEOUT_MS);
    	
    	leftFrontMotor.selectProfileSlot(MotionMagicPIDSlot, MotionMagicPIDIndex);
    	rightFrontMotor.selectProfileSlot(MotionMagicPIDSlot, MotionMagicPIDIndex);
    	
    	leftFrontMotor.config_kP(0, MotionMagicP, TIMEOUT_MS);
    	leftFrontMotor.config_kI(0, MotionMagicI, TIMEOUT_MS);
    	leftFrontMotor.config_kD(0, MotionMagicD, TIMEOUT_MS);
    	leftFrontMotor.config_kF(0, MotionMagicF, TIMEOUT_MS);
    	
    	rightFrontMotor.config_kP(0, MotionMagicP, TIMEOUT_MS);
    	rightFrontMotor.config_kI(0, MotionMagicI, TIMEOUT_MS);
    	rightFrontMotor.config_kD(0, MotionMagicD, TIMEOUT_MS);
    	rightFrontMotor.config_kF(0, MotionMagicF, TIMEOUT_MS);
    	
    	leftFrontMotor.configMotionAcceleration(backAcceleration, TIMEOUT_MS);
    	leftFrontMotor.configMotionCruiseVelocity(backVelocity, TIMEOUT_MS);
    	
    	rightFrontMotor.configMotionAcceleration((int)(correctionR*backAcceleration), TIMEOUT_MS);
    	rightFrontMotor.configMotionCruiseVelocity((int)(correctionR*backVelocity), TIMEOUT_MS);
        
        //Do we need to reset encoders here?
    	//leftFrontMotor.setSelectedSensorPosition(0, MotionMagicPIDIndex, TIMEOUT_MS);
    	//rightFrontMotor.setSelectedSensorPosition(0, MotionMagicPIDIndex, TIMEOUT_MS);
    	
    	MotionMagicDistance *= TICKS_PER_FOOT;
    	leftFrontMotor.set(ControlMode.MotionMagic, MotionMagicDistance);
    	rightFrontMotor.set(ControlMode.MotionMagic, correctionR*MotionMagicDistance);
    }
    */
    public Pose2d getPose() {
        return m_odometry.getPoseMeters();
      }
    
      /**
       * Returns the current wheel speeds of the robot.
       *
       * @return The current wheel speeds.
       */
      public DifferentialDriveWheelSpeeds getWheelSpeeds() {
        return new DifferentialDriveWheelSpeeds(leftFrontMotor.getSelectedSensorVelocity()*DriveConstants.kEncoderDistancePerPulse, -rightFrontMotor.getSelectedSensorVelocity()*DriveConstants.kEncoderDistancePerPulse);
      }
    
      /**
       * Resets the odometry to the specified pose.
       *
       * @param pose The pose to which to set the odometry.
       */
      public void resetOdometry(Pose2d pose) {
        resetEncoders();
        m_odometry.resetPosition(pose, Rotation2d.fromDegrees(getHeading()));
      }
    
      /**
       * Drives the robot using arcade controls.
       *
       * @param fwd the commanded forward movement
       * @param rot the commanded rotation
       */
      public void arcadeDrive(double fwd, double rot) {
        m_drive.arcadeDrive(fwd, rot);
      }
    
      /**
       * Controls the left and right sides of the drive directly with voltages.
       *
       * @param leftVolts  the commanded left output
       * @param rightVolts the commanded right output
       */
      public void tankDriveVolts(double leftVolts, double rightVolts) {
        m_leftMotors.setVoltage(leftVolts);
        m_rightMotors.setVoltage(-rightVolts);
        m_drive.feed();
      }
    
      /**
       * Resets the drive encoders to currently read a position of 0.
       */
      public void resetEncoders() {
        leftFrontMotor.setSelectedSensorPosition(0);
        rightFrontMotor.setSelectedSensorPosition(0);
      }
    
      public void setEncoders(int left, int right) {
        leftFrontMotor.setSelectedSensorPosition(left);
        rightFrontMotor.setSelectedSensorPosition(right);
      }
    
      /**
       * Gets the average distance of the two encoders.
       *
       * @return the average of the two encoder readings
       */
      public double getAverageEncoderDistance() {
    
        return ((leftFrontMotor.getSelectedSensorPosition(0)*DriveConstants.kEncoderDistancePerPulse - rightFrontMotor.getSelectedSensorPosition(0)*DriveConstants.kEncoderDistancePerPulse) / 2.0);
      }
    
      /**
       * Sets the max output of the drive.  Useful for scaling the drive to drive more slowly.
       *
       * @param maxOutput the maximum output to which the drive will be constrained
       */
      public void setMaxOutput(double maxOutput) {
        m_drive.setMaxOutput(maxOutput);
      }
    
      /**
       * Zeroes the heading of the robot.
       */
      public void zeroHeading() {
        m_gyro.reset();
      }
    
      /**
       * Returns the heading of the robot.
       *
       * @return the robot's heading in degrees, from -180 to 180
       */
      public double getHeading() {
        return Math.IEEEremainder(m_gyro.getAngle(), 360) * (DriveConstants.kGyroReversed ? -1.0 : 1.0);
      }
    
      /**
       * Returns the turn rate of the robot.
       *
       * @return The turn rate of the robot, in degrees per second
       */
      public double getTurnRate() {
        return m_gyro.getRate() * (DriveConstants.kGyroReversed ? -1.0 : 1.0);
      }
      public void intializeMotors()
      {
        rightBackMotor.setInverted(true);
    rightMidMotor.setInverted(true);
    rightFrontMotor.setInverted(true);

    rightBackMotor.follow(rightFrontMotor);
    rightMidMotor.follow(rightFrontMotor);
    leftBackMotor.follow(leftFrontMotor);
    leftMidMotor.follow(leftFrontMotor);
      }
}
    