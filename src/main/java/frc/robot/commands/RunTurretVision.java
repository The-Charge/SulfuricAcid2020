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
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Robot;
import frc.robot.subsystems.Turret;


/**
 *
 */
public class RunTurretVision extends CommandBase {
    private static final double ALIGNMENT_RANGE_INNER = 3;
    private static final double ALIGNMENT_RANGE_OUTER = 10;

    private double[] visionResults;
    private double horizontalAngle;
    private double verticalAngle;
    private double alignmentAngle;
    private double distance;

    private Turret m_turret = new Turret();

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=VARIABLE_DECLARATIONS
 
    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=VARIABLE_DECLARATIONS

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTOR
    public RunTurretVision() {

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTOR
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=VARIABLE_SETTING

        // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=VARIABLE_SETTING
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=REQUIRES
        addRequirements(m_turret);

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=REQUIRES
    }

    // Called just before this Command runs the first time
    @Override
    public void initialize() {
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
        visionResults = SmartDashboard.getNumberArray("Vision/result", new double[] {0, 0});
        if (visionResults[1] == 0) {
            SmartDashboard.putString("Vision/valid_shot", "none");
            return;
        }

        distance = visionResults[2];
        horizontalAngle = visionResults[3];
        verticalAngle = visionResults[4];
        alignmentAngle = visionResults[5];

        m_turret.setAngleRelative(horizontalAngle);

        if (m_turret.atAngle(horizontalAngle)) {
            if (alignmentAngle < ALIGNMENT_RANGE_INNER) {
                SmartDashboard.putString("Vision/valid_shot", "inner");
            } else if (alignmentAngle < ALIGNMENT_RANGE_OUTER) {
                SmartDashboard.putString("Vision/valid_shot", "outer");
            } else {
                SmartDashboard.putString("Vision/valid_shot", "locked but none");
            }
        } else {
            SmartDashboard.putString("Vision/valid_shot", "homing but none");
        }
    }

    // Make this return true when this Command no longer needs to run execute()
    @Override
    public boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    @Override
    public void end(boolean interrupted) {
        m_turret.stop();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    
}
