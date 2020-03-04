package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Robot;
import frc.robot.subsystems.Climber;

/**
 *
 */
public class ClimberUnBrake extends CommandBase {

    
    private Climber m_climber;
    public ClimberUnBrake(Climber climber) {

       
        m_climber = climber;
        
        addRequirements(m_climber);

    }

    // Called just before this Command runs the first time
    @Override
    public void initialize() {
        m_climber.disengageBrake();
    }

    // Called repeatedly when this Command is scheduled to run
    @Override
    public void execute() {
        
    }

    // Make this return true when this Command no longer needs to run execute()
    @Override
    public boolean isFinished() {
        return true;
    }

    // Called once after isFinished returns true
    @Override
    public void end(boolean interrupted) {
        
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    
}