package frc.robot.triggers;

import edu.wpi.first.wpilibj2.command.button.Trigger;
import frc.robot.subsystems.Shooter;

public class ShootTrigger extends Trigger {

    private Shooter m_shooter;

    public ShootTrigger(Shooter shooter)
    {
        m_shooter = shooter;
    }

    @Override
    public boolean get() {
      // This returns whether the trigger is active
      return m_shooter.isAtSpeed();
    }
  }