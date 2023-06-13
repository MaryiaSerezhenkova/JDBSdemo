package store.jdbsDemo.dbo;

import java.beans.PropertyVetoException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

public class HikariCPDataSource {
	private static HikariConfig config = new HikariConfig();
	private static HikariDataSource ds;

	static {
		config.setJdbcUrl("jdbc:postgresql://localhost:5432/store");
		config.setDriverClassName("org.postgresql.Driver");
		config.setUsername("postgres");
		config.setPassword("sql");
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
}
