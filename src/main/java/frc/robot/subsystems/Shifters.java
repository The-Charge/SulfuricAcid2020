package frc.robot.subsystems;

import frc.robot.commands.*;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj2.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.Solenoid;

public class Shifters implements Subsystem {

private Solenoid shifterSolenoid;

    public Shifters() {
       
    shifterSolenoid = new Solenoid(0, 1);
    }

    @Override
    public void periodic() {
        // Put code here to be run every loop
		SmartDashboard.putBoolean("Shifters High Gear", !isLow());
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
        return !shifterSolenoid.get();
    }
}

