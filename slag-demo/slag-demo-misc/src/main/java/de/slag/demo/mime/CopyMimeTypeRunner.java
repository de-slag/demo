package de.slag.demo.mime;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class CopyMimeTypeRunner implements Runnable {

	private String directory;

	public CopyMimeTypeRunner(String directory) {
		super();
		this.directory = directory;
	}

	@Override
	public void run() {
		Path path = Paths.get(directory);
		File[] listFiles = path.toFile().listFiles();
		List<File> asList = Arrays.asList(listFiles);
		final FileTypePredicate fileTypePredicate = FileTypePredicate.JPG;
		List<File> jpgs = asList.stream().filter(fileTypePredicate).collect(Collectors.toList());

		System.out.println(jpgs);
		jpgs.forEach(file -> {
			Path originalPath = file.toPath();
			String name = file.getName();
			Path copied = Paths.get("/media/test/data/test-images/" + name);

			try {
				Files.copy(originalPath, copied, StandardCopyOption.REPLACE_EXISTING,
						StandardCopyOption.COPY_ATTRIBUTES, StandardCopyOption.ATOMIC_MOVE);
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		});
		System.out.println("all done");

	}

}
