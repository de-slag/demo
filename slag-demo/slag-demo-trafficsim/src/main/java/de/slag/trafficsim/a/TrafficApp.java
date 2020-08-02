package de.slag.trafficsim.a;

import java.util.List;

public class TrafficApp {

	public static void main(String[] args) {
		out("start");
		TrafficRunner trafficRunner = new TrafficRunner();
		List<Auto> autos = trafficRunner.autos;
		out("anzahl autos: " + autos.size());
		trafficRunner.run();
		out("fertig");
	}

	private static void out(Object y) {
		System.out.println(y);
	}
}
