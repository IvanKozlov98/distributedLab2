package db.strategiesInsert;

import db.QueryHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class StatementStrategy<T> implements StrategyOfSaving<T> {

    final static Logger logger = LoggerFactory.getLogger(StatementStrategy.class);
    private Connection connection;


    public StatementStrategy(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void saveAll(List<T> objects, QueryHelper<T> helper) {
        try (Statement statement = connection.createStatement()) {
            for (T object : objects) {
                statement.executeUpdate(helper.getCreateQuery(object));
            }
        }  catch (SQLException e) {
            logger.warn("statement Error", e);
        }
    }
}
