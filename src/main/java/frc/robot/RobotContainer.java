// RobotBuilder Version: 2.0
//
// This file was generated by RobotBuilder. It contains sections of
// code that are automatically generated and assigned by robotbuilder.
// These sections will be updated in the future when you export to
// Java from RobotBuilder. Do not put any code or make any change in
// the blocks indicating autogenerated code or it will be lost on an
// update. Deleting the comments indicating the section will prevent
// it from being updated in the future.

package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.commands.*;
import frc.robot.subsystems.*;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.*;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.subsystems.Drivetrain;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Filesystem;
import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj.controller.RamseteController;
import edu.wpi.first.wpilibj.controller.SimpleMotorFeedforward;
import edu.wpi.first.wpilibj.geometry.Pose2d;
import edu.wpi.first.wpilibj.geometry.Rotation2d;
import edu.wpi.first.wpilibj.geometry.Transform2d;
import edu.wpi.first.wpilibj.geometry.Translation2d;
import edu.wpi.first.wpilibj.trajectory.Trajectory;
import edu.wpi.first.wpilibj.trajectory.TrajectoryConfig;
import edu.wpi.first.wpilibj.trajectory.TrajectoryGenerator;
import edu.wpi.first.wpilibj.trajectory.TrajectoryUtil;
import edu.wpi.first.wpilibj.trajectory.constraint.DifferentialDriveVoltageConstraint;
import edu.wpi.first.wpilibj2.command.RamseteCommand;
import edu.wpi.first.wpilibj2.command.RunCommand;
import frc.robot.Constants.AutoConstants;
import frc.robot.Constants.DriveConstants;
import frc.robot.Constants.OIConstants;

import static edu.wpi.first.wpilibj.XboxController.Button;

/**
 * This class is where the bulk of the robot should be declared.  Since Command-based is a
 * Command-based is a "declarative" paradigm, very little robot logic should
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls).  Instead, the structure of the robot
 * (including subsystems, commands, and button mappings) should be declared here.
 */

public class RobotContainer {

  //SUBSYSTEMS
  public ControlPanel controlPanel = new ControlPanel();
  public ColorSensor colorSensor = new ColorSensor();
  public Intake intake = new Intake();
  public Drivetrain drivetrain = new Drivetrain();
  public Turret turret = new Turret();
  public Shifters shifters = new Shifters();
  public Climber climber = new Climber();
  public Indexer indexer = new Indexer();
  public BallSensor ballSensor = new BallSensor();
  public Shooter shooter = new Shooter(ballSensor);

  //JOYSTICKS
  public static Joystick leftJoystick;
  public static Joystick rightJoystick;
  public static Joystick buttonBox;

  //JOYSTICK BUTTONS
  public JoystickButton shiftHighWPBtn;
  public JoystickButton shiftHighWHBtn;
  public JoystickButton shiftLowBtn;
  public JoystickButton quarterSpeedBtn;
  public JoystickButton halfSpeedBtn;
  public JoystickButton toggleLockStraightBtn;
  public JoystickButton invertDriveBtn;
  public JoystickButton shootBtn;
  public JoystickButton manualElevation;
  public JoystickButton driveXFeetBtn;
  public JoystickButton climbUp;
  public JoystickButton indexBtn;
  public JoystickButton positionControlBtn;
  public JoystickButton rotationControlBtn;
  public JoystickButton runIntakeBtn;
  public JoystickButton runIntakeInverseBtn;
  public JoystickButton runIntakeIndexerBtn;
  public JoystickButton sensorColorBtn;
  public JoystickButton climbDown;

  public boolean realButtonBox = true;

   
    public RobotContainer() {
      if (realButtonBox) configureButtonBindings();
      else alternateButtonBindings();
      smartDashboardButtons();   
  }

