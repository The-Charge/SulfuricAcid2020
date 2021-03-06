// RobotBuilder Version: 2.0
//
// This file was generated by RobotBuilder. It contains sections of
// code that are automatically generated and assigned by robotbuilder.
// These sections will be updated in the future when you export to
// Java from RobotBuilder. Do not put any code or make any change in
// the blocks indicating autogenerated code or it will be lost on an
// update. Deleting the comments indicating the section will prevent
// it from being updated in the future.


package frc.robot.commands;

import frc.robot.subsystems.BallSensor;
import edu.wpi.first.wpilibj2.command.CommandBase;


/**
 *
 */
//FIXME: Would be better to have the subsystem handle it's own counting in it's periodic() method
// If you do, get rid of this command
public class BallCounter extends CommandBase {

    private final BallSensor m_BallSensor;

    public BallCounter(BallSensor ballSensor) {
        m_BallSensor= ballSensor;    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTOR
        addRequirements(m_BallSensor);
    }

    //FIXME: If it's an empty method, remove it.
    // Called just before this Command runs the first time
    @Override
    public void initialize() { 
    }



    // Called repeatedly when this Command is scheduled to run
    @Override
    public void execute() {
        m_BallSensor.countballs();
    }

    // Make this return true when this Command no longer needs to run execute()
    @Override
    public boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    
    //FIXME: If it's an empty method, remove it.
    public void end() { 

    
       }

    //FIXME: Clean up excess comments
    // Called when another command which requires one or more of the same

}
