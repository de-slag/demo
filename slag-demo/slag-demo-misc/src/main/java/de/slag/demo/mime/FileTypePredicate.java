package de.slag.demo.mime;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLConnection;
import java.util.function.Predicate;

public class FileTypePredicate implements Predicate<File> {
	
	public static final FileTypePredicate JPG = new FileTypePredicate(Type.JPG); 
	
	private final Type type;	

	private FileTypePredicate(Type type) {
		super();
		this.type = type;
	}

	@Override
	public boolean test(File file) {

		final InputStream is;
		try {
			is = new BufferedInputStream(new FileInputStream(file));
		} catch (FileNotFoundException e) {
			throw new RuntimeException(e);
		}
		String probeContentType;
		try {
			probeContentType = URLConnection.guessContentTypeFromStream(is);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}

		return Type.mimeOf(type).equals(probeContentType);
	}

	public enum Type {
		JPG;

		public static String mimeOf(Type type) {
			switch (type) {
			case JPG:
				return "image/png";
			default:
				throw new RuntimeException("not supported: " + type);
			}
		}
	}

}
