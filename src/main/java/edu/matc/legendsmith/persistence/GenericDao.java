package edu.matc.legendsmith.persistence;

import edu.matc.legendsmith.entity.*;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Root;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 * TODO add log statements
 *
 * A generic dao that receives an entity type and performs database operations with it.
 */
public class GenericDao<T> {
    private Class<T> type;
    private final Logger logger = LogManager.getLogger(this.getClass());

    public GenericDao(Class<T> type) {
        this.type = type;
    }

    /**
     * Gets everything from the table.
     *
     * @return list
     */
    public List<T> getAll() {
        Session session = getSession();

        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<T> query = builder.createQuery(type);
        Root<T> root = query.from(type);
        List<T> list = session.createQuery(query).getResultList();

        session.close();

        return list;
    }

    /**
     * Gets an entity by id
     * @param id entity id to search by
     * @return entity
     */
    public <T> T getById(int id) {
        Session session = getSession();

        T entity = (T)session.get(type, id);
        session.close();

        return entity;
    }

    /**
     * Get an entity by it's name.
     *
     * TODO make this part more dynamic. Not everything I'm pulling has a "name" column.
     *
     * @param searchTerm the search term from the user
     * @return list
     */
    public List<T> getByName(String searchTerm) {
        Session session = getSession();

        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<T> query = builder.createQuery(type);
        Root<T> root = query.from(type);

        Expression<String> propertyPath = root.get("name"); //beginning of 'where'
        query.where(builder.like(propertyPath, "%" + searchTerm + "%"));

        List<T> list = session.createQuery(query).getResultList();

        session.close();

        return list;
    }

    /**
     * Inserts a new entity into the database.
     *
     * @param entity the entity being inserted
     * @return the id on the newly inserted entity
     */
    public int insert(T entity) {
        int id = 0;
        Session session = getSession();

        Transaction transaction = session.beginTransaction();
        id = (int)session.save(entity);
        transaction.commit();

        session.close();

        return id;
    }

    /**
     * Updates the entity.
     *
     * @param entity entity to be updated
     */
    public void saveOrUpdate(T entity) {
        Session session = getSession();

        Transaction transaction = session.beginTransaction();
        session.saveOrUpdate(entity);
        transaction.commit();

        session.close();
    }

    /**
     * Deletes the entity.
     *
     * @param entity entity to be deleted
     */
    public void delete(T entity) {
        Session session = getSession();
        Transaction transaction = session.beginTransaction();
        session.delete(entity);
        transaction.commit();
        session.close();
    }

    /**
     * Returns an open session from the SessionFactory.
     *
     * @return session
     */
    private Session getSession() {
        return SessionFactoryProvider.getSessionFactory().openSession();

    }

}
