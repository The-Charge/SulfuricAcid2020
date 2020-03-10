/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;

/**
 * Add your docs here.
 */
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

  public boolean realButtonBox = true;

  public OI() 
  {
      SmartDashboard.putData("TurnOffLights", new TurnOffLights(lights));
      if (realButtonBox) configureButtonBindings();
      smartDashboardButtons();  
  }
}
