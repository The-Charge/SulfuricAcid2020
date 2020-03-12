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
import frc.robot.subsystems.Turret;

/**
 *
 */
public class RunTurretVision extends CommandBase {
    private static final double H_TOLERANCE = 1;

    private double[] visionResults;
    private double horizontalAngle;
    private double verticalAngle;
    private double alignmentAngle;
    private double distance;
    private final Turret m_turret;
    
    public RunTurretVision(Turret turret, double vertical) {
        m_turret = turret;
        verticalAngle = vertical;
        addRequirements(m_turret);
    }

    // Called just before this Command runs the first time
    @Override
    public void initialize() {
        m_turret.enableVision();
        visionResults = new double[] {0, 0};
    }

    // Called repeatedly when this Command is scheduled to run
    @Override
    public void execute() {
        // Result key:
        //  0. timestamp
        //  1. success (0/1)
        //  2. distance
        //  3. horizontal angle
        //  4. vertical angle
        //  5. alignment angle
        //  6. inner port (0/1)
        //  7. instantaneous FPS
        //FIXME: May want to consider moving the vision processing to it's own subsystem (in periodic())
        // That way, you can see/test values without having to take control of the turret
        visionResults = SmartDashboard.getNumberArray("Vision/result", new double[] {0, 0});
        if (visionResults[1] == 0) {
            SmartDashboard.putString("Vision Status", "none");
        } else {
            distance = visionResults[2];
            horizontalAngle = visionResults[3];
            SmartDashboard.putNumber("Horizontal Angle", horizontalAngle);
            double nothing = visionResults[4];
            alignmentAngle = visionResults[5];

            m_turret.gotoHorizontalAngle(horizontalAngle);
            //m_turret.setVerticalAngle(verticalAngle);
           

            if (Math.abs(horizontalAngle) < H_TOLERANCE) {
                SmartDashboard.putString("Vision Status", "locked");
            } else {
                SmartDashboard.putString("Vision Status", "homing");
            }
        }
        m_turret.setRawVertical(verticalAngle);
    }

    // Make this return true when this Command no longer needs to run execute()
    @Override
    public boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    @Override
    public void end(boolean interrupted) {
        m_turret.disableVision();
        SmartDashboard.putString("Vision Status", "disabled");
    }
}
