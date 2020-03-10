package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.MathUtil;
import frc.robot.Robot;
import frc.robot.RobotContainer;
import frc.robot.subsystems.Drivetrain;

public class HalfSpeed extends TankDrive {

    public HalfSpeed(Drivetrain subsystem) {
        super(subsystem);
    }

    // Called repeatedly when this Command is scheduled to run
    @Override
    public void execute() {
        double leftSpeed, rightSpeed;
        rightSpeed = -RobotContainer.rightJoystick.getY() * 0.5;
        leftSpeed = -RobotContainer.leftJoystick.getY() * 0.5;
        rightSpeed = MathUtil.adjSpeed(rightSpeed);
        leftSpeed = MathUtil.adjSpeed(leftSpeed);

        /*SmartDashboard.putNumber("Drivetrain HalfSpeedL", leftSpeed);
        SmartDashboard.putNumber("Drivetrain HalfSpeedR", rightSpeed);
        */
        m_subsystem.run(leftSpeed, rightSpeed);
    }

    // Make this return true when this Command no longer needs to run execute()
    @Override
    public boolean isFinished() {
        return false;
    }
}
