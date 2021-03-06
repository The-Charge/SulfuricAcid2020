package frc.robot.subsystems;

import frc.robot.commands.*;
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

boolean in = false;
boolean out = false;
private static int output = 1;
private static int GainedTemp = 1;
private static int LostTemp = 1;
private static int GainedBalls = 0;
private static int LostBalls = 0;
public Indexer m_indexer;
public Stopper m_stopper;

    

    public BallSensor(Indexer indexer, Stopper stopper) {
    m_indexer = indexer;
    m_stopper = stopper;
    }
    

    @Override
    public void periodic() {
        // Put code here to be run every loop
        countballs();
}

public void setBallsgained(){
    GainedTemp = 1;
    LostTemp = 1;
    GainedBalls = 0;
    LostBalls = 0;
}
public void countballs(){
    if (m_indexer.ballSensedIn()){
        GainedBalls = GainedTemp;
    }
    else if (GainedBalls == GainedTemp &&  m_indexer.ballSensedIn() == false)GainedTemp++;

   
if (!m_stopper.ballSensedOut()){
        LostBalls = LostTemp;
    }
    else if (LostBalls == LostTemp && m_stopper.ballSensedOut())LostTemp++;




output = GainedBalls - LostBalls;
if(output < 0){
    output= 0;
}
//FIXME: DO NOT USE STDOUT!
System.out.println(output);
    //FIXME: Use better names for smartdashboard keys
    // Would strongly recommend starting with class name. IE: DriveTrain left speed set
    // Prevents key collision and helps speed up debugging
    SmartDashboard.putNumber("Balls: ",output);

}
public static int getOutput()
{
    return output;
}
}

