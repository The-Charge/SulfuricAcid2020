package frc.robot.subsystems;

import frc.robot.commands.*;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj2.command.Subsystem;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.Solenoid;

public class Cooling implements Subsystem {

    private Solenoid coolingSolenoid;

    public Cooling() {
        coolingSolenoid = new Solenoid(0, 0);
     //addChild("CoolingSolenoid",coolingSolenoid);
        coolingSolenoid.set(true);
    }
}