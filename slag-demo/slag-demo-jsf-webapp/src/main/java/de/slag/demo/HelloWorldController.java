package de.slag.demo;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import de.slag.demo.helloworld.HelloWorld;


@ManagedBean
@SessionScoped
public class HelloWorldController {

	public String getHello() {
		final ByteArrayOutputStream baos = new ByteArrayOutputStream();
		final PrintStream ps = new PrintStream(baos);
		try {
			return getHello0(baos, ps);
		} catch (Throwable t) {
			System.setOut(System.out);
			throw t;
		}
	}

	private String getHello0(ByteArrayOutputStream baos, PrintStream ps) {
		System.setOut(ps);
		HelloWorld.main(new String[0]);
		return baos.toString();
	}

}
