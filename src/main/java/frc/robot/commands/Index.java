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
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Robot;
import frc.robot.RobotContainer;
import frc.robot.subsystems.Indexer;

/**
 *
 */
public class Index extends CommandBase {

    private double m_speed;
    private final Indexer m_indexer;
    private boolean shooterOpen = false;

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=VARIABLE_DECLARATIONS
    //@param Indexer;
    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTOR
    public Index(Indexer indexer, double speed) {

        m_indexer = indexer;
        m_speed = speed;
        addRequirements(m_indexer);

    }

    public Index(Indexer indexer, double speed, boolean open) {

        m_indexer = indexer;
        m_speed = speed;
        shooterOpen = open;
        addRequirements(m_indexer);

    }

    // Called just before this Command runs the first time
    @Override
    public void initialize() {
        m_indexer.initalizeMotors();
        m_indexer.initSpeedMode();
    }

    // Called repeatedly when this Command is scheduled to run
    @Override
    public void execute() {
        m_indexer.setPercentSpeedPID(m_speed, shooterOpen);
        //SmartDashboard.putNumber("Y AXIS VAL", yAxisVal);
    }

    // Make this return true when this Command no longer needs to run execute()
    @Override
    public boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    @Override
    public void end(boolean interrupted) {
        m_indexer.stop();
       // Robot.indexer.setPercentVBus();
    }
}

    //FIXME: Remove excess comments
    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run

