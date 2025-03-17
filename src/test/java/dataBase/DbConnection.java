package dataBase;

import ConfigUtility.ConfigReader;
import ConfigUtility.Configuration;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnection {
    private static DbConnection instance;
    private Connection connection;

    public DbConnection() throws SQLException {
        createConnection();
    }

    private void createConnection() throws SQLException {
        Configuration config = ConfigReader.readConfig("src/test/resources/generalConfiguration.xml");
        connection = DriverManager.getConnection(getPreparedURL(config), config.getDataBaseConfig().getUserName(), config.getDataBaseConfig().getPassword());
    }

    public static synchronized DbConnection getInstance() throws SQLException {
        if (instance == null) {
            instance = new DbConnection();
        }
        return instance;
    }

    private static String getPreparedURL(Configuration configuration) {
        return "jdbc:mysql://localhost:" + configuration.getDataBaseConfig().getPort() +
                "/" + configuration.getDataBaseConfig().getDataBase() +
                "?allowPublicKeyRetrieval=true&useSSL=false";
    }

    public Connection getConnection() {
        return connection;
    }
}
