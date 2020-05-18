package db.strategiesInsert;

import db.QueryHelper;

import java.util.List;

public interface StrategyOfSaving<T> {
    void saveAll(List<T> objects, QueryHelper<T> helper);
}
