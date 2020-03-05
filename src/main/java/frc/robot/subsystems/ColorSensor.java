// RobotBuilder Version: 2.0
//
// This file was generated by RobotBuilder. It contains sections of
// code that are automatically generated and assigned by robotbuilder.
// These sections will be updated in the future when you export to
// Java from RobotBuilder. Do not put any code or make any change in
// the blocks indicating autogenerated code or it will be lost on an
// update. Deleting the comments indicating the section will prevent
// it from being updated in the future.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.commands.*;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.util.Color;
import edu.wpi.first.wpilibj2.command.Subsystem;
import edu.wpi.first.wpilibj.I2C;
import com.revrobotics.ColorSensorV3;
import com.revrobotics.ColorMatchResult;

import java.util.concurrent.TimeoutException;

import com.revrobotics.ColorMatch;

// BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=IMPORTS

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=IMPORTS


/**
 *
 */
public class ColorSensor implements Subsystem {

    private final I2C.Port i2cPort = I2C.Port.kOnboard;
    private final ColorSensorV3 m_colorSensor = new ColorSensorV3(i2cPort);
    private final ColorMatch m_colorMatcher = new ColorMatch();
    private String colorString = "Hello";

    private final Color kBlueTarget = ColorMatch.makeColor(0.143, 0.427, 0.429);
    private final Color kGreenTarget = ColorMatch.makeColor(0.197, 0.561, 0.240);
    private final Color kRedTarget = ColorMatch.makeColor(0.561, 0.232, 0.114);
    private final Color kYellowTarget = ColorMatch.makeColor(0.361, 0.524, 0.113);
    
    public ColorSensor() {
      setDefaultCommand(new SenseColor(this));
      m_colorMatcher.addColorMatch(kBlueTarget);
      m_colorMatcher.addColorMatch(kGreenTarget);
      m_colorMatcher.addColorMatch(kRedTarget);
      m_colorMatcher.addColorMatch(kYellowTarget); 
      
      
    }

   

   

    @Override
    public void periodic() {
               // Put code here to be run every loop
         /**
     * The method GetColor() returns a normalized color value from the sensor and can be
     * useful if outputting the color to an RGB LED or similar. To
     * read the raw color, use GetRawColor().
     * 
     * The color sensor works best when within a few inches from an object in
     * well lit conditions (the built in LED is a big help here!). The farther
     * an object is the more light from the surroundings will bleed into the 
     * measurements and make it difficult to accurately determine its color.
     */
   
    /**
     * Open Smart Dashboard or Shuffleboard to see the color detected by the 
     * sensor.
     */
    //FIXME: The above comment sounds awesome, but I'm not seeing the code to do it
    // Throw the getColorString() on the smart dashboard
  

    }
    public void ColorSensed(){
      Color detectedColor = m_colorSensor.getColor();

      /**
       * Run the color match algorithm on our detected color
       */
      
      ColorMatchResult match = m_colorMatcher.matchClosestColor(detectedColor);
      //FIXME: Normally I don't have a problem with leaving code in comments at the high school level _if they're helpful_
      // These aren't. Remove them.
       //final int ctr = 0;
       //System.out.println("AAAAAAAAAAAAAAAAAAAGGGGGGGGGGGGGGGGGGGGG");
      if (match.color == kBlueTarget) {
        colorString = "Blue";
        //System.out.print("Bluekjlkjdflkgj");
      } else if (match.color == kRedTarget) {
        colorString = "Red";
        //System.out.print("Redfdjkgldkjf");
      } else if (match.color == kGreenTarget) {
        colorString = "Green";
       // System.out.print("Greendfglkdfglk");
      } else if (match.color == kYellowTarget) {
        colorString = "Yellow";
      } else {
        colorString = "Change"; 
      }
      //FIXME: Get rid of System.out.println
      System.out.print(colorString);
    }

    public Color getColor(){ //parameter String colors

        //FIXME: Use better names for smartdashboard keys
        // Would strongly recommend starting with class name. IE: DriveTrain left speed set
        // Prevents key collision and helps speed up debugging
      //FIXME: Move smartdashboard values to periodic() where possible
      SmartDashboard.putString("Detected Color", colorString);

      return m_colorSensor.getColor();
    }
    public String getColorString(){
      return colorString;
    }
    public double getConfidence(){
    Color detectedColors = m_colorSensor.getColor();
    ColorMatchResult match = m_colorMatcher.matchClosestColor(detectedColors);
    return match.confidence;
    }


      

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CMDPIDGETTERS


    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CMDPIDGETTERS

    //FIXME: Remove excess comments
    // Put methods for controlling this subsystem
    // here. Call these from Commands.


}

