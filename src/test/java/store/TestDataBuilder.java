package store;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import store.jdbsDemo.dbo.HikariCPDataSource;

public class TestDataBuilder {

    private Connection connection;
    private Statement statement;

    public void createTables() {
        try {
            connection = HikariCPDataSource.getConnection();
            statement = connection.createStatement();
            StringBuilder stringBuilder1 = getStringBuilder("src/test/resources/ddl/create_tables.sql");
            statement.execute(stringBuilder1.toString());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void fillTables() {
        StringBuilder stringBuilder2 = getStringBuilder("src/test/resources/ddl/insert_tables.sql");
        try {
            statement.execute(stringBuilder2.toString());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private static StringBuilder getStringBuilder(String filename) {
        StringBuilder stringBuilder = new StringBuilder();
        File file = new File(filename);
        try (FileReader fr = new FileReader(file)) {
            int content;
            while ((content = fr.read()) != -1) {
                stringBuilder.append((char) content);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return stringBuilder;
    }

    public void close() {
        try {
            statement.close();
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}