package edu.matc.legendsmith.persistence;

import edu.matc.legendsmith.entity.Legendary;
import edu.matc.legendsmith.entity.PrimaryItem;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.*;

public class PrimaryItemDao {

    SessionFactory sessionFactory = SessionFactoryProvider.getSessionFactory();
    private final Logger logger = LogManager.getLogger(this.getClass());

    public List<PrimaryItem> getAllPrimaryItems() {
        Session session = sessionFactory.openSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<PrimaryItem> query = builder.createQuery(PrimaryItem.class);
        Root<PrimaryItem> root = query.from(PrimaryItem.class);
        List<PrimaryItem> orders = session.createQuery(query).getResultList();
        session.close();
        return orders;
    }

    public void insert(PrimaryItem primaryItem) {
        int id = 0;
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        id = (int)session.save(primaryItem);
        transaction.commit();
        session.close();
        return id;
    }


    public void saveOrUpdate(PrimaryItem primaryItem) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.saveOrUpdate(primaryItem);
        transaction.commit();
        session.close();
    }

    public void delete(PrimaryItem primaryItem) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.delete(primaryItem);
        transaction.commit();
        session.close();
    }
}
