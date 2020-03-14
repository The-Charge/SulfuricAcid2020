package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import frc.robot.subsystems.*;

public class InitShootState extends ParallelCommandGroup {

    public Shooter m_shooter;

    public InitShootState (Shooter snooter)
    {
        m_shooter = snooter;
    }
    public void addCommands()
    {
        new Shoot(m_shooter, 0);

    }
}
