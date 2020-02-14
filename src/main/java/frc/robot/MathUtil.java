package frc.robot;

//import org.usfirst.frc2619.Plybot2020.subsystems.Drivetrain;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class MathUtil {

	public static double calcDirection(double current, double desired) {
		current = Math.toRadians(current);
		desired = Math.toRadians(desired);
		double current_x = Math.cos(current);
		double current_y = Math.sin(current);
		double desired_x = Math.cos(desired);
		double desired_y = Math.sin(desired);
		double direction = Math.signum(current_x * desired_y - desired_x * current_y);
		return direction;
	}	//return +1 if direction is CW, -1 if CCW - copied from SteamBot

	//limits a value to a max and min
	public static double clamp(double input, double min, double max){
		input = input < min ? min : input;
		input = input > max ? max : input;
		return input;
	}

	//calls deadband and delin
    public static double adjSpeed(double speed) {
    	double dB = 0.1;
    	double root = 1;
    	double power = 3;
    	speed = MathUtil.deadband(speed, dB);
    	speed = MathUtil.delin(speed, dB , root, power);
    	return speed;
    }
	
	//Prevents the robot from moving while the joysticks are close to the center
	public static double deadband(double speed, double dead){
		if (-dead < speed && speed < dead) 
			return 0;
		else
			return speed;
	}//checks if speed is inbetween -dB and +dB then it should be set to zero
	
	//Applies delinerization
	public static double delin(double speed, double dead, double root, double pwr){
		double evn = (pwr/root) % 2; // 1
		double invdB = Math.pow(1 - dead,-1); // 1/0.9
		double cons = pwr/root; // 3
		if (speed != 0) { //Makes sure deadband doesn't bypass the calculations
			if (speed > 0) //Speed is greater than zero and so there are no exceptions
				return Math.pow(invdB * (speed - dead), cons);
			else if (evn != 0) //Less than zero, checks for even power
				return Math.pow(invdB * (speed + dead), cons);
			else //To stay negative, a "-" must be put at the beginning to maintain negativity of speed
				return -Math.pow(invdB * (speed + dead), cons);
		}
		else return 0;
	}//

	//Maps a ranged value between 0 and 1
	public static double lerp(double input, double max, double min){
		return (input-min)/(max-min);
	}
}