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
import frc.robot.subsystems.Shifters;

/**
 *
 */
public class ShiftLow extends CommandBase {

    
    private final Shifters m_subsystem;

    public ShiftLow(Shifters subsystem) {
        m_subsystem = subsystem;
        // Use addRequirements() here to declare subsystem dependencies.
        addRequirements(m_subsystem);
      }

    // Called just before this Command runs the first time
    @Override
    public void initialize() {
        m_subsystem.shiftLow();
    }

    //FIXME: If it's an empty method, remove it.
    // Called repeatedly when this Command is scheduled to run
    @Override
    public void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    @Override
    public boolean isFinished() {
        return false;
    }

    //FIXME: If it's an empty method, remove it.
    // Called once after isFinished returns true
    @Override
    public void end(boolean interrupted) {
    }

}
