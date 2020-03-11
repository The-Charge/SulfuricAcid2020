package frc.robot.commands;
import java.security.PublicKey;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Shifters;
import frc.robot.Robot;

public class ShiftLow extends CommandBase {

    private final Shifters m_subsystem;

    public ShiftLow(Shifters subsystem) {
        m_subsystem = subsystem;
        // Use addRequirements() here to declare subsystem dependencies.
        addRequirements(m_subsystem);
      }

    // Called just before this Command runs the first time
    @Override
    public void initialize() {
        m_subsystem.shiftLow();
    }

    // Make this return true when this Command no longer needs to run execute()
    @Override
    public boolean isFinished() {
        return false;
    }
}
