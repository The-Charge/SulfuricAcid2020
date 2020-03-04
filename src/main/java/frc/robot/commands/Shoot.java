// RobotBuilder Version: 2.0
//
// This file was generated by RobotBuilder. It contains sections of
// code that are automatically generated and assigned by robotbuilder.
// These sections will be updated in the future when you export to
// Java from RobotBuilder. Do not put any code or make any change in
// the blocks indicating autogenerated code or it will be lost on an
// update. Deleting the comments indicating the section will prevent
// it from being updated in the future.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Robot;
import frc.robot.RobotContainer;
import frc.robot.subsystems.Shooter;

/**
 *
 */
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
        //m_speed = RobotContainer.buttonBox.getRawAxis(2);
        //m_speed = (m_speed + 1)/2;
        m_shooter.setPercentSpeedPID(m_speed);

        SmartDashboard.putNumber("ShooterSpeed", m_speed);

    }

    // Make this return true when this Command no longer needs to run execute()
    @Override
    public boolean isFinished() {
        return  m_shooter.isAtSpeed(m_speed);
        //return false;
    }

    // Called once after isFinished returns true
    @Override
    public void end(boolean interrupted) {
        //m_shooter.stop();
    }

    
}
