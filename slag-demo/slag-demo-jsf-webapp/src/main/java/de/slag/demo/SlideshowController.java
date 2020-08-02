package de.slag.demo;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Properties;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

@ManagedBean
@SessionScoped
public class SlideshowController {

	private final List<String> images = new ArrayList<>();

	private int nextIdx = 0;

	private Integer speedInMs;

	private String cacheFolder;

	private String imageHight;

	@PostConstruct
	public void init() {
		final Properties properties = new ConfigPropertiesBuilder().build();

		speedInMs = Integer.valueOf(properties.getProperty("slideshow.speed"));
		cacheFolder = properties.getProperty("slideshow.cache");
		imageHight = properties.getProperty("slideshow.imageHeight", "350");

		initImages();

		this.getClass();
	}

	private void initImages() {
		images.clear();
		final Path path = Paths.get(cacheFolder);
		List<Path> collect;
		try {
			collect = Files.walk(path).collect(Collectors.toList());
		} catch (IOException e1) {
			throw new RuntimeException();
		}

		final List<Path> collect2 = collect.stream().filter(Files::isRegularFile)
				.filter(f -> f.getFileName().toString().endsWith(".jpg")).collect(Collectors.toList());

		collect2.forEach(f -> images.add(f.toString()));
		Collections.shuffle(images);
	}

	public Object getImage() {
		try {
			return getImageFromFileSystem();
		} catch (IOException e) {
			throw new RuntimeException();
		}
	}

	public StreamedContent getImageFromFileSystem() throws IOException {
		String file = images.get(nextIdx);
		if (images.size() > nextIdx + 1) {
			nextIdx = nextIdx + 1;
		} else {
			nextIdx = 0;
		}

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
