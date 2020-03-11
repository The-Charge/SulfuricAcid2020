package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.Subsystem;
import edu.wpi.first.wpilibj.Solenoid;

public class Cooling implements Subsystem {
    /*
    The cooling subsystem cools down Falcons.
    */
    private Solenoid coolingSolenoid;

    public Cooling() {
        coolingSolenoid = new Solenoid(0, 0);
     //addChild("CoolingSolenoid",coolingSolenoid);
        coolingSolenoid.set(true);
    }
}