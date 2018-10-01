package edu.matc.legendsmith.persistence;

import edu.matc.legendsmith.entity.Legendary;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Root;
import java.util.List;

public class LegendaryDao {

    SessionFactory sessionFactory = SessionFactoryProvider.getSessionFactory();
    private final Logger logger = LogManager.getLogger(this.getClass());

    public List<Legendary> getAllLegendaries() {
        Session session = sessionFactory.openSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Legendary> query = builder.createQuery(Legendary.class);
        Root<Legendary> root = query.from(Legendary.class);
        List<Legendary> legendaries = session.createQuery(query).getResultList();

        session.close();

        return legendaries;
    }

    public List<Legendary> getLegendariesByName(String searchTerm) {
        Session session = sessionFactory.openSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Legendary> query = builder.createQuery(Legendary.class);
        Root<Legendary> root = query.from(Legendary.class);

        Expression<String> propertyPath = root.get("name"); //beginning of 'where'
        query.where(builder.like(propertyPath, "%" + searchTerm + "%"));

        List<Legendary> legendaries = session.createQuery(query).getResultList();

        session.close();

        return legendaries;
    }

    public Legendary getLegendaryById(int id) {
        Session session = sessionFactory.openSession();

        Legendary legendary = session.get(Legendary.class, id);

        session.close();


        return legendary;
    }



}
