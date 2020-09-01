package de.slag.demo.factordivision;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class FactorDivisionRunner implements FactorDivisionRunnable {

	private final Integer divident;

	private final List<Integer> result = new ArrayList<Integer>();

	public FactorDivisionRunner(Integer divident) {
		super();
		this.divident = divident;
	}

	@Override
	public List<Integer> getResult() {
		return result;
	}

	@Override
	public void run() {
		Objects.requireNonNull(divident, "Divident is null");
		if (divident < 1) {
			throw new RuntimeException("Divident < 1");
		}
		run0();
	}

	private void run0() {
		// TODO: implement		
	}

}
