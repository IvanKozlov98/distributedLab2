package db;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public interface QueryHelper<T> {
    /**
     * Возвращает sql запрос для вставки новой записи в базу данных.
     * <p/>
     * INSERT INTO [Table] ([column, column, ...]) VALUES (?, ?, ...);
     */
    String getCreateQuery();

    /**
     * Устанавливает аргументы insert запроса в соответствии со значением полей объекта object.
     */
    void prepareStatementForInsert(PreparedStatement statement, T object) throws SQLException;

    /**
     * Возвращает sql запрос для вставки новой записи в базу данных.
     * <p/>
     * */
    String getCreateQuery(T object);

}
