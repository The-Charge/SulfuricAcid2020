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
  private final ExampleSubsystem m_exampleSubsystem = new ExampleSubsystem();
  private final ExampleCommand m_autoCommand = new ExampleCommand(m_exampleSubsystem);

	public ControlPanel controlPanel = new ControlPanel();

  public Drivetrain drivetrain = new Drivetrain();
  public Turret turret = new Turret();
  //public Shifters shifters = new Shifters();
  public Climber climber = new Climber();
  public Indexer indexer = new Indexer();
  public static Joystick leftJoystick;
  public static Joystick rightJoystick;
  public static Joystick buttonBox;
  public JoystickButton shiftHighBtn;
  public JoystickButton shiftLowBtn;
  public JoystickButton quarterSpeedBtn;
  public JoystickButton halfSpeedBtn;
  public JoystickButton toggleLockStraightBtn;
  public JoystickButton shootBtn;
  public JoystickButton manualElevation;
  public JoystickButton driveXFeetBtn;
  public JoystickButton climbUp;
  public JoystickButton climbDown;
  public Intake m_Intake = new Intake();
  public ColorSensor m_colorSensor = new ColorSensor();
  private final RotationControl m_rotationControl = new RotationControl(controlPanel, m_colorSensor);
  private final PositionsControl m_positionsControl = new PositionsControl(controlPanel, m_colorSensor);
    // Button button = new JoystickButton(stick, buttonNumber);

    // There are a few additional built in buttons you can use. Additionally,
    // by subclassing Button you can create custom triggers and bind those to
    // commands the same as any other Button.
    //// TRIGGERING COMMANDS WITH BUTTONS
    // Once you have a button, it's trivial to bind it to a button in one of
    // three ways:
    // Start the command when the button is pressed and let it run the command
    // until it is finished as determined by it's isFinished method.
    // button.whenPressed(new ExampleCommand());
    // Run the command while the button is being held down and interrupt it once
    // the button is released.
    // button.whileHeld(new ExampleCommand());

   // The container for the robot.  Contains subsystems, OI devices, and commands.
    // until it is finished as determined by it's isFinished method.
    // button.whenReleased(new ExampleCommand());
    public RobotContainer() {
    SmartDashboard.putData("RotationControl", new RotationControl(controlPanel, m_colorSensor));
    SmartDashboard.putData("PositionsControl", new PositionsControl(controlPanel, m_colorSensor));
    
    //SmartDashboard.putNumber("Red", detectedColor.red);
    //SmartDashboard.putNumber("Green", detectedColor.green);
    //SmartDashboard.putNumber("Blue", detectedColor.blue);
    //SmartDashboard.putNumber("Confidence", match.confidence);
    //SmartDashboard.putString("Detected Color", colorString);
	configureButtonBindings();

    buttonBox = new Joystick(2);
    driveXFeetBtn = new JoystickButton(buttonBox, 2);
    driveXFeetBtn.whenPressed(new DriveXFeetMM(0, 0, 10, drivetrain));

    manualElevation = new JoystickButton(buttonBox, 6);
    // manualElevation.whileHeld(new ManualTurretElevation(0));
    shootBtn = new JoystickButton(buttonBox, 1);
    // shootBtn.whileHeld(new Shoot(0));
    leftJoystick = new Joystick(0);

    toggleLockStraightBtn = new JoystickButton(leftJoystick, 1);
    toggleLockStraightBtn.whileHeld(new ToggleLockStraight(drivetrain));
    rightJoystick = new Joystick(1);

    halfSpeedBtn = new JoystickButton(rightJoystick, 5);
    halfSpeedBtn.whileHeld(new HalfSpeed(drivetrain));
    quarterSpeedBtn = new JoystickButton(rightJoystick, 4);
    quarterSpeedBtn.whileHeld(new QuarterSpeed(drivetrain));
    /*
    shiftLowBtn = new JoystickButton(rightJoystick, 2);
    shiftLowBtn.whenPressed(new ShiftLow(shifters));
    shiftHighBtn = new JoystickButton(rightJoystick, 1);
    shiftHighBtn.whenPressed(new ShiftHigh(shifters));
*/
    climbDown = new JoystickButton(buttonBox, 6);
    climbDown.whileHeld(new ClimberSpeedMode(climber, -0.5));
    climbDown.whenReleased(new SequentialCommandGroup(new WaitCommand(1), new ClimberBrake(climber), new WaitCommand(1)));
    climbUp = new JoystickButton(buttonBox, 5);
    climbUp.whileHeld(new ClimberSpeedMode(climber, 0.5));


    // SmartDashboard Buttons
    SmartDashboard.putData("TankDrive", new TankDrive(drivetrain));
    SmartDashboard.putData("Autonomous Command", new AutonomousCommand());
    //SmartDashboard.putData("ShiftHigh", new ShiftHigh(shifters));
    //SmartDashboard.putData("ShiftLow", new ShiftLow(shifters));
    //SmartDashboard.putData("Shoot: default", new Shoot(0.4));
    //SmartDashboard.putData("TurretCommand", new TurretCommand());
    SmartDashboard.putData("RunIntake: default", new RunIntake(m_Intake, 0.4));
    SmartDashboard.putData("DriveXFeetMM: default", new DriveXFeetMM(0, 0, 30, drivetrain));
    //SmartDashboard.putData("TurnNDegreesAbsolute: default", new TurnNDegreesAbsolute(180));
    SmartDashboard.putData("InvertDrive", new InvertDrive(drivetrain));
    SmartDashboard.putData("QuarterSpeed", new QuarterSpeed(drivetrain));
    //SmartDashboard.putData("RotationControl", new RotationControl());
    //SmartDashboard.putData("PositionControl", new PositionControl());
    SmartDashboard.putData("Index: default", new Index(indexer, 0.4));
    SmartDashboard.putData("HalfSpeed", new HalfSpeed(drivetrain));
    SmartDashboard.putData("ToggleLockStraight", new ToggleLockStraight(drivetrain));
    //SmartDashboard.putData("ManualTurretElevation: default", new ManualTurretElevation(0));
    //SmartDashboard.putData("ManualTurretElevationDegrees: default", new ManualTurretElevationDegrees(0));
    //SmartDashboard.putData("RunTurretVision", new RunTurretVision());
    //SmartDashboard.putData("RunTurretManual", new RunTurretManual());
    SmartDashboard.putData("ClimberSpeedMode: up", new ClimberSpeedMode(climber, 0.5));
    SmartDashboard.putData("ClimberSpeedMode: down", new ClimberSpeedMode(climber, -0.5));

    SmartDashboard.putNumber("Degrees:", 0);
    SmartDashboard.putNumber("TurnPID P:", 0.05);
    SmartDashboard.putNumber("TurnPID I:", 0.00004);
    SmartDashboard.putNumber("TurnPID D:", 0.0025);

    //SmartDashboard.putData("Reinitialize PIDController:", new ReinitializePIDController());
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
        new TrajectoryConfig(AutoConstants.kMaxSpeedMetersPerSecond,
                             AutoConstants.kMaxAccelerationMetersPerSecondSquared)
            // Add kinematics to ensure max speed is actually obeyed
            .setKinematics(DriveConstants.kDriveKinematics)
            // Apply the voltage constraint
            .addConstraint(autoVoltageConstraint);

    //  config.setReversed(true);

    // An example trajectory to follow.  All units in meters.


    

    /*
    
    Trajectory exampleTrajectory = TrajectoryGenerator.generateTrajectory(
        // Start at the origin facing the +X direction
        new Pose2d(0, 0, new Rotation2d(0)),
        // Pass through these two interior waypoints, making an 's' curve path
        List.of(
          new Translation2d(1, 0)
          //new Translation2d(2, 0)
          //new Translation2d(5, -1)
        ),
        // End 3 meters straight ahead of where we started, facing forward
        new Pose2d(1.4, 2, new Rotation2d(Math.PI/3.25)),
        // Pass config
        config
    );

    */

    
    /*
    
    
    String trajectoryJSON = "paths/Example.wpilib.json";
    Trajectory exampleTrajectory = TrajectoryGenerator.generateTrajectory(
      // Start at the origin facing the +X direction
      new Pose2d(0, 0, new Rotation2d(0)),
      // Pass through these two interior waypoints, making an 's' curve path
      List.of(
        new Translation2d(1, 1)
      ),
      // End 3 meters straight ahead of where we started, facing forward
      new Pose2d(2, 0, new Rotation2d(0)),
      // Pass config
      config
  );

  */
  
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

    /*


    
    String trajectoryJSON = "paths/Example.wpilib.json";
    Trajectory exampleTrajectory = TrajectoryGenerator.generateTrajectory(
      // Start at the origin facing the +X direction
      new Pose2d(0, 0, new Rotation2d(0)),
      // Pass through these two interior waypoints, making an 's' curve path
      List.of(
        new Translation2d(1, 1)
      ),
      // End 3 meters straight ahead of where we started, facing forward
      new Pose2d(2, 0, new Rotation2d(0)),
      // Pass config
      config
  );
  
    try{

    Path trajectoryPath = Filesystem.getDeployDirectory().toPath().resolve(trajectoryJSON);
    exampleTrajectory = TrajectoryUtil.fromPathweaverJson(trajectoryPath);
    System.out.println("INside try");
    }
    catch (IOException ex)
    {
      DriverStation.reportError("Unable to open trajectory", ex.getStackTrace());
      System.out.println("Inside Catch");
    }
    
*/


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
  /**
   * Use this method to define your button->command mappings.  Buttons can be created by
   * instantiating a {@link GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a
   * {@link edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {
  }


  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  

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
