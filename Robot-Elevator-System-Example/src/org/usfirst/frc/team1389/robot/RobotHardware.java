package org.usfirst.frc.team1389.robot;

import com.team1389.hardware.inputs.hardware.GyroHardware;
import com.team1389.hardware.inputs.hardware.PDPHardware;
import com.team1389.hardware.inputs.hardware.SwitchHardware;
import com.team1389.hardware.outputs.hardware.CANTalonGroup;
import com.team1389.hardware.outputs.hardware.CANTalonHardware;
import com.team1389.hardware.outputs.hardware.VictorHardware;
import com.team1389.hardware.registry.Registry;

/**
 * responsible for initializing and storing hardware objects defined in {@link RobotLayout}
 * 
 * @author amind
 * @see RobotLayout
 * @see RobotMap
 */
public class RobotHardware extends RobotLayout {

	/**
	 * Initializes robot hardware by subsystem. <br>
	 * note: use this method as an index to show hardware initializations that occur, and to find
	 * the init code for a particular system's hardware
	 */
	protected RobotHardware() {
		registry = new Registry();
		pdp = new PDPHardware(registry);
		System.out.println("initializing hardware");
		gyro = new GyroHardware<>(GyroHardware.ADXRS_450, spi_GYRO, registry);
		initDriveTrain();
		initElevator();
	}

	private void initElevator() {
		elevatorA = new VictorHardware(inv_ELEVATOR_LEFT, pwm_ELEVATOR_LEFT, registry);
		elevatorB = new VictorHardware(inv_ELEVATOR_RIGHT, pwm_ELEVATOR_RIGHT, registry);
		topSwitch = new SwitchHardware(inv_TOP_LIMIT, dio_TOP_LIMIT, registry);
		bottomSwitch = new SwitchHardware(inv_BOTTOM_LIMIT, dio_BOTTOM_LIMIT, registry);
		topSwitch.setName("topSwitch");
		bottomSwitch.setName("bottomSwitch");

	}

	private void initDriveTrain() {
		leftA = new CANTalonHardware(inv_LEFT_MOTOR_A, sinv_LEFT_ENCODER, can_LEFT_MOTOR_A, registry);
		rightA = new CANTalonHardware(inv_RIGHT_MOTOR_A, sinv_RIGHT_ENCODER, can_RIGHT_MOTOR_A, registry);
		leftB = new CANTalonHardware(inv_LEFT_MOTOR_B, sinv_ELEVATOR_ENCODER, can_LEFT_MOTOR_B, registry);
		rightB = new CANTalonHardware(inv_RIGHT_MOTOR_B, can_RIGHT_MOTOR_B, registry);
		leftGroup = new CANTalonGroup(leftA, leftB);
		rightGroup = new CANTalonGroup(rightA, rightB);
	}

	public Registry getRegistry() {
		return registry;
	}

}