/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;
import edu.wpi.first.wpilibj2.command.Subsystem;
import frc.robot.Constants.ShooterConstants;

/**
 * Add your docs here.
 */
public class SimpleShooter implements Subsystem {
  private WPI_TalonFX m_Shooter;
  private double m_Speed = 0;
  // Put methods for controlling this subsystem
  // here. Call these from Commands.

  public SimpleShooter() {
    m_Shooter = new WPI_TalonFX(ShooterConstants.CAN_ID);
        
    m_Shooter.selectProfileSlot(ShooterConstants.PID_SLOT_SPEED_MODE, 0);
    m_Shooter.config_kP(ShooterConstants.PID_SLOT_SPEED_MODE, ShooterConstants.SPEED_P_CONSTANT, ShooterConstants.TIMEOUT_MS);
    m_Shooter.config_kI(ShooterConstants.PID_SLOT_SPEED_MODE, ShooterConstants.SPEED_I_CONSTANT, ShooterConstants.TIMEOUT_MS);
    m_Shooter.config_kD(ShooterConstants.PID_SLOT_SPEED_MODE, ShooterConstants.SPEED_D_CONSTANT, ShooterConstants.TIMEOUT_MS);
    m_Shooter.config_kF(ShooterConstants.PID_SLOT_SPEED_MODE, ShooterConstants.SPEED_F_CONSTANT, ShooterConstants.TIMEOUT_MS);

    stop();  
  }

  public void stop(){
    m_Shooter.set(0); 
    m_Shooter.setNeutralMode(NeutralMode.Coast);
    m_Speed = 0;
  }

  public void setFractionSpeedPID(double setSpeed) {
    if(setSpeed > 1)m_Speed = 1.0;
    else if (setSpeed < -1)m_Speed = -1.0;
    else m_Speed = setSpeed;
    m_Shooter.set(ControlMode.Velocity, ShooterConstants.MAX_TICKS_PER_100mSEC * m_Speed);
  }

  public double getCurrentSpeed(){
    double doubleTicksPer100ms = m_Shooter.getSelectedSensorVelocity(); 
    return doubleTicksPer100ms/ShooterConstants.MAX_TICKS_PER_100mSEC;
  }

  public double getSetSpeed(){
    return m_Speed;
  }

  public boolean isAtSpeed(){
    return Math.abs(getCurrentSpeed() - m_Speed) < ShooterConstants.FRACTION_SPEED_TOLERANCE;
  }

  /* Call this only after isAtSpeed()
   *
   */
  public boolean isShooting(){
    return Math.abs(getCurrentSpeed()- m_Speed) > ShooterConstants.FRACTION_SPEED_DROP_WHEN_SHOOTING;
  }

  public double checkTemp(){
    return m_Shooter.getTemperature();
  }
  
  public String toString(){
    String l_text = "Shooter is set at " + Double.toString(m_Speed) + "; is running at " + Double.toString(getCurrentSpeed());
    return l_text;
  }

  public int getAbsoluteSpeed(){
    return m_Shooter.getSelectedSensorVelocity();
  }
}
