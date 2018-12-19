package edu.matc.legendsmith.persistence;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;
import java.util.Map;


/**
 * The type User legendary data handler. Used to query entity types.
 *
 * @param <T> the type parameter
 */
public class UserLegendaryDataHandler<T> {
    private final Logger logger = LogManager.getLogger(this.getClass());
    private Class<T> type;

    /**
     * Instantiates a new User legendary data handler.
     *
     * @param type the type
     */
    public UserLegendaryDataHandler(Class<T> type) {
        this.type = type;
    }

    /**
     * Returns a specific entity by 2 foreign keys.
     *
     * @param fk1name the fk 1 name
     * @param fk1     the fk field
     * @param fk2name the fk 2 name
     * @param fk2     the fk field
     * @return the entity
     */
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
