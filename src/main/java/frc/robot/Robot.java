/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import frc.robot.subsystems.*;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.geometry.Pose2d;
import edu.wpi.first.wpilibj.geometry.Rotation2d;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.Constants.DriveConstants;
import frc.robot.commands.*;
/**
 * The VM is configured to automatically run this class, and to call the functions corresponding to
 * each mode, as described in the TimedRobot documentation. If you change the name of this class or
 * the package after creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {
 


private Command m_autonomousCommand;
public RobotContainer m_robotContainer;
private SendableChooser chooser;
boolean visionOverride = false;



  /**
   * This function is run when the robot is first started up and should be used for any
   * initialization code.
   */
  @Override
  public void robotInit() {

    // Instantiate our RobotContainer.  This will perform all our button bindings, and put our
    // autonomous chooser on the dashboard.
    
    m_robotContainer = new RobotContainer(); 
    chooser = new SendableChooser<Command>();
    //m_robotContainer.drivetrain.invertMotors();
    chooser.setDefaultOption("Drive Forward", new SequentialCommandGroup(m_robotContainer.getAutonomousForward()));
    //chooser.addOption("Shoot Drive Forward", new SequentialCommandGroup(m_robotContainer.shoot(), m_robotContainer.getAutonomousForward()));
    chooser.addOption("Drive Backward", new SequentialCommandGroup(m_robotContainer.getAutonomousBackward()));
    chooser.addOption("Corner", new SequentialCommandGroup(m_robotContainer.getAutonomousCorner(), m_robotContainer.getAutonomousCorner2()));
    chooser.addOption("PortTR", new SequentialCommandGroup(m_robotContainer.getAutonomousPortTR(), m_robotContainer.getAutonomousPortTR2()));
    SmartDashboard.putData("AutoSelect", chooser);
  }

  /**
   * This function is called every robot packet, no matter the mode. Use this for items like
   * diagnostics that you want ran during disabled, autonomous, teleoperated and test.
   *
   * <p>This runs after the mode specific periodic functions, but before
   * LiveWindow and SmartDashboard integrated updating.
   */
  @Override
  public void robotPeriodic() {
    // Runs the Scheduler.  This is responsible for polling buttons, adding newly-scheduled
    // commands, running already-scheduled commands, removing finished or interrupted commands,
    // and running subsystem periodic() methods.  This must be called from the robot's periodic
    // block in order for anything in the Command-based framework to work.
    CommandScheduler.getInstance().run();
    
    

  }

  /**
   * This function is called once each time the robot enters Disabled mode.
   */
  @Override
  public void disabledInit() {
  }

  @Override
  public void disabledPeriodic() {
  }

  /**
   * This autonomous runs the autonomous command selected by your {@link RobotContainer} class.
   */
  @Override
  public void autonomousInit() {
    m_robotContainer.drivetrain.resetEncoders();
    m_robotContainer.drivetrain.resetOdometry(new Pose2d(0, 0, new Rotation2d(0)));
    m_autonomousCommand =  new SequentialCommandGroup(m_robotContainer.rShoot(), 
                            new SequentialCommandGroup( 
                              new ParallelCommandGroup(m_robotContainer.rOpen(), m_robotContainer.rIntake(), m_robotContainer.rIndex(), 
                                new SequentialCommandGroup(new WaitCommand(4), m_robotContainer.rClose(), m_robotContainer.rIndex(), m_robotContainer.getAutonomousNew()))));//(Command) chooser.getSelected();

    // schedule the autonomous command (example)
    if (m_autonomousCommand != null) {
      m_autonomousCommand.schedule();
    }
  }

  /**
   * This function is called periodically during autonomous.
   */
  @Override
  public void autonomousPeriodic() {
    SmartDashboard.putNumber("Left Encoder", m_robotContainer.drivetrain.leftFrontMotor.getSelectedSensorPosition(0));
    SmartDashboard.putNumber("Right Encoder", m_robotContainer.drivetrain.rightFrontMotor.getSelectedSensorPosition(0));
    SmartDashboard.putNumber("Gyro Reading", m_robotContainer.drivetrain.getHeading());
    SmartDashboard.putString("Pose", m_robotContainer.drivetrain.m_odometry.getPoseMeters().getTranslation().toString());
    //SmartDashboard.putData("", m_robotContainer.m_robotDrive.getPose());
    //SmartDashboard.putNumber("Left Encoder", System.currentTimeMillis());
  }

  @Override
  public void teleopInit() {
    // This makes sure that the autonomous stops running when
    // teleop starts running. If you want the autonomous to
    // continue until interrupted by another command, remove
    // this line or comment it out.
    if (m_autonomousCommand != null) {
      m_autonomousCommand.cancel();
    }
    m_robotContainer.stopper.closeStopper();
  }

  /**
   * This function is called periodically during operator control.
   */
  @Override
  public void teleopPeriodic() {

    SmartDashboard.putNumber("TriggerLeft",RobotContainer.Xbox.getRawAxis(2));
    SmartDashboard.putNumber("TriggerRight",RobotContainer.Xbox.getRawAxis(3));


    if (RobotContainer.buttonBox.getRawButton(8)) visionOverride = !visionOverride;
    
    if (!visionOverride){
      if (RobotContainer.Xbox.getTriggerPressed()){
        new RunTurretVision(m_robotContainer.turret);
      }
      if (RobotContainer.Xbox.getX(Hand.kLeft) > 0.5){
        new RunIntake(m_robotContainer.intake, 0.4);
        new Index(m_robotContainer.indexer, 1);
      }
      if (RobotContainer.Xbox.getY(Hand.kRight) > 0.5){
        new ParallelCommandGroup(new OpenStopper(m_robotContainer.stopper), new Index(m_robotContainer.indexer, 0.5, true)) ;
      } else if (RobotContainer.Xbox.getY(Hand.kRight) <= 0.1){
        new ParallelCommandGroup ((new CloseStopper(m_robotContainer.stopper, m_robotContainer.indexer)));
      }
    
    }else {
        new RunTurretManual(m_robotContainer.turret);
      }
    
    }

  

  @Override
  public void testInit() {
    // Cancels all running commands at the start of test mode.
    CommandScheduler.getInstance().cancelAll();
  }

  /**
   * This function is called periodically during test mode.
   */
  @Override
  public void testPeriodic() {
  }
}
