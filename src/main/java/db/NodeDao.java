package db;


import db.strategiesInsert.StrategyOfSaving;
import generated.org.openstreetmap.osm._0.Node;

import java.math.BigInteger;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class NodeDao implements Dao<Node> {

    private Connection connection;

    NodeDao(Connection connection) {
        this.connection = connection;
    }


    @Override
    public Node get(long id) {
        return null;
    }

    @Override
    public List<Node> getAll() {
        List<Node> nodes = new ArrayList<>();
        try(Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM nodes");) {
            while(resultSet.next()) {
                Node node = new Node();
                node.setId(BigInteger.valueOf(resultSet.getLong(1)));
                node.setLat(resultSet.getDouble(2));
                node.setLon(resultSet.getDouble(3));
                node.setUser(resultSet.getString(4));
                node.setUid(BigInteger.valueOf(resultSet.getLong(5)));
                node.setUid(BigInteger.valueOf(resultSet.getLong(6)));
                node.setUid(BigInteger.valueOf(resultSet.getLong(7)));
                nodes.add(node);
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return nodes;
    }

    @Override
    public void save(Node object) {

    }

    @Override
    public void saveAll(List<Node> objects, StrategyOfSaving<Node> strategyOfSaving) throws SQLException{
        boolean autoCommit = connection.getAutoCommit();
        connection.setAutoCommit(false);
        strategyOfSaving.saveAll(objects, new NodeQueryHelper());
        connection.commit();
        connection.setAutoCommit(autoCommit);
    }

    @Override
    public void update(Node object, String[] params) {

    }

    @Override
    public void delete(Node object) {
    }

    @Override
    public void clear() {
        try(Statement statement = connection.createStatement()) {
            statement.executeUpdate("DELETE FROM nodes");
        }catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
