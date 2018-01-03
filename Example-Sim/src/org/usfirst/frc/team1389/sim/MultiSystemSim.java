package org.usfirst.frc.team1389.sim;

import java.util.HashMap;
import java.util.Map;

import com.team1389.util.Loopable;

import simulation.Simulator;
import simulation.test.Testable;

public class MultiSystemSim {
	ElevatorSim elevator;

	public MultiSystemSim() {
		elevator = new ElevatorSim();
	}

	public static void main(String[] args) {
		MultiSystemSim sim = new MultiSystemSim();
		new Thread(sim::runSim).start();
		sim.runTests();
	}

	public void runSim() {
		try {
			Simulator.simulate(Loopable.combine(elevator));
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void runTests() {
		Map<String, Testable> tests = new HashMap<>();
		Map<String, String> testsPassed = new HashMap<>();
		tests.put("Elevator", elevator);
		tests.forEach((k, v) -> testsPassed.put(k, v.testPassed() ? "PASSED" : "FAILED"));
		testsPassed.entrySet().forEach(System.out::println);
	}
}
