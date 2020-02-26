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

import frc.robot.RobotContainer;
import frc.robot.commands.*;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Subsystem;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.Solenoid;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;



/**
 *
 */
public class Shooter implements Subsystem {

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTANTS

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTANTS

    
private WPI_TalonFX motorShooter;
private Solenoid stopperSolenoid;
//set to use TalonSRX for testing, will later change to TalonFX 
                                 //in robotbuilder   


//TODO: Tune PID values, current values based on Plybot
  
    private final static double SPEED_P_CONSTANT = 0.1;
	private final static double SPEED_I_CONSTANT = 0.00001;
	private final static double SPEED_D_CONSTANT = 0.0;
	private final static double SPEED_F_CONSTANT = 0.0;
    
    public double speedP = SPEED_P_CONSTANT;
	public double speedI = SPEED_I_CONSTANT;
	public double speedD = SPEED_D_CONSTANT;
    public double speedF = SPEED_F_CONSTANT;

    
    public final static int PID_SLOT_SPEED_MODE = 0;
    
    public double SHOOTER_INWARD_MULTIPLIER = 0;
    public double SHOOTER_OUTWARD_MULTIPLIER = 0;

    private final int TIMEOUT_MS = 10;
    private static final int MAX_TICKS_PER_SEC = 9000;

    public Shooter(BallSensor ballSensor) {


    //leftMotorShooter = new WPI_TalonSRX(7);       
    //rightMotorShooter = new WPI_TalonSRX(8);

    motorShooter = new WPI_TalonFX(8);
    stopperSolenoid = new Solenoid(0,5);
    setDefaultCommand(new CloseStopper(this,ballSensor));
    }

    @Override
    public void periodic() {
        // Put code here to be run every loop

    }

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CMDPIDGETTERS


    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CMDPIDGETTERS

    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void stop(){
        motorShooter.set(0); 
        motorShooter.setNeutralMode(NeutralMode.Brake);
    }

    public void run(double pow) {    	
        motorShooter.set(pow);
     
    }

    public void initSpeedMode() {    	
    	motorShooter.set(ControlMode.Velocity, 0);
        
        motorShooter.config_kP(PID_SLOT_SPEED_MODE, speedP, TIMEOUT_MS);
    	motorShooter.config_kI(PID_SLOT_SPEED_MODE, speedI, TIMEOUT_MS);
    	motorShooter.config_kD(PID_SLOT_SPEED_MODE, speedD, TIMEOUT_MS);
    	motorShooter.config_kF(PID_SLOT_SPEED_MODE, speedF, TIMEOUT_MS);

        motorShooter.selectProfileSlot(PID_SLOT_SPEED_MODE, 0);
        
        motorShooter.set(ControlMode.Velocity, 0);
    }

    public void setPercentSpeedPID(double setSpeed) {
        SmartDashboard.putNumber("Shooter PID Val", setSpeed);
        motorShooter.set(ControlMode.Velocity, MAX_TICKS_PER_SEC * setSpeed);
  
    }
    
    public int getTicksPerSecondLeft(){
        return motorShooter.getSelectedSensorVelocity();
    }

    public int getTicksPerSecondRight(){
        return motorShooter.getSelectedSensorVelocity();
    }

    public double getCurrentSpeed(){
        return motorShooter.getSelectedSensorVelocity();
    }

    public void deactivateStopper(){
        stopperSolenoid.set(false);
    }

    public void activateStopper(){
        stopperSolenoid.set(true);
    }
}
