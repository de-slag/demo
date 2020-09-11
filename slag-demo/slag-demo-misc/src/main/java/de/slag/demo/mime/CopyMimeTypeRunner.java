package de.slag.demo.mime;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLConnection;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CopyMimeTypeRunner implements Runnable {

	private String directory;

	private String mimeType;

	public CopyMimeTypeRunner(String directory, String mimeType) {
		super();
		this.directory = directory;
		this.mimeType = mimeType;
	}

	@Override
	public void run() {
		Path path = Paths.get(directory);
		File[] listFiles = path.toFile().listFiles();
		List<File> asList = Arrays.asList(listFiles);
		Stream<File> jpgs = asList.stream().filter(file -> {
			Path path2 = file.toPath();
			String probeContentType;

			InputStream is;
			try {
				is = new BufferedInputStream(new FileInputStream(file));
			} catch (FileNotFoundException e) {
				throw new RuntimeException(e);
			}
			try {
				probeContentType = URLConnection.guessContentTypeFromStream(is);
			} catch (IOException e) {
				throw new RuntimeException(e);
			}

			return "image/png".equals(probeContentType);
		});
		List<File> collect = jpgs.collect(Collectors.toList());

		System.out.println(collect);
		collect.forEach(file -> {
			Path originalPath = file.toPath();
			String name = file.getName();
//			if (!name.endsWith(".jpg")) {
//				name += ".jpg";
//			}
			Path copied = Paths.get("/media/test/data/test-images/" + name);

			try {
				Files.copy(originalPath, copied, StandardCopyOption.REPLACE_EXISTING);
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		});
		System.out.println("all done");

	}

}