  private void smartDashboardButtons() {
    // SmartDashboard Buttons
    SmartDashboard.putData("TankDrive", new TankDrive(drivetrain));
    SmartDashboard.putData("Autonomous Command", new AutonomousCommand());
    SmartDashboard.putData("ShiftHigh", new ShiftHigh(shifters));
    SmartDashboard.putData("ShiftLow", new ShiftLow(shifters));
    SmartDashboard.putData("Shoot: default", new Shoot(0.4, shooter, ballSensor));
    //SmartDashboard.putData("TurretCommand", new TurretCommand());
    SmartDashboard.putData("RunIntake: default", new RunIntake(intake, 0.4));
    SmartDashboard.putData("DriveXFeetMM: default", new DriveXFeetMM(0, 0, 30, drivetrain));
    //SmartDashboard.putData("TurnNDegreesAbsolute: default", new TurnNDegreesAbsolute(180));
    SmartDashboard.putData("InvertDrive", new InvertDrive(drivetrain));
    SmartDashboard.putData("QuarterSpeed", new QuarterSpeed(drivetrain));
    SmartDashboard.putData("RotationControl", new RotationControl(controlPanel, colorSensor));
    SmartDashboard.putData("PositionControl", new PositionsControl(controlPanel, colorSensor));
    SmartDashboard.putData("Index: default", new Index(indexer, 0.4));
    SmartDashboard.putData("HalfSpeed", new HalfSpeed(drivetrain));
    SmartDashboard.putData("ToggleLockStraight", new ToggleLockStraight(drivetrain));
    //SmartDashboard.putData("ManualTurretElevation: default", new ManualTurretElevation(0));
    //SmartDashboard.putData("ManualTurretElevationDegrees: default", new ManualTurretElevationDegrees(0));
    //SmartDashboard.putData("RunTurretVision", new RunTurretVision());
    //SmartDashboard.putData("RunTurretManual", new RunTurretManual());
    SmartDashboard.putData("ClimberSpeedMode: up", new ClimberSpeedMode(climber, 0.5));
    SmartDashboard.putData("ClimberSpeedMode: down", new ClimberSpeedMode(climber, -0.5));
    SmartDashboard.putData("RotationControl", new RotationControl(controlPanel, colorSensor));
    SmartDashboard.putData("PositionsControl", new PositionsControl(controlPanel, colorSensor));
    SmartDashboard.putNumber("Degrees:", 0);
    SmartDashboard.putNumber("TurnPID P:", 0.05);
    SmartDashboard.putNumber("TurnPID I:", 0.00004);
    SmartDashboard.putNumber("TurnPID D:", 0.0025);

    //SmartDashboard.putData("Reinitialize PIDController:", new ReinitializePIDController());
  }
private void configureButtonBindings() {
    rightJoystick = new Joystick(0);
    leftJoystick = new Joystick(1);
    buttonBox = new Joystick(2);

    //reverse intake
    runIntakeInverseBtn = new JoystickButton(buttonBox, 1);
    runIntakeInverseBtn.whileHeld(new RunIntake(intake, -1));
    runIntakeInverseBtn.whileHeld(new Index(indexer, -1));
      
    //climb up/climb down
    climbDown = new JoystickButton(buttonBox, 6);
    climbDown.whileHeld(new ClimberSpeedMode(climber, -0.5));
    climbDown.whenReleased(new SequentialCommandGroup(new WaitCommand(1), new ClimberBrake(climber), new WaitCommand(1)));
    climbUp = new JoystickButton(buttonBox, 5);
    climbUp.whileHeld(new ClimberSpeedMode(climber, 0.5));
      
    //manualElevation = new JoystickButton(buttonBox, 2);
    //manualElevation.whileHeld(new ManualTurretElevation(0));

    //runIntake
    runIntakeBtn = new JoystickButton(buttonBox, 4);
    runIntakeBtn.whileHeld(new RunIntake(intake, 1));

    //Intake and Indexer
    runIntakeIndexerBtn = new JoystickButton(buttonBox, 7);
    runIntakeIndexerBtn.whileHeld(new Index(indexer, 1));
    runIntakeIndexerBtn.whileHeld(new RunIntake(intake, 1));

    shootBtn = new JoystickButton(buttonBox, 9);
    shootBtn.whileHeld(new Shoot(0.5, shooter, ballSensor));
     
    positionControlBtn = new JoystickButton(buttonBox, 5);
    positionControlBtn.whileHeld(new PositionsControl(controlPanel, colorSensor));
    rotationControlBtn = new JoystickButton(buttonBox, 6);
    rotationControlBtn.whileHeld(new RotationControl(controlPanel, colorSensor));

    //senseColorBtn = new JoystickButton(buttonBox, 5);
    //senseColorBtn.whileHeld(new SenseColor(colorSensor));

    //Drive Train buttons
    
    //left joystick
    toggleLockStraightBtn = new JoystickButton(leftJoystick, 4 );
    toggleLockStraightBtn.whileHeld(new ToggleLockStraight(drivetrain));

    //right joystick
    invertDriveBtn = new JoystickButton(rightJoystick, 2);
    invertDriveBtn.whenPressed(new InvertDrive(drivetrain));
    shiftHighWPBtn = new JoystickButton(rightJoystick, 3);
    shiftHighWPBtn.whenPressed(new ShiftHigh(shifters));
    quarterSpeedBtn = new JoystickButton(rightJoystick, 4);
    quarterSpeedBtn.whenPressed(new QuarterSpeed(drivetrain));
    
    shiftLowBtn = new JoystickButton(rightJoystick, 5);
    shiftLowBtn.whenPressed(new ShiftLow(shifters));
    shiftHighWHBtn = new JoystickButton(rightJoystick, 1);
    shiftHighWHBtn.whileHeld(new ShiftHigh(shifters));

  }

