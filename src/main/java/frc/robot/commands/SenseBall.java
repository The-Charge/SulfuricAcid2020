package frc.robot.commands;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Stopper;

public class SenseBall extends CommandBase {
    private final Stopper m_stopper;

    public SenseBall(Stopper stopper) {

        m_stopper = stopper;
        addRequirements(m_stopper);
        
    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=REQUIRES
    }

    // Called repeatedly when this Command is scheduled to run
    @Override
    public void execute() {
        m_stopper.ballSensedOut();
    }

    // Make this return true when this Command no longer needs to run execute()
    @Override
    public boolean isFinished() {
        return false;
    }
}
