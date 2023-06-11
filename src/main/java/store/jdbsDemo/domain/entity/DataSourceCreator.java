package store.jdbsDemo.domain.entity;

import java.beans.PropertyVetoException;
import java.io.IOException;
import java.sql.SQLException;

import javax.sql.DataSource;

public class DataSourceCreator {
//    private static DataSourceCreator instance;
//    private ComboPooledDataSource cpds;
//
//    private DataSourceCreator() throws IOException, SQLException, PropertyVetoException {
//        cpds = new ComboPooledDataSource();
//        cpds.setDriverClass("org.postgresql.Driver");
//        cpds.setJdbcUrl("jdbc:postgresql://localhost:5433/store");
//        cpds.setUser("postgres");
//        cpds.setPassword("postgres");
//        cpds.setMinPoolSize(5);
//        cpds.setAcquireIncrement(5);
//        cpds.setMaxPoolSize(20);
//        cpds.setMaxStatements(180);
//    }
//
//    public static DataSource getInstance() throws IOException, SQLException, PropertyVetoException {
//        if (instance == null) {
//            synchronized (DataSourceCreator.class) {
//                instance = new DataSourceCreator();
//            }
//        }
//        return instance.cpds;
//    }
}