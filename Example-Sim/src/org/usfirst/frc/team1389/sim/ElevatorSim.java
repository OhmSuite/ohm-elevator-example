package org.usfirst.frc.team1389.sim;

import org.usfirst.frc.team1389.systems.Elevator;

import com.team1389.hardware.inputs.software.DigitalIn;
import com.team1389.hardware.inputs.software.RangeIn;
import com.team1389.hardware.value_types.Position;
import com.team1389.system.SystemManager;
import com.team1389.util.Loopable;
import com.team1389.watch.Watcher;

import simulation.Simulator;
import simulation.motor.LinearAttachment;
import simulation.motor.Motor;
import simulation.motor.Motor.MotorType;
import simulation.motor.MotorSystem;
import simulation.test.Testable;

public class ElevatorSim implements Loopable, Testable {
	Elevator elevator;
	SystemManager manager;
	MotorSystem system;
	boolean enabled;
	private RangeIn<Position> positionInput;

	@Override
	public void init() {
		system = new MotorSystem(new LinearAttachment(9), 40, 1, new Motor(MotorType.CIM), new Motor(MotorType.CIM));
		system.setRangeOfMotion(0, 72);
		system.setTheta(Math.random() * 72);
		positionInput = system.getPositionInput().mapToRange(0, 1.38).setRange(0, 100);
		DigitalIn limitSwitch = new DigitalIn(() -> positionInput.get() < .02);
		RangeIn<Position> encoderPos = positionInput.copy().offset(-positionInput.get());

		new Watcher(positionInput.getWatchable("ElevatorPos"), encoderPos.getWatchable("encoderPos"))
				.outputToDashboard();

		elevator = new Elevator(system.getVoltageOutput(), encoderPos, limitSwitch);

		manager = new SystemManager(elevator);
		manager.init();
	}

	@Override
	public void update() {
		if (enabled) {
			manager.update();
		}
		Watcher.update();
		system.update();
	}

	public static void main(String[] args) throws InterruptedException {
		ElevatorSim sim = new ElevatorSim();
		sim.enabled = false;
		new Thread(() -> {
			try {
				Simulator.simulate(sim, 30);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}).start();
		sim.test();
	}

	public void test() {
		try {
			enabled = true;
			Thread.sleep(5000);

			System.out.println(elevator.getState());

			// check that zeroing finished
			assert elevator.getState() == Elevator.State.RUNNING : "elevator not in correct state, "
					+ elevator.getState();
			double posInaccuracy = elevator.elevatorController.getSource().get() - positionInput.get();

			// test zeroing offset
			assert Math.abs(posInaccuracy) < .5 : "zeroing failed, elevator has wrong position value, " + posInaccuracy;

			// check elevator PID stability
			elevator.elevatorController.setSetpoint(90);
			Thread.sleep(3000);
			assert elevator.elevatorController.onTarget(1) : "elevator PID unstable";
			enabled = false;
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
