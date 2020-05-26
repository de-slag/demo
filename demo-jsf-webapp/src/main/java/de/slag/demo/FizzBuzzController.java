package de.slag.demo;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import de.slag.demo.fizzbuzz.FizzBuzzSimpleRunner;

@ManagedBean
@SessionScoped
public class FizzBuzzController {

	public String getOutput() {
		List<String> entries = new ArrayList<>();
		FizzBuzzSimpleRunner fizzBuzzSimpleRunner = new FizzBuzzSimpleRunner(s -> entries.add(s));
		fizzBuzzSimpleRunner.run();
		return String.join("<br/>", entries);
	}

}
