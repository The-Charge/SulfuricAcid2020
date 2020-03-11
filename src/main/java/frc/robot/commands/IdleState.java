package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.CommandGroupBase;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import frc.robot.Robot;
import frc.robot.subsystems.*;

public class IdleState extends ParallelCommandGroup {

    public Intake m_intake;
    public Indexer m_indexer;
    public Stopper m_stopper;
    public Shooter m_shooter;

    public IdleState (Intake intake, Indexer indexer, Stopper stooper, Shooter snooter)
    {
        m_intake = intake;
        m_indexer = indexer;
        m_stopper = stooper;
        m_shooter = snooter;
    }
    public void addCommands​(Command... commands)
    {
        new Index(m_indexer, 0);
        new RunIntake(m_intake, 0);
        new CloseStopper(m_stopper, m_indexer); 
        new Shoot(m_shooter, 0);

    }
}
