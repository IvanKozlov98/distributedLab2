package db;

import db.strategiesInsert.StrategyOfSaving;

import java.util.List;


/**
 * this is interface on standart CRUD
 * */
public interface Dao<T> {
    T get(long id);

    List<T> getAll();

    void save(T object);

    void saveAll(List<T> objects, StrategyOfSaving<T> strategyOfSaving);

    void update(T object, String[] params);

    void delete(T object);

    void clear();
}
