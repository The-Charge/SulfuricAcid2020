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

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Robot;
import frc.robot.RobotContainer;
import frc.robot.subsystems.Turret;

/**
 *
 */
public class RunTurretManual extends CommandBase {
    private double turretHorizontal, turretVertical;
    private final Turret m_turret;
    
    public RunTurretManual(Turret turret) {
        m_turret = turret;
        addRequirements(m_turret);
    }

    // Called just before this Command runs the first time
    @Override
    public void initialize() {
        
    }

    // Called repeatedly when this Command is scheduled to run
    @Override
    public void execute() {
        turretHorizontal = -RobotContainer.buttonBox.getY();
        m_turret.runHorizontalManual(turretHorizontal);

        turretVertical = RobotContainer.buttonBox.getX();
        turretVertical = (1 + turretVertical) / 2;
        turretVertical = .4 * turretVertical + .4;
        m_turret.setRawVertical(turretVertical);
    }

    // Make this return true when this Command no longer needs to run execute()
    @Override
    public boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    @Override
    public void end(boolean interrupted) {
        m_turret.stopHorizontal();
    }
}
