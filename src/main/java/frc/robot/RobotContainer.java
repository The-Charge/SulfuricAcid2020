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
import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.commands.*;
import frc.robot.subsystems.*;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
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
  public Lights lights = new Lights();
  public Drivetrain drivetrain = new Drivetrain();
  public Turret turret = new Turret();
  public Shifters shifters = new Shifters();
  public Climber climber = new Climber();
  public Intake intake = new Intake();
  public Stopper stopper = new Stopper();
  public Indexer indexer = new Indexer(stopper);
  public Shooter shooter = new Shooter();
  public BallSensor ballSensor = new BallSensor(indexer, stopper);
  public TurretElevation turretElevation = new TurretElevation();

  //JOYSTICKS
  public static Joystick leftJoystick;
  public static Joystick rightJoystick;
  public static Joystick buttonBox;
  public static XboxController Xbox;

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
  public JoystickButton sensorColorBtn;
  public JoystickButton zeroBalls;
  public JoystickButton climbDown;
  public JoystickButton visionOverrideBtn;
  public JoystickButton runIntakeIndexerBtn;
  public JoystickButton aButton;
  public JoystickButton bButton;
  public JoystickButton xButton;
  public JoystickButton yButton;

  public boolean realButtonBox = true;

   
    public RobotContainer() {
    SmartDashboard.putData("TurnOffLights", new TurnOffLights(lights));
      if (realButtonBox) configureButtonBindings();
      smartDashboardButtons();   
  }

  private void smartDashboardButtons() {
 
    // SmartDashboard Buttons
    SmartDashboard.putData("Run Vision", new RunTurretVision(turret));
    SmartDashboard.putData("Reset Encoder", new ResetTurretEncoder(turret));
    SmartDashboard.putData("Zero Turret", new TurretToZero(turret));
    SmartDashboard.putData("TankDrive", new TankDrive(drivetrain));
    SmartDashboard.putData("Autonomous Command", new AutonomousCommand());
    SmartDashboard.putData("ShiftHigh", new ShiftHigh(shifters));
    SmartDashboard.putData("ShiftLow", new ShiftLow(shifters));
    SmartDashboard.putData("Shoot: default", new Shoot(shooter,0.4));
    //SmartDashboard.putData("TurretCommand", new TurretCommand());
    SmartDashboard.putData("RunIntake: default", new RunIntake(intake, 0.4));
    SmartDashboard.putData("DriveXFeetMM: default", new DriveXFeetMM(0, 0, 30, drivetrain));
    //SmartDashboard.putData("TurnNDegreesAbsolute: default", new TurnNDegreesAbsolute(180));
    SmartDashboard.putData("InvertDrive", new InvertDrive(drivetrain));
    SmartDashboard.putData("QuarterSpeed", new QuarterSpeed(drivetrain));
    SmartDashboard.putData("RotationControl", new RotationControl(controlPanel, colorSensor));
    SmartDashboard.putData("PositionControl", new PositionsControl(controlPanel, colorSensor));
    SmartDashboard.putData("Index: default", new Index(indexer, 0.1));
    SmartDashboard.putData("HalfSpeed", new HalfSpeed(drivetrain));
    SmartDashboard.putData("ToggleLockStraight", new ToggleLockStraight(drivetrain));
    //SmartDashboard.putData("ManualTurretElevation: default", new ManualTurretElevation(0));
    //SmartDashboard.putData("ManualTurretElevationDegrees: default", new ManualTurretElevationDegrees(0));
    //SmartDashboard.putData("RunTurretVision", new RunTurretVision());
    //SmartDashboard.putData("RunTurretManual", new RunTurretManual());
    SmartDashboard.putData("ClimberRun: up", new ClimberRun(climber, 0.5));
    SmartDashboard.putData("ClimberRun: down", new ClimberRun(climber, -0.5));
    SmartDashboard.putData("RotationControl", new RotationControl(controlPanel, colorSensor));
    SmartDashboard.putData("PositionsControl", new PositionsControl(controlPanel, colorSensor));
    SmartDashboard.putNumber("Degrees:", 0);
    SmartDashboard.putNumber("TurnPID P:", 0.05);
    SmartDashboard.putNumber("TurnPID I:", 0.00004);
    SmartDashboard.putNumber("TurnPID D:", 0.0025);
    SmartDashboard.putData("Set Balls 0", new BallCounterReset(ballSensor));

    //SmartDashboard.putData("Reinitialize PIDController:", new ReinitializePIDController());
  }
