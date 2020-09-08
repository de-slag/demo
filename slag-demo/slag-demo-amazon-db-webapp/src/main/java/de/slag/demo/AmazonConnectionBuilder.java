package de.slag.demo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Objects;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class AmazonConnectionBuilder {

	private static final String RDS_PORT = "RDS_PORT";
	private static final String RDS_PASSWORD = "RDS_PASSWORD";
	private static final String RDS_USERNAME = "RDS_USERNAME";
	private static final String RDS_DB_NAME = "RDS_DB_NAME";
	private static final String RDS_HOSTNAME = "RDS_HOSTNAME";
	private static final Log LOG = LogFactory.getLog(AmazonConnectionBuilder.class);

	private String dbName;
	private String userName;
	private String password;
	private String hostname;
	private String port;

	public Connection build() {
		return getRemoteConnection();
	}

	public AmazonConnectionBuilder() {
		dbName = System.getenv(RDS_DB_NAME);
		userName = System.getenv(RDS_USERNAME);
		password = System.getenv(RDS_PASSWORD);
		hostname = System.getenv(RDS_HOSTNAME);
		port = System.getenv(RDS_PORT);
	}

	private Connection getRemoteConnection() {
		Objects.requireNonNull(dbName, RDS_DB_NAME + " is null");
		Objects.requireNonNull(userName, RDS_USERNAME + " is null");
		Objects.requireNonNull(password, RDS_PASSWORD + " is null");
		Objects.requireNonNull(hostname, RDS_HOSTNAME + " is null");
		Objects.requireNonNull(port, RDS_PORT + " is null");

		loadDriver();

		final String jdbcUrl = "jdbc:mysql://" + hostname + ":" + port + "/" + dbName + "?user=" + userName
				+ "&password=" + password;
		LOG.info("jdbc url: " + jdbcUrl);
		LOG.trace("Getting remote connection with connection string from environment variables.");
		try {
			Connection con = DriverManager.getConnection(jdbcUrl);
			LOG.info("Remote connection successful.");
			return con;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	private void loadDriver() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			throw new RuntimeException(e);
		}
	}

}
