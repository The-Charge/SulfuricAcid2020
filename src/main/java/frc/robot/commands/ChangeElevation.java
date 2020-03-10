
package frc.robot.commands;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Robot;
import frc.robot.RobotContainer;
import frc.robot.subsystems.TurretElevation;

public class ChangeElevation extends CommandBase {
    private double m_turretVertical;
    private final TurretElevation m_turret;
    
    public ChangeElevation(TurretElevation turret, double turretVertical) {
        m_turret = turret;
        m_turretVertical = turretVertical;
        addRequirements(m_turret);
    }
    // Called repeatedly when this Command is scheduled to run
    @Override
    public void execute() {
        m_turret.setRawVertical(m_turretVertical);
    }

    // Make this return true when this Command no longer needs to run execute()
    @Override
    public boolean isFinished() {
        return false;
    }
}
