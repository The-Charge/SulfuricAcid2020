package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Robot;
import frc.robot.subsystems.Stopper;
import frc.robot.subsystems.Indexer;
import frc.robot.subsystems.Shooter;

public class CloseStopper extends CommandBase {

    private final Stopper m_stopper;
    private final Indexer m_indexer;
   
    public CloseStopper(Stopper stopper, Indexer indexer) {

    m_stopper = stopper;
    m_indexer = indexer;
        addRequirements(m_stopper, m_indexer);
    }

    // Called repeatedly when this Command is scheduled to run
    @Override
    public void execute() {
      m_indexer.setPercentSpeedPID(-0.2, true);
    }

    // Make this return true when this Command no longer needs to run execute()
    @Override
    public boolean isFinished() {
      return !m_stopper.ballSensedOut();
    }

    // Called once after isFinished returns true
    @Override
    public void end(boolean interrupted) {
        m_stopper.closeStopper();
        m_indexer.stop();
    }
}
