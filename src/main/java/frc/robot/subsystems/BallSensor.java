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
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.commands.*;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Subsystem;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.PIDSource;

import javax.print.attribute.standard.OutputDeviceAssigned;

import com.fasterxml.jackson.databind.ser.impl.FailingSerializer;

// BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=IMPORTS
import edu.wpi.first.wpilibj.DigitalInput;

// END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=IMPORTS


/**
 *
 */
public class BallSensor implements Subsystem {

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTANTS

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTANTS

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
private DigitalInput ballIn;
private DigitalInput ballOut;
boolean in = false;
boolean out = false;
private static int output = 1;
private static int GainedTemp = 1;
private static int LostTemp = 1;
private static int GainedBalls = 0;
private static int LostBalls = 0;

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS

    

    public BallSensor() {

        
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS
ballIn = new DigitalInput(0);


        
ballOut = new DigitalInput(1);


setDefaultCommand(new BallCounter(this));


        // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS
    }
    

    @Override
    public void periodic() {
        // Put code here to be run every loop
       

   
 
    
    


    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CMDPIDGETTERS


    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CMDPIDGETTERS

    // Put methods for controlling this subsystem
    // here. Call these from Commands.

}
public void setBallsgained(){
    GainedTemp = 1;
    LostTemp = 1;
    GainedBalls = 0;
    LostBalls = 0;
}
public void countballs(){
    if (ballIn.get() == true){
        GainedBalls = GainedTemp;
    }
    else if (GainedBalls == GainedTemp &&  ballIn.get() == false)GainedTemp++;

   
if (ballOut.get() == false){
        LostBalls = LostTemp;
    }
    else if (LostBalls == LostTemp &&  ballOut.get() == true) LostTemp++;




output = GainedBalls - LostBalls;
if(output < 0){
    output= 0;
}
System.out.println(output);
    SmartDashboard.putNumber("Balls: ",output);

}
}

