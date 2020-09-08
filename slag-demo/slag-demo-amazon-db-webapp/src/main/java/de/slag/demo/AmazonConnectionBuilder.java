package de.slag.demo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Objects;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class AmazonConnectionBuilder {

	public static final String RDS_PORT = "RDS_PORT";
	public static final String RDS_PASSWORD = "RDS_PASSWORD";
	public static final String RDS_USERNAME = "RDS_USERNAME";
	public static final String RDS_DB_NAME = "RDS_DB_NAME";
	public static final String RDS_HOSTNAME = "RDS_HOSTNAME";
	public static final Log LOG = LogFactory.getLog(AmazonConnectionBuilder.class);

	private String dbName;
	private String userName;
	private String password;
	private String hostname;
	private String port;

	public AmazonConnectionBuilder() {
		dbName = System.getProperty(RDS_DB_NAME);
		userName = System.getProperty(RDS_USERNAME);
		password = System.getProperty(RDS_PASSWORD);
		hostname = System.getProperty(RDS_HOSTNAME);
		port = System.getProperty(RDS_PORT);
	}

	public AmazonConnectionBuilder withPort(String port) {
		this.port = port;
		return this;
	}

	public AmazonConnectionBuilder withHostname(String hostname) {
		this.hostname = hostname;
		return this;
	}

	public AmazonConnectionBuilder withPassword(String password) {
		this.password = password;
		return this;
	}

	public AmazonConnectionBuilder withUserName(String userName) {
		this.userName = userName;
		return this;
	}

	public AmazonConnectionBuilder withDbName(String dbName) {
		this.dbName = dbName;
		return this;
	}

	public Connection build() throws AmazonConnectionBuilderException {
		return getRemoteConnection();
	}

	private Connection getRemoteConnection() throws AmazonConnectionBuilderException {
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

	@Override
	public String toString() {
		return "AmazonConnectionBuilder [dbName=" + dbName + ", userName=" + userName + ", password=" + password
				+ ", hostname=" + hostname + ", port=" + port + "]";
	}

}
