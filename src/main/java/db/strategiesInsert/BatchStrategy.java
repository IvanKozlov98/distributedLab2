package db.strategiesInsert;

import db.QueryHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class BatchStrategy<T> implements StrategyOfSaving<T> {

    final static Logger logger = LoggerFactory.getLogger(BatchStrategy.class);
    private Connection connection;


    public BatchStrategy(Connection connection) {
        this.connection = connection;
    }


    @Override
    public void saveAll(List<T> objects, QueryHelper<T> helper) {
        int BATCH_SIZE = Math.min(1000, objects.size());
        try (Statement statement = connection.createStatement()) {
            int tempSize = 0;
            boolean autoCommit = connection.getAutoCommit();
            connection.setAutoCommit(false);
            for (T object : objects) {
                statement.addBatch(helper.getCreateQuery(object));
                if (tempSize + 1 < BATCH_SIZE) {
                    tempSize++;
                } else {
                    tempSize = 0;
                    statement.executeBatch();
                    connection.commit();
                }
            }
            connection.setAutoCommit(autoCommit);
        }  catch (SQLException e) {
            logger.warn("statement Error", e);
        }
    }
}