  private void alternateButtonBindings() {
    rightJoystick = new Joystick(0);
    leftJoystick = new Joystick(1);
    buttonBox = new Joystick(2);

    //reverse intake
    runIntakeInverseBtn = new JoystickButton(buttonBox, 8);
    runIntakeInverseBtn.whileHeld(new RunIntake(intake, -1));
      
    //climb up/climb down
    climbDown = new JoystickButton(buttonBox, 6);
    climbDown.whileHeld(new ClimberSpeedMode(climber, -0.5));
    climbDown.whenReleased(new SequentialCommandGroup(new WaitCommand(1), new ClimberBrake(climber), new WaitCommand(1)));
    climbUp = new JoystickButton(buttonBox, 5);
    climbUp.whileHeld(new ClimberSpeedMode(climber, 0.5));

    manualElevation = new JoystickButton(buttonBox, 2);
    //manualElevation.whileHeld(new ManualTurretElevation(0));

    //runIntake
    runIntakeBtn = new JoystickButton(buttonBox, 4);
    runIntakeBtn.whenPressed(new RunIntake(intake, 1));

    //Intake and Indexer
    runIntakeIndexerBtn = new JoystickButton(buttonBox, 7);
    runIntakeIndexerBtn.whenPressed(new Index(indexer, 1));
    runIntakeIndexerBtn.whenPressed(new RunIntake(intake, 1));

    shootBtn = new JoystickButton(buttonBox, 1);
    shootBtn.whileHeld(new Shoot(0, shooter, ballSensor));
     
    positionControlBtn = new JoystickButton(buttonBox, 5);
    positionControlBtn.whileHeld(new PositionsControl(controlPanel, colorSensor));
    rotationControlBtn = new JoystickButton(buttonBox, 6);
    rotationControlBtn.whileHeld(new RotationControl(controlPanel, colorSensor));

    //Drive Train buttons
    
    //left joystick
    toggleLockStraightBtn = new JoystickButton(leftJoystick, 4 );
    toggleLockStraightBtn.whenPressed(new ToggleLockStraight(drivetrain));

    //right joystick
    invertDriveBtn = new JoystickButton(rightJoystick, 2);
    invertDriveBtn.whenPressed(new InvertDrive(drivetrain));
    shiftHighWPBtn = new JoystickButton(rightJoystick, 3);
    shiftHighWPBtn.whenPressed(new ShiftHigh(shifters));
    quarterSpeedBtn = new JoystickButton(rightJoystick, 4);
    quarterSpeedBtn.whenPressed(new QuarterSpeed(drivetrain));
    
    shiftLowBtn = new JoystickButton(rightJoystick, 2);
    shiftLowBtn.whenPressed(new ShiftLow(shifters));
    shiftHighWHBtn = new JoystickButton(rightJoystick, 1);
    shiftHighWHBtn.whileHeld(new ShiftHigh(shifters));

  }

