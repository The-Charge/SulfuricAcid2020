package frc.robot.triggers;

import edu.wpi.first.wpilibj2.command.button.Trigger;
import frc.robot.subsystems.Indexer;

public class IntakeOnly extends Trigger {

    private Indexer m_indexer;

    public IntakeOnly(Indexer indexer)
    {
        m_indexer = indexer;
    }

    @Override
    public boolean get() {
      // This returns whether the trigger is active
      return !m_indexer.ballSensedIn();
    }
  }