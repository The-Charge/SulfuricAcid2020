/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.XboxController;
import frc.robot.subsystems.*;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.commands.*;
import frc.robot.subsystems.Drivetrain;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * This class is where the bulk of the robot should be declared. Since
 * Command-based is a "declarative" paradigm, very little robot logic should
 * actually be handled in the {@link Robot} periodic methods (other than the
 * scheduler calls). Instead, the structure of the robot (including subsystems,
 * commands, and button mappings) should be declared here.
 */

public class RobotContainer {

  // The robot's subsystems and commands are defined here...
  private final ExampleSubsystem m_exampleSubsystem = new ExampleSubsystem();
  private final ExampleCommand m_autoCommand = new ExampleCommand(m_exampleSubsystem);

  public Drivetrain drivetrain = new Drivetrain();
  public Shifters shifters = new Shifters();
  public Climber climber = new Climber();

  public static Joystick leftJoystick;
  public static Joystick rightJoystick;
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
  public Joystick buttonBox;

  /**
   * The container for the robot. Contains subsystems, OI devices, and commands.
   */
  public RobotContainer() {
    // Configure the button bindings
    configureButtonBindings();

    buttonBox = new Joystick(2);
    driveXFeetBtn = new JoystickButton(buttonBox, 2);
    driveXFeetBtn.whenPressed(new DriveXFeetMM(0, 0, 10, drivetrain));

    manualElevation = new JoystickButton(buttonBox, 6);
    // manualElevation.whileHeld(new ManualTurretElevation(0));
    shootBtn = new JoystickButton(buttonBox, 1);
    // shootBtn.whileHeld(new Shoot(0));
    leftJoystick = new Joystick(1);

    toggleLockStraightBtn = new JoystickButton(leftJoystick, 1);
    toggleLockStraightBtn.whileHeld(new ToggleLockStraight(drivetrain));
    rightJoystick = new Joystick(0);

    halfSpeedBtn = new JoystickButton(rightJoystick, 5);
    halfSpeedBtn.whileHeld(new HalfSpeed(drivetrain));
    quarterSpeedBtn = new JoystickButton(rightJoystick, 4);
    quarterSpeedBtn.whileHeld(new QuarterSpeed(drivetrain));
    shiftLowBtn = new JoystickButton(rightJoystick, 2);
    shiftLowBtn.whenPressed(new ShiftLow(shifters));
    shiftHighBtn = new JoystickButton(rightJoystick, 1);
    shiftHighBtn.whenPressed(new ShiftHigh(shifters));

    climbDown = new JoystickButton(buttonBox, 6);
    // climbDown.whileHeld(new ClimberSpeedMode(climber, -0.5));
    climbDown.whileHeld(new ClimberSpeedMode(climber, -0.5));
    climbDown.whenReleased(new SequentialCommandGroup(new WaitCommand(1), new ClimberBrake(climber), new WaitCommand(1)));
    climbUp = new JoystickButton(buttonBox, 5);
    //climbUp.whileHeld(new ClimberSpeedMode(climber, 0.5));
    climbUp.whileHeld(new ClimberSpeedMode(climber, 0.5));


    // SmartDashboard Buttons
    SmartDashboard.putData("Autonomous Command", new AutonomousCommand());
    SmartDashboard.putData("ShiftHigh", new ShiftHigh(shifters));
    SmartDashboard.putData("ShiftLow", new ShiftLow(shifters));
    //SmartDashboard.putData("Shoot: default", new Shoot(0.4));
    //SmartDashboard.putData("TurretCommand", new TurretCommand());
    //SmartDashboard.putData("RunIntake: default", new RunIntake(0.4));
    SmartDashboard.putData("DriveXFeetMM: default", new DriveXFeetMM(0, 0, 30, drivetrain));
    //SmartDashboard.putData("TurnNDegreesAbsolute: default", new TurnNDegreesAbsolute(180));
    SmartDashboard.putData("InvertDrive", new InvertDrive(drivetrain));
    SmartDashboard.putData("QuarterSpeed", new QuarterSpeed(drivetrain));
    //SmartDashboard.putData("RotationControl", new RotationControl());
    //SmartDashboard.putData("PositionControl", new PositionControl());
    //SmartDashboard.putData("Index: default", new Index(0.4));
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
  public Command getAutonomousCommand() {
    // An ExampleCommand will run in autonomous
    return m_autoCommand;
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
