package db;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class DataBaseInitializer {
    final static Logger logger = LoggerFactory.getLogger(DataBaseInitializer.class);
/**
 * id, lat, lon, user, uid," +
 *                 " version, changeset
 *
 */

    private static final String createQuery = "Create Table Nodes (" +
            "id bigint, " +
            "lat REAL," +
            "lon REAL," +
            "nameOfUser VARCHAR(100)," +
            "uid bigint," +
            "version bigint," +
            "changeset bigint" +
            ");";

    private void createTableForNode(Connection connection) {
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate(createQuery);

        } catch (SQLException e) {
            logger.warn("Error in create table for node", e);
        }


    }

    public void initDb(Connection connection) {
        createTableForNode(connection);
    }
}
