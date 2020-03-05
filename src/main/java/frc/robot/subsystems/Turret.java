// RobotBuilder Version: 2.0
//
// This file was generated by RobotBuilder. It contains sections of
// code that are automatically generated and assigned by robotbuilder.
// These sections will be updated in the future when you export to
// Java from RobotBuilder. Do not put any code or make any change in
// the blocks indicating autogenerated code or it will be lost on an
// update. Deleting the comments indicating the section will prevent
// it from being updated in the future.

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
// BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=IMPORTS
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=IMPORTS


/**
 *
 */
public class Turret implements Subsystem {
    private static final double H_MIN_ENCODER_TICKS = -482070.0;  // used to stop turret from rotating past ends
    private static final double H_MAX_ENCODER_TICKS = 484191.0;
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

    private final WPI_TalonSRX turretMotor;
    private final Servo elevationServo;
    private final Relay visionLights;

    public Turret() {    
        turretMotor = new WPI_TalonSRX(7);
        elevationServo = new Servo(0);
        visionLights = new Relay(0);

        turretMotor.set(ControlMode.PercentOutput, 0);
        turretMotor.setSelectedSensorPosition(0);
        turretMotor.setNeutralMode(NeutralMode.Brake);

        //setDefaultCommand(new RunTurretManual(this));
        setDefaultCommand(new RunTurretVision(this));
    }

    @Override
    public void periodic() {
        SmartDashboard.putNumber("Turret Elevation", elevationServo.get());
        SmartDashboard.putNumber("Turret Rotation", getCurrentHorizontalAngle());
        checkHorizontalLimitSwitches();
    }
    
    public void enableVision() {
        visionLights.set(Value.kForward);
    }

    public void disableVision() {
        visionLights.set(Value.kReverse);
    }

    private double calcActuatorDistance(final double angle) {
        // Running law of cosines on the turret
        double d = Math.sqrt(Math.pow(Turret.TURRET_SIDE_A, 2) + Math.pow(Turret.TURRET_SIDE_B, 2) - 2 * Turret.TURRET_SIDE_A * Turret.TURRET_SIDE_B * Math.cos(Math.toRadians(94.4 - angle)));
    
        // This line subtracts the length of the actuator while not extended
        d -= 218;  // 218 is what the actuator blueprints says is the "Closed Length (hole to hole)"
        // This line changes the normalization from 0-140 to 0-1
        d /= 140;  // 140 is what the actuator blueprints says is the max the actuator can extend from the base
        return d;
    }

    public void checkHorizontalLimitSwitches() {
        if (turretMotor.getSensorCollection().isRevLimitSwitchClosed()) {
            turretMotor.setSelectedSensorPosition((int)H_MIN_ENCODER_TICKS, 0, TIMEOUT_MS);
        } else if (turretMotor.getSensorCollection().isFwdLimitSwitchClosed()) {
            turretMotor.setSelectedSensorPosition((int)H_MAX_ENCODER_TICKS, 0, TIMEOUT_MS);
        }
    }

    public boolean atLimitSwitch() {
        return (turretMotor.getSensorCollection().isRevLimitSwitchClosed()
                || turretMotor.getSensorCollection().isFwdLimitSwitchClosed());
    }

    public void gotoHorizontalAngle(double setpoint) {
        SmartDashboard.putNumber("Angle Offset", setpoint);
        SmartDashboard.putBoolean("Valid Turret Rotation",
            getCurrentHorizontalAngle() + setpoint < H_MIN_DEGREES
            || getCurrentHorizontalAngle() + setpoint > H_MAX_DEGREES
        );

        if (Math.abs(setpoint) > H_TOLERANCE) {
            double percent = Math.abs(setpoint) / 40;
            percent = Math.max(0.06, Math.min(0.19, percent));
            if (setpoint < 0) { percent = -percent; }
            turretMotor.set(ControlMode.PercentOutput, percent);
        } else {
            turretMotor.set(ControlMode.PercentOutput, 0);
        }
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
        // Use these to adjust 0-1 to actuall 0-1 on the servo
        //setpoint *= 0.6;
        //setpoint += 0.2;
        elevationServo.set(setpoint);
    }

    public boolean atVerticalAngle(final double angle) {
        return (Math.abs(elevationServo.get() - calcActuatorDistance(angle)) <= V_TOLERANCE);
    }

    public void stopHorizontal() {
        turretMotor.set(ControlMode.PercentOutput, 0);
    }

    public boolean runHorizontalManual(double target) {
        double ticks = turretMotor.getSelectedSensorPosition();
        SmartDashboard.putNumber("Position", ticks);
        if (ticks < 0) {
            ticks /= Math.abs(H_MIN_ENCODER_TICKS);
        } else {
            ticks /= Math.abs(H_MAX_ENCODER_TICKS);
        }
        // SmartDashboard

        double error = target - ticks;
        double speed = 0.2;
        SmartDashboard.putNumber("Normal", ticks);
        SmartDashboard.putNumber("Target", target);
        if (Math.abs(error) > 0.03) {
            if (error < 0) { speed = -speed; }
            turretMotor.set(ControlMode.PercentOutput, speed);
            return false;
        } else {
            stopHorizontal();
            return true;
        }
    }

    public void setRawHorizontalPercent(double setpoint) {
        turretMotor.set(ControlMode.PercentOutput, setpoint);
    }

    public double getCurrentHorizontalAngle() {
        return turretMotor.getSelectedSensorPosition();
    }
}