package org.usfirst.frc.team1389.robot;

import com.team1389.hardware.registry.port_types.CAN;
import com.team1389.hardware.registry.port_types.DIO;
import com.team1389.hardware.registry.port_types.PWM;
import com.team1389.hardware.registry.port_types.SPIPort;

import edu.wpi.first.wpilibj.SPI;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into to a variable name.
 * This provides flexibility changing wiring, makes checking the wiring easier and significantly
 * reduces the number of magic numbers floating around.
 * <p>
 * <b>Conventions</b>: <br>
 * For I/O ports, the naming convention is <em>type_ALL_CAPS_IDENTIFIER</em>. for example, a talon
 * port might be named can_LEFT_MOTOR_A. Possible port types and identifiers are CAN (can), Analog
 * (anlg), PWM (pwm), USB (usb), PCM (pcm), DIO (dio), etc
 * <p>
 * Inputs and Outputs may be inverted. The inversions in this map should only relate to the physical
 * configuration of the robot. A positive value should cause the output to move in the most logical
 * direction (I.e, the drive motors should move forward with positive voltage values) <br>
 * the convention for inversion constants is <em>inv_ASSOCIATED_IO_IDENTIFIER</em> for outputs and
 * <em>sinv_ASSOCIATED_IO_IDENTIFIER</em> for inputs.
 */
public class RobotMap {
	//Drivetrain
	protected static final CAN can_LEFT_MOTOR_A = new CAN(9);
	protected static final boolean inv_LEFT_MOTOR_A = true;
	protected static final CAN can_LEFT_MOTOR_B = new CAN(5);
	protected static final boolean inv_LEFT_MOTOR_B = false;
	protected static final CAN can_RIGHT_MOTOR_A = new CAN(1);
	protected static final boolean inv_RIGHT_MOTOR_A = true;
	protected static final CAN can_RIGHT_MOTOR_B = new CAN(7);
	protected static final boolean inv_RIGHT_MOTOR_B = true;
	
	//Elevator
	protected static final PWM pwm_ELEVATOR_LEFT = new PWM(8);
	protected static final PWM pwm_ELEVATOR_RIGHT = new PWM(9);
	protected static final boolean inv_ELEVATOR_LEFT = false;
	protected static final boolean inv_ELEVATOR_RIGHT = false;
	
	//Sensors
	protected static final DIO dio_TOP_LIMIT = new DIO(0);
	protected static final DIO dio_BOTTOM_LIMIT = new DIO(1);
	protected static final boolean inv_TOP_LIMIT = false;
	protected static final boolean inv_BOTTOM_LIMIT = true;

	protected static final boolean sinv_LEFT_ENCODER = true;
	protected static final boolean sinv_RIGHT_ENCODER = false;
	protected static final boolean sinv_ELEVATOR_ENCODER = true;
	protected static final SPIPort spi_GYRO = new SPIPort(SPI.Port.kOnboardCS0);

}
