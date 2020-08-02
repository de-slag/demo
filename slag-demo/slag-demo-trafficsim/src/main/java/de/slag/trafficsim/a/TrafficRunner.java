package de.slag.trafficsim.a;

import java.util.ArrayList;
import java.util.List;

public class TrafficRunner implements Runnable {

	List<Auto> autos = new ArrayList<>();

	public TrafficRunner() {
		for (int i = 0; i < 1; i++) {
			autos.add(createAuto());

		}

	}

	private Auto createAuto() {
		int inMeternPerSecond = TrafficUtils.inMeternPerSecond(30);
		int beschleunigung = 4;
		int position = 0;
		int fahrspur = 0;
		return new Auto(inMeternPerSecond, fahrspur, position, beschleunigung);
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub

	}

}
