package frc.robot.commands;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Robot;
import frc.robot.subsystems.Lights;


/**
 *
 */
public class TurnOffLights extends CommandBase {
    private Lights m_lights;
   
    public TurnOffLights(Lights lights) {
        m_lights = lights;
    
        addRequirements(m_lights);
        

    }

    //FIXME: If it's an empty method, remove it
    // Called just before this Command runs the first time
    @Override
    public void initialize() {
       
    }

    // Called repeatedly when this Command is scheduled to run
    @Override
    public void execute() {
        m_lights.onStop();
    }

    // Make this return true when this Command no longer needs to run execute()
    @Override
    public boolean isFinished() {
        return false;
    }

    //FIXME: If it's an empty method, remove it
    // Called once after isFinished returns true
    @Override
    public void end(boolean interrupted) {
        
    }

    //FIXME: Remove excess comments
    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    
}
