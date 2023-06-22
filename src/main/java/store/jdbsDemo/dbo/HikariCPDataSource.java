package store.jdbsDemo.dbo;

import java.beans.PropertyVetoException;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

public class HikariCPDataSource {
	private static HikariConfig config = new HikariConfig();
	private static HikariDataSource ds;
	private static Properties properties = new Properties();
	public static final String PATH_TO_PROPERTIES = "src/main/resources/db.properties";

//	static {
//
//		config.setJdbcUrl("jdbc:postgresql://localhost:5432/store");
//		config.setDriverClassName("org.postgresql.Driver");
//		config.setUsername("postgres");
//		config.setPassword("sql");
//		config.addDataSourceProperty("cachePrepStmts", "true");
//		config.addDataSourceProperty("prepStmtCacheSize", "250");
//		config.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");
//		ds = new HikariDataSource(config);
//
//	}
	static {
		properties = getProperties();
		try {
			Class.forName(properties.getProperty("db.driver"));
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		config.setJdbcUrl(properties.getProperty("db.url"));
		config.setDriverClassName(properties.getProperty("db.driver"));
		config.setUsername(properties.getProperty("db.user"));
		config.setPassword(properties.getProperty("db.password"));
		config.addDataSourceProperty("cachePrepStmts", "true");
		config.addDataSourceProperty("prepStmtCacheSize", "250");
		config.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");
		ds = new HikariDataSource(config);
	}

	public static Connection getConnection() throws SQLException {
		return ds.getConnection();
	}

	private HikariCPDataSource() {

	}

	public static HikariDataSource getInstance() throws IOException, SQLException, PropertyVetoException {
		if (ds == null) {
			synchronized (HikariCPDataSource.class) {
				ds = new HikariDataSource(config);
			}
		}
		return ds;
	}

	private static Properties getProperties() {
		try (InputStream fis = HikariCPDataSource.class.getClassLoader().getResourceAsStream("db.properties")) {
			Properties prop = new Properties();
			prop.load(fis);
			return prop;
		} catch (IOException e) {
			throw new RuntimeException(e.getMessage());
		}
	}
}
