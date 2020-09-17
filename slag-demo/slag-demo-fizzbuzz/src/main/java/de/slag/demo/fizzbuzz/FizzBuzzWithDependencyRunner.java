package de.slag.demo.fizzbuzz;

import java.util.function.Consumer;

import de.slag.demo.base.DemoNumberUtils;

public class FizzBuzzWithDependencyRunner implements Runnable {

	private Consumer<String> out;

	public FizzBuzzWithDependencyRunner() {
		this(System.out::println);
	}

	public FizzBuzzWithDependencyRunner(Consumer<String> out) {
		this.out = out;
	}

	@Override
	public void run() {
		int i = 1;
		while (i <= 100) {
			if (DemoNumberUtils.isCompleteleyDivisibleBy(i, 5, 3)) {
				out.accept("fizzbuzz");
			} else if (DemoNumberUtils.isCompleteleyDivisibleBy(i, 5)) {
				out.accept(("buzz"));
			} else if (DemoNumberUtils.isCompleteleyDivisibleBy(i, 3)) {
				out.accept(("fizz"));
			} else {
				out.accept((String.valueOf(i)));
			}
			i++;
		}
	}
}
