package frc.robot.triggers;

import edu.wpi.first.wpilibj2.command.button.Trigger;
import frc.robot.subsystems.Stopper;

public class TopBall extends Trigger {

    private Stopper m_stopper;

    public TopBall(Stopper stopper)
    {
        m_stopper = stopper;
    }

    @Override
    public boolean get() {
      // This returns whether the trigger is active
      return m_stopper.ballSensedOut();
    }
  }