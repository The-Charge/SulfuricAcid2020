//DriveXFeet doesn't work in Suflfuric Acid (only works in older version)
package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Robot;
import frc.robot.subsystems.Drivetrain;

public class DriveXFeetMM extends CommandBase {

    private int m_velocity;
    private int m_acceleration;
    private double m_distance;

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


    public DriveXFeetMM(int velocity, int acceleration, double distance, Drivetrain subsystem) {
        m_velocity = velocity;
        m_acceleration = acceleration;
        m_distance = distance;

        m_subsystem = subsystem;
        addRequirements(m_subsystem);
    }

    // Called just before this Command runs the first time
    @Override
    public void initialize() {
        // Robot.drivetrain.ResestEncoder();

        m_subsystem.MotionMagicInit(m_distance);

        // else -- not necessary but may be used later during the season
        // Robot.drivetrain.MotionMagicInit(m_distance, m_velocity, m_acceleration);

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
