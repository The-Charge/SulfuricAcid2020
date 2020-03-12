package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.RobotContainer;
import frc.robot.subsystems.Shooter;

public class Shoot extends CommandBase {

    private double m_speed;
    private final Shooter m_shooter;

    public Shoot(Shooter shooter, double speed) {
        m_shooter = shooter;
        m_speed = speed;
        addRequirements(m_shooter);
    }

    // Called just before this Command runs the first time
    @Override
    public void initialize() {
        m_shooter.initSpeedMode();
    }

    // Called repeatedly when this Command is scheduled to run
    @Override
    public void execute() {
        if (RobotContainer.Xbox.getBackButtonPressed()){
            m_speed += m_shooter.XBOX_INCREASE_DECREASE_SHOOTER_SPEED;
        }
        if (RobotContainer.Xbox.getStartButtonPressed()){
            m_speed -= m_shooter.XBOX_INCREASE_DECREASE_SHOOTER_SPEED;
        }
        m_shooter.setPercentSpeedPID(m_speed);

        SmartDashboard.putNumber("ShooterSpeed", m_speed);
    }

    // Make this return true when this Command no longer needs to run execute()
    @Override
    public boolean isFinished() {
        return  m_shooter.isAtSpeed(m_speed);
    }
}
