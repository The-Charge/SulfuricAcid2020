/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj2.command.Subsystem;
import frc.robot.Constants.IndexerConstants;;

/**
 * Add your docs here.
 */
public class SimpleIndexer implements Subsystem {
  private WPI_TalonSRX indexerMotorLF;
  private WPI_TalonSRX indexerMotorRF;
  private double m_Speed = 0;
  
  // Put methods for controlling this subsystem
  // here. Call these from Commands.
  public SimpleIndexer() {

    indexerMotorLF = new WPI_TalonSRX(IndexerConstants.LF_CAN_ID);
    indexerMotorRF = new WPI_TalonSRX(IndexerConstants.RF_CAN_ID);
    indexerMotorLF.set(ControlMode.Velocity, 0);

    indexerMotorLF.selectProfileSlot(IndexerConstants.PID_SLOT_SPEED_MODE, 0);        
    indexerMotorLF.config_kP(IndexerConstants.PID_SLOT_SPEED_MODE, IndexerConstants.SPEED_P_CONSTANT, IndexerConstants.TIMEOUT_MS);
    indexerMotorLF.config_kI(IndexerConstants.PID_SLOT_SPEED_MODE, IndexerConstants.SPEED_I_CONSTANT, IndexerConstants.TIMEOUT_MS);
    indexerMotorLF.config_kD(IndexerConstants.PID_SLOT_SPEED_MODE, IndexerConstants.SPEED_D_CONSTANT, IndexerConstants.TIMEOUT_MS);
    indexerMotorLF.config_kF(IndexerConstants.PID_SLOT_SPEED_MODE, IndexerConstants.SPEED_F_CONSTANT, IndexerConstants.TIMEOUT_MS);
    indexerMotorRF.follow(indexerMotorLF);
    stop();
  }
  public void stop(){
    indexerMotorLF.set(0); 
    indexerMotorLF.setNeutralMode(NeutralMode.Coast);
    m_Speed = 0;
  }
  public void setFractionSpeedPID(double setSpeed) {
    if(setSpeed > 1)m_Speed = 1.0;
    else if (setSpeed < -1)m_Speed = -1.0;
    else m_Speed = setSpeed;
    indexerMotorLF.set(ControlMode.Velocity, IndexerConstants.MAX_TICKS_PER_100mSEC * m_Speed);
  }

  public double getCurrentSpeed(){
    double doubleTicksPer100ms = indexerMotorLF.getSelectedSensorVelocity(); 
    return doubleTicksPer100ms/IndexerConstants.MAX_TICKS_PER_100mSEC;
  }

  public double getSetSpeed(){
    return m_Speed;
  }

  public double checkTemp(){
    return indexerMotorLF.getTemperature();
  }
  
  public String toString(){
    String l_text = "Indexer is set at " + Double.toString(m_Speed) + "; is running at " + Double.toString(getCurrentSpeed());
    return l_text;
  }

  public int getAbsoluteSpeed(){
    return indexerMotorLF.getSelectedSensorVelocity();
  }
} 
