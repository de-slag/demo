package de.slag.demo.fizzbuzz;

import java.util.function.Consumer;

public class FizzBuzzSimpleRunner implements Runnable {

	private Consumer<String> out;

	public FizzBuzzSimpleRunner() {
		this(System.out::println);
	}
	
	public FizzBuzzSimpleRunner(Consumer<String> out) {
		this.out = out;
	}

	@Override
	public void run() {
		int i = 1;
		while (i <= 100) {
			if (i % 3 == 0 && i % 5 == 0) {
				out.accept("fizzbuzz");
			} else if (i % 5 == 0) {
				out.accept(("buzz"));
			} else if (i % 3 == 0) {
				out.accept(("fizz"));
			} else {
				out.accept((String.valueOf(i)));
			}
			i++;
		}
	}
}
