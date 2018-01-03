package org.usfirst.frc.team1389.systems;

import org.usfirst.frc.team1389.robot.RobotConstants;

import com.team1389.configuration.PIDConstants;
import com.team1389.control.SynchronousPIDController;
import com.team1389.hardware.inputs.software.DigitalIn;
import com.team1389.hardware.inputs.software.RangeIn;
import com.team1389.hardware.outputs.software.PercentOut;
import com.team1389.hardware.value_types.Percent;
import com.team1389.hardware.value_types.Position;
import com.team1389.system.TimedSubsystem;
import com.team1389.util.list.AddList;
import com.team1389.watch.Watchable;

public class Elevator extends TimedSubsystem {

	private PercentOut elevator;
	private RangeIn<Position> elevatorPos;
	private DigitalIn limitSwitch;

	public SynchronousPIDController<Percent, Position> elevatorController;

	private State state;

	public Elevator(PercentOut elevator, RangeIn<Position> elevatorPos, DigitalIn limitSwitch) {
		super();
		this.elevator = elevator;
		this.elevatorPos = elevatorPos.copy().mapToRange(0, 100);
		this.limitSwitch = limitSwitch;
		elevatorController = new SynchronousPIDController<>(new PIDConstants(40, 0, 60), this.elevatorPos,
				this.elevator);
		elevatorController.setInputRange(-100, 100);

	}

	@Override
	public AddList<Watchable> getSubWatchables(AddList<Watchable> stem) {
		return stem;
	}

	@Override
	public String getName() {
		return "Elevator";
	}

	@Override
	public void init() {
		state = State.ZEROING;
		elevatorController.setSetpoint(elevatorPos.get());
	}

	@Override
	public void update() {
		switch (state) {
		case RUNNING:
			break;
		case ZEROING:
			elevatorController
					.setSetpoint(elevatorController.getSetpoint() - RobotConstants.ElevatorZeroSpeed * getDelta());
			if (limitSwitch.get()) {
				System.out.println("elevator zeroed");
				elevatorController.getSource().adjustOffsetToMatch(0.0);
				elevatorPos = elevatorController.getSource();
				state = State.RUNNING;
				elevatorController.setSetpoint(50);
			}
			break;
		default:
			break;
		}
		elevatorController.update();
	}

	public State getState() {
		return state;
	}

	public enum State {
		ZEROING, RUNNING
	}
}
