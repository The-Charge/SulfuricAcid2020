/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.subsystems.Indexer;
import frc.robot.subsystems.Shooter;
import frc.robot.subsystems.SimpleIndexer;
import frc.robot.subsystems.SimpleIntake;
import frc.robot.subsystems.SimpleShooter;
import frc.robot.subsystems.Stopper;

public class AutoShoot extends CommandBase {
  /**
   * Creates a new AutoShoot.
   */

  private final SimpleIndexer m_Indexer;
  private final SimpleShooter m_Shooter;
  private final SimpleIntake m_Intake;
  private final Stopper m_Stopper;
  private int previousState; // 0 = Paused, 1 = Shooting
  private int counter;
  private int numBallsIndexer;

  public AutoShoot(SimpleIndexer indexer, SimpleShooter shooter, SimpleIntake intake, Stopper stopper, int balls) {
    // Use addRequirements() here to declare subsystem dependencies.
    m_Indexer = indexer;
    m_Shooter = shooter;
    m_Intake = intake;
    m_Stopper = stopper;
    addRequirements(m_Indexer);
    addRequirements(m_Intake);
    addRequirements(m_Shooter);
    addRequirements(m_Stopper);
    previousState = 0;
    counter = 0;
    numBallsIndexer = balls;
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    m_Stopper.openStopper();
    m_Shooter.setFractionSpeedPID(Constants.AutoConstants.initShootSpeed);
  }
  private void shootState() {
    if (Math.abs(m_Intake.getSetSpeed()) > .01)  m_Intake.stop();
    if (Math.abs(Math.abs(m_Indexer.getSetSpeed()) - Constants.AutoConstants.indexFastSpeed) > .01)
      m_Indexer.setFractionSpeedPID(Constants.AutoConstants.indexFastSpeed);
    if (m_Stopper.isIn()) m_Stopper.openStopper();
    if (Math.abs(Math.abs(m_Shooter.getSetSpeed())) - Constants.AutoConstants.initShootSpeed > .02)
      m_Shooter.setFractionSpeedPID(Constants.AutoConstants.initShootSpeed);
      previousState = 1;
  }

  private void shootPauseState() {
    if (previousState == 1 && m_Shooter.isShooting()) counter++;
    if (Math.abs(m_Intake.getSetSpeed()) > .01) m_Intake.stop();
    if (Math.abs(m_Indexer.getSetSpeed()) > 0.01)
      m_Indexer.setFractionSpeedPID(0);
    if (m_Stopper.isIn()) m_Stopper.openStopper();
    if (Math.abs(Math.abs(m_Shooter.getSetSpeed()) - Constants.AutoConstants.initShootSpeed) > .02)
      m_Shooter.setFractionSpeedPID(Constants.AutoConstants.initShootSpeed);
    previousState = 0;
  }
  // Called every time the scheduler runs while the command is scheduled.
  @Override

  
  public void execute() {
    if ((m_Shooter.isAtSpeed())) shootState();
    else shootPauseState();
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    if (numBallsIndexer >= counter) return false;
    else return true;
  }
}
