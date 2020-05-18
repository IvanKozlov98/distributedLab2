package db;

import db.strategiesInsert.BatchStrategy;
import db.strategiesInsert.PrepareStatementStrategy;
import db.strategiesInsert.StatementStrategy;
import db.strategiesInsert.StrategyOfSaving;
import generated.org.openstreetmap.osm._0.Node;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;
import java.util.Properties;

public class MainDatabase {

    final static Logger logger = LoggerFactory.getLogger(MainDatabase.class);

    public Connection getConnection() throws SQLException
    {
        logger.info("Start getConnection");
        Properties props = new Properties();
        URL res = getClass().getClassLoader().getResource("database.properties");
        try (InputStream in = Files.newInputStream(Paths.get(res.toURI()))) {
            props.load(in);
        } catch (IOException e) {
            logger.info("Error in file", e);
        } catch (URISyntaxException ee) {
            logger.info("Error in file uri", ee);
        }
        String drivers = props.getProperty("jdbc.drivers");
        if (drivers != null) {
            System.setProperty("jdbc.drivers", drivers);
        }
        logger.info("Driver successfully setuped");

        String url = props.getProperty("jdbc.url");
        String name = props.getProperty("jdbc.username");
        String password = props.getProperty("jdbc.password");
        logger.info("before DriverManager getConnection");
        return DriverManager.getConnection(url, name, password);
    }



    private void savingRecordsByStrategy(
            NodeDao nodeDao,
            List<Node> nodes,
            StrategyOfSaving<Node> strategy
    ) {
        try {
            long start = System.currentTimeMillis();
            nodeDao.saveAll(nodes, strategy);
            long finish = System.currentTimeMillis();
            long timeConsumedMillis = finish - start;
            logger.info("Time strategy " + strategy.toString() + " is " + timeConsumedMillis);
            nodeDao.clear();
        } catch (SQLException e) {
            logger.warn("some error in exeucting", e);
        }
    }

    public void run(List<Node> nodes) {

        try (Connection connection = getConnection()) {
            //DataBaseInitializer initializer = new DataBaseInitializer();
            //initializer.initDb(connection);

            NodeDao nodeDao = new NodeDao(connection);
            // first strategy
            boolean autoCommit = connection.getAutoCommit();
            connection.setAutoCommit(false);
            StrategyOfSaving<Node> prepareStatementStrategy = new PrepareStatementStrategy<>(connection);
            savingRecordsByStrategy(
                    nodeDao, nodes, prepareStatementStrategy
            );
            // second strategy
            StrategyOfSaving<Node> statementStrategy = new StatementStrategy<>(connection);
            savingRecordsByStrategy(
                    nodeDao, nodes, statementStrategy
            );
            // third strategy
            StrategyOfSaving<Node> batchStrategy = new BatchStrategy<>(connection);
            savingRecordsByStrategy(
                    nodeDao, nodes, batchStrategy
            );

        } catch (SQLException e) {
            logger.warn("Some sql error", e);
        }
    }
}