  public Command getAutonomousCommand() {

    // Create a voltage constraint to ensure we don't accelerate too fast
    var autoVoltageConstraint =
    new DifferentialDriveVoltageConstraint(
            new SimpleMotorFeedforward(DriveConstants.ksVolts,
                                       DriveConstants.kvVoltSecondsPerMeter,
                                       DriveConstants.kaVoltSecondsSquaredPerMeter),
            DriveConstants.kDriveKinematics,
            10);

    // Create config for trajectory
    TrajectoryConfig config =
        new TrajectoryConfig(AutoConstants.kMaxSpeedMetersPerSecond, AutoConstants.kMaxAccelerationMetersPerSecondSquared)
            // Add kinematics to ensure max speed is actually obeyed
            .setKinematics(DriveConstants.kDriveKinematics)
            // Apply the voltage constraint
            .addConstraint(autoVoltageConstraint);

    //  config.setReversed(true);
  
    try{
      
      String trajectoryJSON = "paths/Backwards.wpilib.json";
    Path trajectoryPath = Filesystem.getDeployDirectory().toPath().resolve(trajectoryJSON);
    Trajectory exampleTrajectory = TrajectoryUtil.fromPathweaverJson(trajectoryPath);
    System.out.println(exampleTrajectory);
    exampleTrajectory = exampleTrajectory.transformBy(new Transform2d(new Pose2d(0, 0, new Rotation2d(0)), new Pose2d(-3, 5, new Rotation2d(0))));
    System.out.println(exampleTrajectory);
    RamseteCommand ramseteCommand = new RamseteCommand(
        exampleTrajectory,
        drivetrain::getPose,
        new RamseteController(AutoConstants.kRamseteB, AutoConstants.kRamseteZeta),
        new SimpleMotorFeedforward(DriveConstants.ksVolts,
                                   DriveConstants.kvVoltSecondsPerMeter,
                                   DriveConstants.kaVoltSecondsSquaredPerMeter),
        DriveConstants.kDriveKinematics,
        drivetrain::getWheelSpeeds,
        new PIDController(DriveConstants.kPDriveVel, 0, 0),
        new PIDController(DriveConstants.kPDriveVel, 0, 0),
        // RamseteCommand passes volts to the callback
        drivetrain::tankDriveVolts,
        drivetrain
    );

    // Run path following command, then stop at the end.
    return ramseteCommand.andThen(() -> drivetrain.tankDriveVolts(0, 0));
    }
    catch (IOException ex)
    {
      DriverStation.reportError("Unable to open trajectory", ex.getStackTrace());
      System.out.println("Inside Catch");
    }
    



    return null;
  }

  public Command getAutonomousCommand2() {

    // Create a voltage constraint to ensure we don't accelerate too fast
    var autoVoltageConstraint =
        new DifferentialDriveVoltageConstraint(
            new SimpleMotorFeedforward(DriveConstants.ksVolts,
                                       DriveConstants.kvVoltSecondsPerMeter,
                                       DriveConstants.kaVoltSecondsSquaredPerMeter),
            DriveConstants.kDriveKinematics,
            10);

    // Create config for trajectory
    TrajectoryConfig config =
        new TrajectoryConfig(AutoConstants.kMaxSpeedMetersPerSecond,
                             AutoConstants.kMaxAccelerationMetersPerSecondSquared)
            // Add kinematics to ensure max speed is actually obeyed
            .setKinematics(DriveConstants.kDriveKinematics)
            // Apply the voltage constraint
            .addConstraint(autoVoltageConstraint);

    //config.setReversed(true);

    // An example trajectory to follow.  All units in meters.

    //config.setReversed(true);
    
    
    Trajectory exampleTrajectory = TrajectoryGenerator.generateTrajectory(
        // Start at the origin facing the +X direction
        new Pose2d(1.4, 2, new Rotation2d(Math.PI/3.25)),
        // Pass through these two interior waypoints, making an 's' curve path
        List.of(
          //new Translation2d(3, 0)
          //new Translation2d(5, -1),
          //new Translation2d(5, -1)
        ),
        // End 3 meters straight ahead of where we started, facing forward
        new Pose2d(3, 0, new Rotation2d(0)),
        // Pass config
        config
    );

    RamseteCommand ramseteCommand = new RamseteCommand(
        exampleTrajectory,
        drivetrain::getPose,
        new RamseteController(AutoConstants.kRamseteB, AutoConstants.kRamseteZeta),
        new SimpleMotorFeedforward(DriveConstants.ksVolts,
                                   DriveConstants.kvVoltSecondsPerMeter,
                                   DriveConstants.kaVoltSecondsSquaredPerMeter),
        DriveConstants.kDriveKinematics,
        drivetrain::getWheelSpeeds,
        new PIDController(DriveConstants.kPDriveVel, 0, 0),
        new PIDController(DriveConstants.kPDriveVel, 0, 0),
        // RamseteCommand passes volts to the callback
        drivetrain::tankDriveVolts,
        drivetrain
    );

    // Run path following command, then stop at the end.
    return ramseteCommand.andThen(() -> drivetrain.tankDriveVolts(0, 0));
  }

  public Joystick getRightJoystick() {
    return rightJoystick;
  }

  public Joystick getLeftJoystick() {
    return leftJoystick;
  }

  public Joystick getButtonBox() {
    return buttonBox;
  }

}
