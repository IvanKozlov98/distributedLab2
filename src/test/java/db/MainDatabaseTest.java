package db;


import db.strategiesInsert.BatchStrategy;
import db.strategiesInsert.PrepareStatementStrategy;
import db.strategiesInsert.StatementStrategy;
import generated.org.openstreetmap.osm._0.Node;
import org.junit.Before;
import org.junit.Test;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static java.util.stream.Collectors.counting;
import static org.junit.Assert.*;
import static java.util.stream.Collectors.*;

public class MainDatabaseTest {

    List<Node> nodes = new ArrayList<>();

    @Before
    public void configureNodes() {
        for (int i = 0; i < 1000; ++i) {
            nodes.add(new Node());
        }
    }


    enum TypeInsert {
        BATCH, STATEMENT, PREPARED_STATEMENT
    };

    void insertByType(TypeInsert typeInsert) {
        MainDatabase mainDatabase = new MainDatabase();
        try (Connection connection = mainDatabase.getConnection()) {
            NodeDao nodeDao = new NodeDao(connection);
            nodeDao.clear();
            switch (typeInsert) {
                case BATCH: {
                    nodeDao.saveAll(nodes, new BatchStrategy<>(connection));
                    break;
                }
                case STATEMENT: {
                    nodeDao.saveAll(nodes, new StatementStrategy<>(connection));
                    break;
                }
                case PREPARED_STATEMENT: {
                    nodeDao.saveAll(nodes, new PrepareStatementStrategy<>(connection));
                    break;
                }
            }

            List<Node> nodesInDb = nodeDao.getAll();
            assertEquals(nodes.size(), nodesInDb.size());
            assertEquals(nodes.get(0), nodesInDb.get(0));
            assertEquals(nodesInDb.stream().collect( groupingBy( k -> k, counting())),
                    (nodes.stream().collect( groupingBy( k -> k, counting()) )));

        } catch (SQLException e) {
            fail();
        }
    }

    @Test
    public void insertBatch() {
        insertByType(TypeInsert.BATCH);
    }


    @Test
    public void insertStatement() {
        insertByType(TypeInsert.STATEMENT);
    }

    @Test
    public void insertPreparedStatement() {
        insertByType(TypeInsert.PREPARED_STATEMENT);
    }
}