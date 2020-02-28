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
import edu.wpi.first.wpilibj2.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;

import edu.wpi.first.wpilibj.Solenoid;



/**
 *
 */
public class Shifters implements Subsystem {

private Solenoid shifterSolenoid;

    public Shifters() {
       
    //shifterSolenoid = new Solenoid(1, 1);
 

    }


    @Override
    public void periodic() {
        // Put code here to be run every loop
        writeDashboardDebugValues();
    }

    // Put methods for controlling this subsystem
    // here. Call these from Commands.
    public void shiftHigh() {
		shifterSolenoid.set(true);
	}

	public void shiftLow() {
		shifterSolenoid.set(false);
    }
    
    public boolean isLow(){
        if (shifterSolenoid.get() == false) return true;
        else return false;
    }
	
	public void writeDashboardDebugValues() {
		SmartDashboard.putBoolean("High", !isLow());
	}
}

