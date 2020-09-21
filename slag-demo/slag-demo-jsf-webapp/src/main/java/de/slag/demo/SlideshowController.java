package de.slag.demo;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.net.URLConnection;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Properties;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

@ManagedBean
@SessionScoped
public class SlideshowController {

	private static final Log LOG = LogFactory.getLog(SlideshowController.class);

	private static final Predicate<Path> FILTER_JPG = f -> f.getFileName().toString().endsWith(".jpg");

	private static final String MIME_JPG = "image/jpeg";

	private static final Predicate<Path> FILTER_MIME_JPG = path -> {

		String mimeType;
		try {
			mimeType = Files.probeContentType(path);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		if (mimeType != null) {
			return MIME_JPG.equals(mimeType);
		}

		URLConnection connection;
		try {
			connection = path.toFile().toURL().openConnection();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		mimeType = connection.getContentType();

		return MIME_JPG.equals(mimeType);
	};

	private final List<String> images = new ArrayList<>();

	private int currentIndex = 0;

	private Integer speedInMs;

	private String cacheFolder;

	private String imageHight;

	private double pictureShare;

	@PostConstruct
	public void init() {
		final Properties properties = new ConfigPropertiesBuilder().build();

		if (properties.isEmpty()) {
			LOG.warn("no properties found, using default values");
		}

		speedInMs = Integer.valueOf(properties.getProperty("slideshow.speed", "7500"));
		cacheFolder = properties.getProperty("slideshow.cache", System.getProperty("java.io.tmpdir"));
		imageHight = properties.getProperty("slideshow.imageHeight", "350");
		pictureShare = Double.valueOf(properties.getProperty("slideshow.picture.share", "0.5"));

		LOG.info("slideshow.speed: " + speedInMs);
		LOG.info("slideshow.cache: " + cacheFolder);
		LOG.info("slideshow.imageHeight: " + imageHight);

		initImages();
	}

	private void initImages() {
		images.clear();
		final Path path = Paths.get(cacheFolder);
		List<Path> collect = new ArrayList<Path>(); 
			File[] listFiles = path.toFile().listFiles();
			collect.addAll(Arrays.asList(listFiles).stream().map(f -> f.toPath()).collect(Collectors.toList()));
			
		

		final List<Path> collect2 = collect.stream().filter(Files::isRegularFile).filter(FILTER_MIME_JPG)
				.collect(Collectors.toList());

		Collections.shuffle(collect2);

		int d = (int) (collect2.size() * pictureShare);

		for (int i = 0; i < d; i++) {
			images.add(collect2.get(i).toString());
		}
		LOG.info("images initialized: " + images);

	}

	public Object getImage() {
		try {
			return getImageFromFileSystem();
		} catch (IOException e) {
			throw new RuntimeException();
		}
	}

	public StreamedContent getImageFromFileSystem() throws IOException {

		if (images.size() > currentIndex + 1) {
			currentIndex = currentIndex + 1;
		} else {
			currentIndex = 0;
		}

		String file = images.get(currentIndex);

		return getImageFromFileSystem(file);
	}

	private StreamedContent getImageFromFileSystem(String file) {
		return DefaultStreamedContent.builder().contentType("image/jpg").stream(() -> {
			try {
				return new ByteArrayInputStream(Files.readAllBytes(new File(file).toPath()));
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		}).build();
	}

	public List<String> getImages() {
		return images;
	}

	public Integer getSpeedInMs() {
		return speedInMs;
	}

	public String getImageHeight() {
		return imageHight;
	}

	public Object getProperty(String key) {
		return getImageHeight();
	}
}
