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
import edu.wpi.first.wpilibj.Solenoid;
// BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=IMPORTS
import edu.wpi.first.wpilibj.DigitalInput;

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=IMPORTS


/**
 *
 */
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
        SmartDashboard.putBoolean("Ball Sensed:", ballSensor.get());
    }

    // Put methods for controlling this subsystem
    // here. Call these from Commands.

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
