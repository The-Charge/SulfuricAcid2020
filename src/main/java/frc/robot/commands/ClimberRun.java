package frc.robot.commands;
 
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Robot;
import frc.robot.subsystems.Climber;
 
/**
 *
 */
public class ClimberRun extends CommandBase {
 
    private double m_power;
    private Climber m_climber;
    public ClimberRun(Climber climber, double power) {
 
        m_power = power;
        m_climber = climber;
        
        addRequirements(m_climber);
 
    }
 
    // Called just before this Command runs the first time
    @Override
    public void initialize() {
        m_climber.setPercentVBus();
        
    }
 
    // Called repeatedly when this Command is scheduled to run
    @Override
    public void execute() {
        m_climber.set(m_power);
 
        m_climber.limitCurrent();
    }
 
    // Make this return true when this Command no longer needs to run execute()
    @Override
    public boolean isFinished() {
        return false;
    }
 
    // Called once after isFinished returns true
    @Override
    public void end(boolean interrupted) {
        m_climber.stopMotor();
    }
 
    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
}
 
