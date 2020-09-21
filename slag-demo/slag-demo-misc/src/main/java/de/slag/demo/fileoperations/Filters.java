package de.slag.demo.fileoperations;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URLConnection;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.function.BiFunction;
import java.util.function.Predicate;

import javax.imageio.ImageIO;

public class Filters {

	private static final String MIME_JPG = "image/jpeg";

	public static final Predicate<File> JPG = f -> MIME_JPG.equals(getMimeType(f));

	private static String getMimeType(File f) {
		Path path = f.toPath();
		String mimeType;
		try {
			mimeType = Files.probeContentType(path);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		if (mimeType != null) {
			return mimeType;
		}

		URLConnection connection;
		try {
			connection = f.toURL().openConnection();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		mimeType = connection.getContentType();

		return mimeType;

	}

	public static class Images {
		public static final BiFunction<File, Integer, Boolean> SIZE_LOWER = (file, sizeLowerThan) -> {
			BufferedImage read;
			try {
				read = ImageIO.read(file);
			} catch (IOException e) {
				throw new RuntimeException(e);
			}

			int width = read.getWidth();
			int height = read.getHeight();
			int size = height * width;
			return size < sizeLowerThan;

		};

		public static final Predicate<File> SMALL = f -> SIZE_LOWER.apply(f, 162_000);
	}

}
