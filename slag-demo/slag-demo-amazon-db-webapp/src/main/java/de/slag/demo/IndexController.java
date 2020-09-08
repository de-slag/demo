package de.slag.demo;

import java.sql.Connection;
import java.sql.SQLException;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

@ManagedBean
@SessionScoped
public class IndexController {

	private static final Log LOG = LogFactory.getLog(IndexController.class);

	private String status;

	public String getVersion() {
		return IndexController.class.getPackage().getImplementationVersion();
	}

	public void submit() {
		status = "";
		final AmazonConnectionBuilder amazonConnectionBuilder = new AmazonConnectionBuilder();
		try (final Connection connection = amazonConnectionBuilder.build()) {
			status = getStatus() + "connection created: " + connection;
		} catch (SQLException e) {
			LOG.error(e);
		}
	}

	public String getStatus() {
		return status;
	}

}
