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
//FIXME: Need type on declaration for generic (should be SendableChooser<Command>)
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
    m_robotContainer.stopper.closeStopper();
    m_robotContainer.drivetrain.resetOdometry(new Pose2d(0, 0, new Rotation2d(0)));
    ParallelCommandGroup m_parallel = new ParallelCommandGroup(m_robotContainer.rShoot(), m_robotContainer.rElevation());
    SequentialCommandGroup m_sequential = new SequentialCommandGroup(m_parallel, new WaitCommand(1),
                                            new ParallelCommandGroup(
                                              new ParallelCommandGroup(m_robotContainer.rOpen(), m_robotContainer.rIntake(), m_robotContainer.rIndex()),
                                                new SequentialCommandGroup(new WaitCommand(4), 
                                                  new ParallelCommandGroup(new SequentialCommandGroup(m_robotContainer.rResetTurret(),m_robotContainer.rZeroTurret()), 
                                                    new SequentialCommandGroup(m_robotContainer.getAutonomousBackwardS()))))
                                            );
                        //BADm_robotContainer.rResetTurret(),m_robotContainer.rZeroTurret() m_robotContainer.getAutonomousBackwardS2()
    
    //SequentialCommandGroup m_enginerds = new SequentialCommandGroup(new WaitCommand(3),
                                          //new ParallelCommandGroup(m_robotContainer.rIndexReverse(), m_robotContainer.rIntakeReverse(),
                                            //new SequentialCommandGroup(new WaitCommand(4), 
                                              //new SequentialCommandGroup(m_robotContainer.rResetTurret(),m_robotContainer.rZeroTurret()))));
    m_autonomousCommand = m_sequential;
    //m_autonomousCommand =  new SequentialCommandGroup(m_robotContainer.rShoot(), 
    //                        new SequentialCommandGroup( new WaitCommand(3),
    //                          new ParallelCommandGroup(m_robotContainer.rOpen(), m_robotContainer.rIntake(), m_robotContainer.rIndex(), 
    //                            new SequentialCommandGroup(new WaitCommand(4), m_robotContainer.rClose(), m_robotContainer.rIndex(), m_robotContainer.getAutonomousNew()))));//(Command) chooser.getSelected();

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
    //FIXME: Move these to the subsystem periodic()
    SmartDashboard.putNumber("Left Encoder", m_robotContainer.drivetrain.leftFrontMotor.getSelectedSensorPosition(0));
    SmartDashboard.putNumber("Right Encoder", m_robotContainer.drivetrain.rightFrontMotor.getSelectedSensorPosition(0));
    SmartDashboard.putNumber("Gyro Reading", m_robotContainer.drivetrain.getHeading());
    SmartDashboard.putString("Pose", m_robotContainer.drivetrain.m_odometry.getPoseMeters().getTranslation().toString());
    SmartDashboard.putNumber("Shooter", m_robotContainer.shooter.motorShooter.getSelectedSensorVelocity()/20000.0);
    SmartDashboard.putNumber("Turrett", m_robotContainer.turret.getCurrentHorizontalAngle());
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
    //FIXME: Should refactor this to be commands rather than in periodic. 
    if (RobotContainer.buttonBox.getRawButtonPressed(8)) visionOverride = !visionOverride;
    
    if (!visionOverride){
      if (RobotContainer.Xbox.getRawButtonPressed(9)){
        // FIXME: This doesn't do anything. Makes an object, but it's never used
        // FIXME: Do not make new objects in a periodic method! (floods garbage collector)
        new RunTurretVision(m_robotContainer.turret, 0.8);
      }
    } else {
      // FIXME: This doesn't do anything. Makes an object, but it's never used
      // FIXME: Do not make new objects in a periodic method! (floods garbage collector)
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
