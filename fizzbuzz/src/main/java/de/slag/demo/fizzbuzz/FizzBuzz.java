package de.slag.demo.fizzbuzz;

public class FizzBuzz {

	public static void main(String[] args) {
		int i = 1;
		while (i <= 100) {
			if (i % 3 == 0 && i % 5 == 0) {
				out("fizzbuzz");
			} else if (i % 5 == 0) {
				out("buzz");
			} else if (i % 3 == 0) {
				out("fizz");
			} else {
				out(i);				
			}
			i++;
		}
	}

	private static void out(Object o) {
		System.out.println(o);
	}
}
