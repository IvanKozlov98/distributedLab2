package db.strategiesInsert;

import db.QueryHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class PrepareStatementStrategy<T> implements StrategyOfSaving<T> {

    final static Logger logger = LoggerFactory.getLogger(PrepareStatementStrategy.class);
    private Connection connection;


    public PrepareStatementStrategy(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void saveAll(List<T> objects, QueryHelper<T> queryHelper) {
        if (objects.isEmpty())
            return;
        String createQuery = queryHelper.getCreateQuery();
        try(PreparedStatement preparedStatement = connection.prepareStatement(createQuery)) {
            for (T object : objects) {
                queryHelper.prepareStatementForInsert(preparedStatement, object);
                int count = preparedStatement.executeUpdate();
                if (count == -1) {
                    logger.warn("Some error on insert");
                }
            }
        } catch (SQLException e) {
            logger.warn("preparedStatement Error", e);
        }
    }
}
