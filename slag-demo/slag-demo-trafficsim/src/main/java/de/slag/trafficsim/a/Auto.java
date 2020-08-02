package de.slag.trafficsim.a;

public class Auto {

	public Auto(int geschwindigkeit, int fahrspur, int position, int beschleunigung) {
		super();
		this.geschwindigkeit = geschwindigkeit;
		this.fahrspur = fahrspur;
		this.position = position;
		this.beschleunigung = beschleunigung;
	}

	// in m pro Sekunde
	int geschwindigkeit;

	int fahrspur;

	int position;

	// in m pro Sekunde quadrat
	int beschleunigung;

	public void drive() {
		int neueGeschwindigkeit = geschwindigkeit + beschleunigung;
		int neuePosition = position + neueGeschwindigkeit / 2;
	}

}
