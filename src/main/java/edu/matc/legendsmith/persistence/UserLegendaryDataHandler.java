package edu.matc.legendsmith.persistence;

import edu.matc.legendsmith.entity.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;
import java.util.Map;

public class UserLegendaryDataHandler<T> {
    private final Logger logger = LogManager.getLogger(this.getClass());
    private Class<T> type;

    UserLegendaryDataHandler(Class<T> type) {
        this.type = type;
    }

    public T returnEntityByForeignKeys(String fk1name, int fk1, String fk2name, int fk2) {
        GenericDao dao = new GenericDao(type);

        Map<String, Integer> map = new HashMap<>();

        map.put(fk1name, fk1);
        map.put(fk2name, fk2);

        T entity = (T)dao.findByPropertyEqual(map);

        if (entity == null) {
            logger.error("Null value returned.");
        }

        return entity;
    }



}
