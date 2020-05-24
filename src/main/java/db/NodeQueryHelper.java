package db;

import db2.utils.Model.Node;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class NodeQueryHelper implements QueryHelper<Node> {
    /**
     * Возвращает sql запрос для вставки новой записи в базу данных.
     * <p/>
     * INSERT INTO [Table] ([column, column, ...]) VALUES (?, ?, ...);
     */
    @Override
    public String getCreateQuery() {
        return "INSERT INTO Nodes (id, lat, lon, nameOfUser, uid," +
                " version, changeset) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?);";
    }

    /**
     * Устанавливает аргументы insert запроса в соответствии со значением полей объекта object.
     *
     * @param statement
     * @param object
     */
    @Override
    public void prepareStatementForInsert(PreparedStatement statement, Node object) throws SQLException {
        statement.setLong(1, object.getId().longValue());
        statement.setDouble(2, object.getLat());
        statement.setDouble(3, object.getLon());
        statement.setString(4, object.getUser());
        statement.setLong(5, object.getUid().longValue());
        statement.setLong(6, object.getVersion().longValue());
        statement.setLong(7, object.getChangeset().longValue());
    }

    private static void appendToValues(StringBuilder stringBuilder, String var) {
        stringBuilder.append(var);
        stringBuilder.append(',');
    }

    private static String configureValues(Node object) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append('(');
        appendToValues(stringBuilder, object.getId().toString());
        appendToValues(stringBuilder, object.getLat().toString());
        appendToValues(stringBuilder, object.getLon().toString());
        appendToValues(stringBuilder, "\'" + object.getUser() + "\'");
        appendToValues(stringBuilder, object.getUid().toString());
        appendToValues(stringBuilder, object.getVersion().toString());
        stringBuilder.append(object.getChangeset().toString());
        stringBuilder.append(')');
        return stringBuilder.toString();
    }

    /**
     * Возвращает sql запрос для вставки новой записи в базу данных.
     * <p/>
     *
     * @param object
     */
    @Override
    public String getCreateQuery(Node object) {
        return "INSERT INTO Nodes (id, lat, lon, nameOfUser, uid," +
                " version, changeset) " +
                "VALUES " +
                configureValues(object)
                + ";";
    }
}
