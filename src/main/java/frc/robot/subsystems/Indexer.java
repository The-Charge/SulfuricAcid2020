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

import frc.robot.commands.*;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Subsystem;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
//import edu.wpi.first.wpilibj.
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
// BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=IMPORTS
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=IMPORTS


/**
 *
 */
public class Indexer implements Subsystem {

private WPI_TalonSRX indexerMotorLF;
private WPI_TalonSRX indexerMotorRF;
private Intake m_intake;
private DigitalInput ballIn;
private int timer;

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
    private final static double SPEED_P_CONSTANT = 0.01;
	private final static double SPEED_I_CONSTANT = 0.00001;
	private final static double SPEED_D_CONSTANT = 0.0;
	private final static double SPEED_F_CONSTANT = 0.0;
    
    public double speedP = SPEED_P_CONSTANT;
	public double speedI = SPEED_I_CONSTANT;
	public double speedD = SPEED_D_CONSTANT;
    public double speedF = SPEED_F_CONSTANT;

    private Stopper m_stopper;

    
    public final static int PID_SLOT_SPEED_MODE = 0;

    private final int TIMEOUT_MS = 10;
    private static final int MAX_TICKS_PER_SEC = 130000;

    private final SpeedControllerGroup indexerMotors 
= new SpeedControllerGroup(indexerMotorRF,indexerMotorLF);

    public Indexer(Stopper stopper) {

indexerMotorLF = new WPI_TalonSRX(8);


        
indexerMotorRF = new WPI_TalonSRX(10);

ballIn = new DigitalInput(2);
m_stopper = stopper;
timer = 1000;
    }


    public void periodic()
    {
      
    }
    public void stop(){
        indexerMotorLF.set(0);
        indexerMotorRF.set(0);
    
    }

    public void initSpeedMode() {    	
    	indexerMotorLF.set(ControlMode.Velocity, 0);
        
        indexerMotorLF.config_kP(PID_SLOT_SPEED_MODE, speedP, TIMEOUT_MS);
    	indexerMotorLF.config_kI(PID_SLOT_SPEED_MODE, speedI, TIMEOUT_MS);
    	indexerMotorLF.config_kD(PID_SLOT_SPEED_MODE, speedD, TIMEOUT_MS);
    	indexerMotorLF.config_kF(PID_SLOT_SPEED_MODE, speedF, TIMEOUT_MS);

        indexerMotorLF.selectProfileSlot(PID_SLOT_SPEED_MODE, 0);
        /*
        indexerMotorRF.set(ControlMode.Velocity, 0);
        
        indexerMotorRF.config_kP(PID_SLOT_SPEED_MODE, speedP, TIMEOUT_MS);
    	indexerMotorRF.config_kI(PID_SLOT_SPEED_MODE, speedI, TIMEOUT_MS);
    	indexerMotorRF.config_kD(PID_SLOT_SPEED_MODE, speedD, TIMEOUT_MS);
    	indexerMotorRF.config_kF(PID_SLOT_SPEED_MODE, speedF, TIMEOUT_MS);

        indexerMotorRF.selectProfileSlot(PID_SLOT_SPEED_MODE, 0);
        */
    }

    
    public void setPercentSpeedPID(double setSpeed, boolean shooterOpen) {
        //boolean wasBall = wasBallSensedIn();
        if (shooterOpen || setSpeed < 0)
        {

        }
        else if (m_stopper.ballSensedOut())
        {
            setSpeed = 0.25*setSpeed;
        }
        else if (ballSensedIn() || wasBallSensedIn())
        {
            setSpeed = 0.45*setSpeed;
        }
        else
         setSpeed = 0*setSpeed;
       
        SmartDashboard.putNumber("PID Val", setSpeed);
        SmartDashboard.putBoolean("BallSensedIn", ballSensedIn());
        SmartDashboard.putBoolean("WasBallSensed", wasBallSensedIn());
        indexerMotorLF.set(ControlMode.Velocity, MAX_TICKS_PER_SEC * setSpeed);
        //indexerMotorRF.set(ControlMode.Velocity, MAX_TICKS_PER_SEC * setSpeed);
    }

    

    private boolean wasBallSensedIn() {
        if (ballSensedIn()){
            timer = 0;
        }
        timer++;
        if (timer < 30) return true;
        else return false;
    }

    public int getTicksPerSecondLeft() {
        return indexerMotorLF.getSelectedSensorVelocity();
    }

	public void setPercentVBus(double setSpeed) {
        
    }
    public void initalizeMotors()
    {
        indexerMotorRF.follow(indexerMotorLF);
    }
    public void setCoastMode()
    {
        indexerMotorRF.setNeutralMode(NeutralMode.Coast);
        indexerMotorLF.setNeutralMode(NeutralMode.Coast);
    }
    public void setBrakeMode()
    {
        indexerMotorRF.setNeutralMode(NeutralMode.Brake);
        indexerMotorLF.setNeutralMode(NeutralMode.Brake);
    }
    public boolean ballSensedIn(){
        return ballIn.get();
    }
}

