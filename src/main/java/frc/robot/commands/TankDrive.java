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
import frc.robot.MathUtil;
import frc.robot.Robot;
import frc.robot.subsystems.Drivetrain;
import frc.robot.RobotContainer;

/**
 *
 */
public class TankDrive extends CommandBase {

    public final Drivetrain m_subsystem;

    public TankDrive(Drivetrain subsystem) {
        m_subsystem = subsystem;
        // Use addRequirements() here to declare subsystem dependencies.
        addRequirements(subsystem);
      }

    // Called just before this Command runs the first time
    @Override
    public void initialize() {
        // FIXME: initializeMotors takes care of setting up invert/follow/etc. 
        // Remove it from here.
        m_subsystem.initializeMotors();
        m_subsystem.setPercentVBus();
    }

    // Called repeatedly when this Command is scheduled to run
    @Override
    public void execute() {
        double leftSpeed, rightSpeed;
        rightSpeed = -RobotContainer.rightJoystick.getY();
        leftSpeed = -RobotContainer.leftJoystick.getY();

        rightSpeed = MathUtil.adjSpeed(rightSpeed);
        leftSpeed = MathUtil.adjSpeed(leftSpeed);

        //FIXME: Use better names for smartdashboard keys
        // Would strongly recommend starting with class name. IE: DriveTrain left speed set
        // Prevents key collision and helps speed up debugging
        SmartDashboard.putNumber("leftSpeed", leftSpeed);
        SmartDashboard.putNumber("rightSpeed", rightSpeed);


        m_subsystem.run(leftSpeed, rightSpeed);
        //m_subsystem.tankDriveVolts(leftSpeed, rightSpeed);
  
    }

    // Make this return true when this Command no longer needs to run execute()
    @Override
    public boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    @Override
    public void end(boolean interrupted) {
        m_subsystem.stop();
    }
}
