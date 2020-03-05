// RobotBuilder Version: 2.0
//
// This file was generated by RobotBuilder. It contains sections of
// code that are automatically generated and assigned by robotbuilder.
// These sections will be updated in the future when you export to
// Java from RobotBuilder. Do not put any code or make any change in
// the blocks indicating autogenerated code or it will be lost on an
// update. Deleting the comments indicating the section will prevent
// it from being updated in the future.


//DriveXFeet doesn't work in Suflfuric Acid (only works in older version)
package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Robot;
import frc.robot.subsystems.Drivetrain;


/**
 *
 */
public class DriveXFeetMM extends CommandBase {

    //FIXME: m_velocity and m_acceleration aren't used in the code.
    // Use them or remove them.
    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=VARIABLE_DECLARATIONS
    private int m_velocity;
    private int m_acceleration;
    private double m_distance;

    
 
    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=VARIABLE_DECLARATIONS

    private final Drivetrain m_subsystem;

    public DriveXFeetMM(double distance, Drivetrain subsystem)
    {
        //FIXME: DRY (don't repeat yourself)
        // Call your other constructor using constructor chaining
        // https://www.geeksforgeeks.org/constructor-chaining-java-examples/
        m_distance = distance;
        m_subsystem = subsystem;
        addRequirements(m_subsystem);
    }

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTOR
    public DriveXFeetMM(int velocity, int acceleration, double distance, Drivetrain subsystem) {

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTOR
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=VARIABLE_SETTING
        m_velocity = velocity;
        m_acceleration = acceleration;
        m_distance = distance;

        m_subsystem = subsystem;
        addRequirements(m_subsystem);

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=REQUIRES
    }

    // Called just before this Command runs the first time
    @Override
    public void initialize() {
        // Robot.drivetrain.ResestEncoder();

        m_subsystem.MotionMagicInit(m_distance);

        // else -- not necessary but may be used later during the season
        // Robot.drivetrain.MotionMagicInit(m_distance, m_velocity, m_acceleration);

    }

    //FIXME: If it's an empty method, remove it.
    // Called repeatedly when this Command is scheduled to run
    @Override
    public void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    @Override
    public boolean isFinished() {
        return m_subsystem.isAtPIDDestination();
    }

    // Called once after isFinished returns true
    @Override
    public void end(boolean interrupted) {
        m_subsystem.stop();
    }

}
