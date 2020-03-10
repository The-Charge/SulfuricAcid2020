package frc.robot.commands;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Robot;
import frc.robot.RobotContainer;
import frc.robot.subsystems.BallSensor;
import edu.wpi.first.wpilibj2.command.CommandBase;

public class BallDetector extends CommandBase {

    private final BallSensor m_BallSensor;

    public BallDetector(BallSensor ballSensor) {
m_BallSensor= ballSensor;

        addRequirements(m_BallSensor);
    }

    // Called just before this Command runs the first time
    @Override
    public void initialize() { 
        m_BallSensor.setBallsgained();
    }


    //FIXME: If it's an empty method, remove it.
    // Called repeatedly when this Command is scheduled to run
    @Override
    public void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    @Override
    public boolean isFinished() {
        return true;
    }

    //FIXME: If it's an empty method, remove it.
    // Called once after isFinished returns true
    public void end() { 

    
       }

    //FIXME: Clean up excess comments
    // Called when another command which requires one or more of the same

}
