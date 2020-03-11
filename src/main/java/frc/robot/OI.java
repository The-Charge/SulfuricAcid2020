package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.XboxController;
import frc.robot.commands.*;
import frc.robot.subsystems.*;

public class OI {

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
    public JoystickButton openStopperBtn;

    public ControlPanel controlPanel = RobotContainer.controlPanel;
    public ColorSensor colorSensor = RobotContainer.colorSensor;
    public Drivetrain drivetrain = RobotContainer.drivetrain;
    public Turret turret = RobotContainer.turret;
    public Lights lights = RobotContainer.lights;
    public Shifters shifters = RobotContainer.shifters;
    public Climber climber = RobotContainer.climber;
    public Intake intake = RobotContainer.intake;
    public Stopper stopper = RobotContainer.stopper;
    public Indexer indexer = RobotContainer.indexer;
    public Shooter shooter = RobotContainer.shooter;
    public BallSensor ballSensor = RobotContainer.ballSensor;
    public Cooling cooling = RobotContainer.cooling;

    public void configureButtonBindings() {
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
        climbUp.whileHeld((new SequentialCommandGroup(new ClimberUnBrake(climber), new WaitCommand(1), new ClimberRun(climber, 0.7))));
        climbUp.whenReleased(new ClimberBrake(climber));
          
        //manualElevation = new JoystickButton(buttonBox, 2);
        //manualElevation.whileHeld(new ManualTurretElevation(0));
    
        //runIntake
        runIntakeBtn = new JoystickButton(buttonBox, 4);
        runIntakeBtn.whileHeld(new RunIntake(intake, 1));
    
        //Intake and Indexer
        runIntakeIndexerBtn = new JoystickButton(Xbox, 5);
        runIntakeIndexerBtn.whileHeld(new RunIntake(intake, 0.6));
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
          aButton.whenPressed(new Shoot(shooter, 1)); 
          aButton.whenPressed(new RunTurretVision(turret, 0.8)); 
          bButton = new JoystickButton(Xbox, 2);
          bButton.whenPressed(new Shoot(shooter, .8));
          bButton.whenPressed(new RunTurretVision(turret, 0.8));
          xButton = new JoystickButton(Xbox, 3);
          xButton.whenPressed(new Shoot(shooter, .7));
          xButton.whenPressed(new RunTurretVision(turret, 0.8));
          yButton = new JoystickButton(Xbox, 4);
          yButton.whenPressed(new Shoot(shooter, .48));
          yButton.whenPressed(new RunTurretVision(turret, 0.4));
          openStopperBtn = new JoystickButton(Xbox, 10);
          openStopperBtn.whenPressed(new OpenStopper(stopper));
    
    
    
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