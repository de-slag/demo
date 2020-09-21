package de.slag.demo.fileoperations;

import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class FileoperationsIntegrationTest {

	public static void main(String[] args) {
		if(args.length != 2) {
			throw new RuntimeException("use 2 parameters: pathName, copyToDirName");
		}
		String pathName = args[0];
		String copyToDirName = args[1];
		
		File file = new File(pathName);
		File[] listFiles = file.listFiles();
		List<File> asList = Arrays.asList(listFiles);
		System.out.println("all files: " + asList.size());

		List<File> jpegs = asList.parallelStream()
				.filter(Filters.JPG).filter(Predicate.not(Filters.Images.SMALL))
				.collect(Collectors.toList());
		System.out.println("jpegs: " + jpegs.size());

		jpegs.forEach(f -> {
			Operations.COPY.accept(f, copyToDirName + f.getName());
		});

		File fileAfterAll = new File(copyToDirName);
		File[] listFilesAfterAll = fileAfterAll.listFiles();
		List<File> asList2 = Arrays.asList(listFilesAfterAll);
		System.out.println("copied files: " + asList2.size());
	}

}
