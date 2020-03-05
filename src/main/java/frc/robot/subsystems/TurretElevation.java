package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.Relay.Value;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Subsystem;
import frc.robot.commands.RunTurretManual;
import frc.robot.commands.RunTurretVision;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;



/**
 *
 */
public class TurretElevation implements Subsystem {
    private static final int H_MIN_ENCODER_TICKS = -482070;  // used to stop turret from rotating past ends
    private static final int H_MAX_ENCODER_TICKS = 484191;
    private static final double H_DEGREES_PER_TICK = 0;
    private static final double H_MIN_DEGREES = H_MIN_ENCODER_TICKS * H_DEGREES_PER_TICK;
    private static final double H_MAX_DEGREES = H_MAX_ENCODER_TICKS * H_DEGREES_PER_TICK;
    private static final double H_TOLERANCE = 0.5;

    //Constants aquired from CAD team used for trig calculations (millimeters):
    public static final double TURRET_SIDE_A = 244.475;
    public static final double TURRET_SIDE_B = 369.4176;
    private final double V_MIN_ANGLE = 20.0; //34.4;
    private final double V_MAX_ANGLE = 57.4;
    private final double V_TOLERANCE = 0.01;

    private static final int TIMEOUT_MS = 10;

    private double actuatorDistance;

    private final Servo elevationServo;

    public TurretElevation() {    
        elevationServo = new Servo(0);
    }

    @Override
    public void periodic() {
        SmartDashboard.putNumber("Turret Elevation", elevationServo.get());
    }
    
    private double calcActuatorDistance(final double angle) {
        // Running law of cosines on the turret
        //FIXME: Would recommend breaking up into terms. ie: double a_term = (part A); double b_term = (part B)
        // Makes it a bit easier to tell it's the law of cosines.
        double d = Math.sqrt(Math.pow(Turret.TURRET_SIDE_A, 2) + Math.pow(Turret.TURRET_SIDE_B, 2) - 2 * Turret.TURRET_SIDE_A * Turret.TURRET_SIDE_B * Math.cos(Math.toRadians(94.4 - angle)));
    
        //FIXME: Whoever put the comments, great job.
        // Go the next step though, and move them to class level constants as they're not changing.
        // This line subtracts the length of the actuator while not extended
        d -= 218;  // 218 is what the actuator blueprints says is the "Closed Length (hole to hole)"
        // This line changes the normalization from 0-140 to 0-1
        d /= 140;  // 140 is what the actuator blueprints says is the max the actuator can extend from the base
        return d;
    }

    public void setVerticalAngle(double setpoint) {
        if(setpoint > V_MAX_ANGLE) {
            setpoint = V_MAX_ANGLE;
        } else if(setpoint < V_MIN_ANGLE) {
            setpoint = V_MIN_ANGLE;
        }
        
        actuatorDistance = calcActuatorDistance(setpoint);
        elevationServo.set(actuatorDistance);
    }

    public void setRawVertical(double setpoint) {
        elevationServo.set(setpoint);
    }

    public boolean atVerticalAngle(final double angle) {
        return (Math.abs(elevationServo.get() - calcActuatorDistance(angle)) <= V_TOLERANCE);
    }

}