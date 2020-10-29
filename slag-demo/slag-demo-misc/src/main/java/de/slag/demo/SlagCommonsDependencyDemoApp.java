package de.slag.demo;

import java.time.LocalDate;

import de.slag.common.util.DateUtils;

public class SlagCommonsDependencyDemoApp {

	public static void main(String[] args) {
		System.out.println(DateUtils.firstDayOfQuater(LocalDate.now()));
	}

}
