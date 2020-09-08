package de.slag.demo;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

@ManagedBean
@SessionScoped
public class IndexController {

	private static final Log LOG = LogFactory.getLog(IndexController.class);

	private List<String> status = new ArrayList<>();
	
	@PostConstruct
	public void init() {
		status.clear();
		status.add(System.getenv("SLAG_TEST"));
		status.add(System.getProperty("SLAG_TEST"));
		status.add(System.getProperty(AmazonConnectionBuilder.RDS_DB_NAME));
	}

	public String getVersion() {
		return IndexController.class.getPackage().getImplementationVersion();
	}

	public void submit() {
		status.clear();
		status.add(System.getenv("SLAG_TEST"));
		final AmazonConnectionBuilder amazonConnectionBuilder = new AmazonConnectionBuilder();
		status.add(amazonConnectionBuilder.toString());
		try (final Connection connection = amazonConnectionBuilder.build()) {
			status.add(getStatus() + "connection created: " + connection);
		} catch (SQLException | AmazonConnectionBuilderException e) {
			LOG.error(e);
			status.add(e.getMessage());
		}
	}

	public String getStatus() {
		return String.join("\n", status);
	}

}
