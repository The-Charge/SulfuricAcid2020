/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import frc.robot.subsystems.ColorSensor;
import edu.wpi.first.wpilibj2.command.CommandBase;

public class SenseColor extends CommandBase {
 
  private final ColorSensor m_colorSensor;


  public SenseColor(ColorSensor colorSensor) {
    m_colorSensor = colorSensor;
    addRequirements(m_colorSensor);
  }

    //FIXME: If it's an empty method, remove it.
  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
      //call the method and remove periodic
      m_colorSensor.ColorSensed();
  }

    //FIXME: If it's an empty method, remove it.
  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
