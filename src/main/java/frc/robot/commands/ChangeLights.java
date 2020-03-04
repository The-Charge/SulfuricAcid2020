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
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Robot;
import frc.robot.subsystems.Lights;


/**
 *
 */
public class ChangeLights extends CommandBase {
    private Lights m_lights;
   
    public ChangeLights(Lights lights) {
        m_lights = lights;
    
        addRequirements(m_lights);
        

    }

    // Called just before this Command runs the first time
    @Override
    public void initialize() {
        m_lights.onStart();
    }

    // Called repeatedly when this Command is scheduled to run
    @Override
    public void execute() {
        m_lights.changeLight();
    }

    // Make this return true when this Command no longer needs to run execute()
    @Override
    public boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    @Override
    public void end(boolean interrupted) {
        m_lights.onStop();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    
}