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

import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.Relay;

import com.ctre.phoenix.CANifier;



/**
 *
 */
public class Lights implements Subsystem {

   

    public static CANifier canifier = new CANifier(0);
    public static Relay relay1 = new Relay(1);
    public String vision;
    public final static CANifier.PWMChannel kMotorControllerCh = CANifier.PWMChannel.PWMChannel2;
    public Turret m_turret;
 

    public Lights(Turret turret) {   
        setDefaultCommand(new ChangeLights(this));
        m_turret = turret;

    }

    

    
    public void onStart(){
        canifier.enablePWMOutput(kMotorControllerCh.value, true);
        relay1.set(Relay.Value.kOn);

    }

    public void onStop(){
        canifier.enablePWMOutput(kMotorControllerCh.value, false);
    }
    public void onLoop(){}

    public boolean isDone(){return false;}
   
    public void changeLight()
    {
        vision = m_turret.getVisionString();

        if (vision.equals("locked"))
        {
            canifier.setLEDOutput(242, CANifier.LEDChannel.LEDChannelA);
            canifier.setLEDOutput(170 ,CANifier.LEDChannel.LEDChannelB);
            canifier.setLEDOutput(53, CANifier.LEDChannel.LEDChannelC);
        }
        else{
            if(BallSensor.getOutput() == 1)   
            {
                canifier.setLEDOutput(250, CANifier.LEDChannel.LEDChannelA);
                canifier.setLEDOutput(250, CANifier.LEDChannel.LEDChannelB);
                canifier.setLEDOutput(35, CANifier.LEDChannel.LEDChannelC);
            }
            else if(BallSensor.getOutput() == 2)
            {
                canifier.setLEDOutput(185, CANifier.LEDChannel.LEDChannelA);
                canifier.setLEDOutput(242, CANifier.LEDChannel.LEDChannelB);
                canifier.setLEDOutput(61, CANifier.LEDChannel.LEDChannelC);
            } 
            else if (BallSensor.getOutput() == 3)
            {
                canifier.setLEDOutput(139, CANifier.LEDChannel.LEDChannelA);
                canifier.setLEDOutput(242, CANifier.LEDChannel.LEDChannelB);
                canifier.setLEDOutput(61, CANifier.LEDChannel.LEDChannelC);
                
            }
            else if (BallSensor.getOutput() == 4)
            {
                canifier.setLEDOutput(91, CANifier.LEDChannel.LEDChannelA);
                canifier.setLEDOutput(242, CANifier.LEDChannel.LEDChannelB);
                canifier.setLEDOutput(61, CANifier.LEDChannel.LEDChannelC);
            }
            else if (BallSensor.getOutput() == 5)
            {
                canifier.setLEDOutput(48, CANifier.LEDChannel.LEDChannelA);
                canifier.setLEDOutput(201, CANifier.LEDChannel.LEDChannelB);
                canifier.setLEDOutput(56, CANifier.LEDChannel.LEDChannelC);
            }
        }
        
    }
   

    
   

}