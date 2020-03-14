/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj2.command.Subsystem;
import frc.robot.Constants.IntakeConstants;;

public class SimpleIntake implements Subsystem {
  private WPI_TalonSRX intakeMotor;
  private double m_speed = 0;
  /**
   * Creates a new SimpleIntake.
   */
  public SimpleIntake() {
    intakeMotor = new WPI_TalonSRX(IntakeConstants.CAN_ID);
    intakeMotor.setInverted(true);
    intakeMotor.setSensorPhase(true);	
    intakeMotor.set(ControlMode.Velocity, 0);
    intakeMotor.setNeutralMode(NeutralMode.Coast);
    intakeMotor.selectProfileSlot(IntakeConstants.PID_SLOT_SPEED_MODE, 0);  
    intakeMotor.config_kP(IntakeConstants.PID_SLOT_SPEED_MODE, IntakeConstants.SPEED_P_CONSTANT, IntakeConstants.TIMEOUT_MS);
    intakeMotor.config_kI(IntakeConstants.PID_SLOT_SPEED_MODE, IntakeConstants.SPEED_I_CONSTANT, IntakeConstants.TIMEOUT_MS);
    intakeMotor.config_kD(IntakeConstants.PID_SLOT_SPEED_MODE, IntakeConstants.SPEED_D_CONSTANT, IntakeConstants.TIMEOUT_MS);
    intakeMotor.config_kF(IntakeConstants.PID_SLOT_SPEED_MODE, IntakeConstants.SPEED_F_CONSTANT, IntakeConstants.TIMEOUT_MS);
  }

  public void stop() {
    m_speed = 0;
    intakeMotor.set(m_speed);
  }

  public void setFractionSpeedPID(double setSpeed) {
    if(setSpeed > 1)m_speed = 1;
    else if (setSpeed < -1)m_speed = -1;
    else m_speed = setSpeed;
    intakeMotor.set(ControlMode.Velocity, IntakeConstants.MAX_TICKS_PER_100mSEC * setSpeed);
  }

  public double getCurrentSpeed(){
    double doubleTicksPer100ms = intakeMotor.getSelectedSensorVelocity(); 
    return doubleTicksPer100ms/IntakeConstants.MAX_TICKS_PER_100mSEC;
  }

  public double getSetSpeed(){
    return m_speed;
  }

  public double checkTemp(){
    return intakeMotor.getTemperature();
  }
  
  public String toString(){
    String l_text = "Intake is set at " + Double.toString(m_speed) + "; is running at " + Double.toString(getCurrentSpeed());
    return l_text;
  }

  public int getAbsoluteSpeed(){
    return intakeMotor.getSelectedSensorVelocity();
  }
}
