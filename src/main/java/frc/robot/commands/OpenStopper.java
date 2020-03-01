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
import frc.robot.Robot;
import frc.robot.subsystems.Stopper;
import frc.robot.subsystems.Shooter;

/**
 *
 */
public class OpenStopper extends CommandBase {
    private final Stopper m_stopper;
    
    public OpenStopper(Stopper stopper) {

    m_stopper = stopper;
        addRequirements(m_stopper);
    }

    // Called just before this Command runs the first time
    @Override
    public void initialize() {
        m_stopper.openStopper();
    }

    // Called repeatedly when this Command is scheduled to run
    @Override
    public void execute() {

    }

    // Make this return true when this Command no longer needs to run execute()
    @Override
    public boolean isFinished() {
        return true;
    }

    // Called once after isFinished returns true
    @Override
    public void end(boolean interrupted) {

    }

}
