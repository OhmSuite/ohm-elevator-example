package org.usfirst.frc.team1389.robot;

import com.team1389.hardware.inputs.hardware.GyroHardware;
import com.team1389.hardware.inputs.hardware.PDPHardware;
import com.team1389.hardware.inputs.hardware.SwitchHardware;
import com.team1389.hardware.outputs.hardware.CANTalonGroup;
import com.team1389.hardware.outputs.hardware.CANTalonHardware;
import com.team1389.hardware.outputs.hardware.VictorHardware;
import com.team1389.hardware.registry.Registry;
import com.team1389.hardware.registry.port_types.SPIPort;

/**
 * contains a list of declared hardware objects for this robot. Separated from {@link RobotHardware}
 * to make it easier to see what hardware is connected to the robot.
 * 
 * @author amind
 *
 */
public class RobotLayout extends RobotMap {
	public Registry registry;
	public PDPHardware pdp;
	public CANTalonHardware leftA;
	public CANTalonHardware rightA;
	public CANTalonHardware leftB;
	public CANTalonHardware rightB;
	public CANTalonGroup leftGroup;
	public CANTalonGroup rightGroup;
	public GyroHardware<SPIPort> gyro;

	public VictorHardware elevatorA;
	public VictorHardware elevatorB;

	public SwitchHardware topSwitch;
	public SwitchHardware bottomSwitch;

}