private void configureButtonBindings() {
    leftJoystick = new Joystick(0);
    rightJoystick = new Joystick(1);
    buttonBox = new Joystick(2);
    Xbox = new XboxController(3);

    //reverse intake
    runIntakeInverseBtn = new JoystickButton(buttonBox, 1);
    runIntakeInverseBtn.whileHeld(new RunIntake(intake, -0.2));
    runIntakeInverseBtn.whileHeld(new Index(indexer, -0.7));
      
    //climb up/climb down
    climbDown = new JoystickButton(buttonBox, 3);
    climbDown.whileHeld(new ClimberRun(climber, -0.6));
    climbUp = new JoystickButton(buttonBox, 2);
    climbUp.whileHeld((new SequentialCommandGroup(new ClimberUnBrake(climber), new WaitCommand(1), new ClimberRun(climber, 0.6))));
    climbUp.whenReleased(new ClimberBrake(climber));
      
    //manualElevation = new JoystickButton(buttonBox, 2);
    //manualElevation.whileHeld(new ManualTurretElevation(0));

    //runIntake
    runIntakeBtn = new JoystickButton(buttonBox, 4);
    runIntakeBtn.whileHeld(new RunIntake(intake, 0.3));

    //Intake and Indexer
    runIntakeIndexerBtn = new JoystickButton(Xbox, 5);
    runIntakeIndexerBtn.whileHeld(new RunIntake(intake, 0.4));
    runIntakeIndexerBtn.whileHeld(new Index(indexer, 1));
    

    shootBtn = new JoystickButton(Xbox, 6);
    //shootBtn.whileHeld(new ParallelCommandGroup(new OpenStopper(stopper))); indexer, slow speed
    shootBtn.whileHeld(new ParallelCommandGroup(new OpenStopper(stopper), new Index(indexer, 0.5, true)));
    shootBtn.whenReleased(new ParallelCommandGroup (new CloseStopper(stopper, indexer), new Shoot(shooter, 0)));

    visionOverrideBtn = new JoystickButton(buttonBox, 8);
    visionOverrideBtn.whenPressed(new RunTurretManual(turret));
     
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
    shiftHighWHBtn.whenPressed(new ShiftHigh(shifters));
    shiftHighWHBtn.whenReleased(new ShiftLow(shifters));

      aButton = new JoystickButton(Xbox, 1);
      aButton.whenPressed(new Shoot(shooter, .9)); 
      aButton.whenPressed(new ChangeElevation(turretElevation, 0.8)); 
      bButton = new JoystickButton(Xbox, 2);
      bButton.whenPressed(new Shoot(shooter, .72));
      aButton.whenPressed(new ChangeElevation(turretElevation, 0.8));
      xButton = new JoystickButton(Xbox, 3);
      xButton.whenPressed(new Shoot(shooter, .62));
      aButton.whenPressed(new ChangeElevation(turretElevation, 0.8));
      yButton = new JoystickButton(Xbox, 4);
      yButton.whenPressed(new Shoot(shooter, .48));
      aButton.whenPressed(new ChangeElevation(turretElevation, 0.4));

  }

  //FIXME: Would recommend moving the Auton Command generating methods to a separate AutonomousCommandContainer
  // Could also argue having each one be in it's own class to make it easier to find one when it breaks.
  public Command getAutonomousCorner() {

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

    //  config.setReversed(true);

    // An example trajectory to follow.  All units in meters.

  
    try{
      
      String trajectoryJSON = "paths/Corner.wpilib.json";
    Path trajectoryPath = Filesystem.getDeployDirectory().toPath().resolve(trajectoryJSON);
    Trajectory exampleTrajectory = TrajectoryUtil.fromPathweaverJson(trajectoryPath);
    exampleTrajectory = exampleTrajectory.transformBy(new Transform2d(new Pose2d(0, 0, new Rotation2d(0)), new Pose2d(-4, 6, new Rotation2d(0))));
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

  public Command getAutonomousCorner2() {

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

    config.setReversed(true);
    
    
    Trajectory exampleTrajectory = TrajectoryGenerator.generateTrajectory(
        // Start at the origin facing the +X direction
        new Pose2d(2.723867910106207, -2.124089374707513+6, new Rotation2d(.9)),
        // Pass through these two interior waypoints, making an 's' curve path
        List.of(
          //new Translation2d(0.414797278616654,-5.3165544523351125 + 6),
          //new Translation2d(0.965825497494843,-4.555610721503329+6),
          //new Translation2d(1.560586114696696,-3.7072021940242132 + 6),
          //new Translation2d(2.225318569216417,-2.788821829227232 + 6)
          //new Translation2d(5, -1)
        ),
        // End 3 meters straight ahead of where we started, facing forward
        new Pose2d(1.561, -3.707+6, new Rotation2d(.9)),
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

  public Command getAutonomousPortTR() {

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

    //  config.setReversed(true);

    // An example trajectory to follow.  All units in meters.
    
  
    try{
      
      String trajectoryJSON = "paths/PortTR.wpilib.json";
    Path trajectoryPath = Filesystem.getDeployDirectory().toPath().resolve(trajectoryJSON);
    Trajectory exampleTrajectory = TrajectoryUtil.fromPathweaverJson(trajectoryPath);
    System.out.println(exampleTrajectory);
    exampleTrajectory = exampleTrajectory.transformBy(new Transform2d(new Pose2d(0, 0, new Rotation2d(0)), new Pose2d(-4, 6, new Rotation2d(0))));
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

  public Command getAutonomousPortTR2() {

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

    config.setReversed(true);
    
    
    Trajectory exampleTrajectory = TrajectoryGenerator.generateTrajectory(
        // Start at the origin facing the +X direction
        new Pose2d(2.482-4, -.515+6, new Rotation2d(Math.PI/2)),
        // Pass through these two interior waypoints, making an 's' curve path
        List.of(
          //new Translation2d(0.414797278616654,-5.3165544523351125 + 6),
          //new Translation2d(0.965825497494843,-4.555610721503329+6),
          //new Translation2d(1.560586114696696,-3.7072021940242132 + 6),
          //new Translation2d(2.225318569216417,-2.788821829227232 + 6)
          //new Translation2d(5, -1)
        ),
        // End 3 meters straight ahead of where we started, facing forward
        new Pose2d(2.501-4, -2.336+6, new Rotation2d(Math.PI/2)),
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
  public Command getAutonomousForward() {

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
        new Pose2d(0 , 0, new Rotation2d(0)),
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

  public Command getAutonomousBackward() {

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

    config.setReversed(true);
    
    
    Trajectory exampleTrajectory = TrajectoryGenerator.generateTrajectory(
        // Start at the origin facing the +X direction
        new Pose2d(0 , 0, new Rotation2d(0)),
        // Pass through these two interior waypoints, making an 's' curve path
        List.of(
          //new Translation2d(3, 0)
          //new Translation2d(5, -1),
          //new Translation2d(5, -1)
        ),
        // End 3 meters straight ahead of where we started, facing forward
        new Pose2d(-1, 0, new Rotation2d(0)),
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

  public Command getAutonomousNew() {

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

    config.setReversed(true);
    
    
    Trajectory exampleTrajectory = TrajectoryGenerator.generateTrajectory(
        // Start at the origin facing the +X direction
        new Pose2d(0 , 0, new Rotation2d(0)),
        // Pass through these two interior waypoints, making an 's' curve path
        List.of(
          new Translation2d(.5, 0),
          new Translation2d(1, -1),
          new Translation2d(1, -3)
        ),
        // End 3 meters straight ahead of where we started, facing forward
        new Pose2d(1, -5, new Rotation2d(0)),
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

  public Command rShoot() {
    return new Shoot(shooter,AutoConstants.initShootSpeed);
  }

  public Command rIndex() {
    return new Index(indexer,0.4);
  }

  public Command rIntake() {
    return new RunIntake(intake,0.4);
  }

  public Command rClose() {
    return new CloseStopper(stopper, indexer);
  }

  public Command rOpen()
  {
    return new OpenStopper(stopper);
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
