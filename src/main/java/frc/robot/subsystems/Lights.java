package frc.robot.subsystems;

import frc.robot.commands.*;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Subsystem;

import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.Relay;

import com.ctre.phoenix.CANifier;

public class Lights implements Subsystem {

    public static CANifier canifier = new CANifier(0);
    public static Relay relay1 = new Relay(1);

    public final static CANifier.PWMChannel kMotorControllerCh = CANifier.PWMChannel.PWMChannel2;
   
    public Lights() {   
        setDefaultCommand(new ChangeLights(this));
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
    public String vision = "Locked";
   
    public void changeLight()
    {

        if (vision.equals("Locked"))
        {
            //FIXME: Move the magic numbers (242, 170, 53) to constants. Document them.
            canifier.setLEDOutput(242, CANifier.LEDChannel.LEDChannelA);
            canifier.setLEDOutput(170 ,CANifier.LEDChannel.LEDChannelB);
            canifier.setLEDOutput(53, CANifier.LEDChannel.LEDChannelC);
        }
        else{
            if(BallSensor.getOutput() == 1)   
            {
                //FIXME: Move the magic numbers (250, 250, 35) to constants. Document them.
                // Note that there are more below, be sure to get them as well.
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