package frc.robot.subsystems;

import frc.robot.commands.*;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Subsystem;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.DigitalInput;

public class Stopper implements Subsystem {

private DigitalInput ballSensor;
private Solenoid stopperSolenoid;

    public Stopper() {

    ballSensor = new DigitalInput(1);
    stopperSolenoid = new Solenoid(0,2);
    
    stopperSolenoid.set(true);
        
    }

    @Override
    public void periodic() {
        // Put code here to be run every loop
        SmartDashboard.putBoolean("Stopper Ball Sensed:", ballSensor.get());
    }

    public boolean ballSensedOut(){
        return ballSensor.get();
    }

    public void openStopper(){
        stopperSolenoid.set(false);
    }

    public void closeStopper(){
        //if (!ballSensed()) stopperSolenoid.set(true);
        //else new CloseStopper(this);
        stopperSolenoid.set(true);
    }

}
