package de.slag.trafficsim.a;

public class TrafficUtils {

	public static int inKmh(int mPerSecond) {
		return (int) (mPerSecond * 3.6);
	}
	
	public static int inMeternPerSecond(int kmh) {
		return (int) (kmh / 3.6);
	}
	
	
	
}
