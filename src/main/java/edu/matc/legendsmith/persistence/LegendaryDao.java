package edu.matc.legendsmith.persistence;

import edu.matc.legendsmith.entity.Legendary;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class LegendaryDao {

    SessionFactory sessionFactory = SessionFactoryProvider.getSessionFactory();
    private final Logger logger = LogManager.getLogger(this.getClass());

    public List<Legendary> getLegendaries() {
        Session session = sessionFactory.openSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Legendary> query = builder.createQuery(Legendary.class);
        Root<Legendary> root = query.from(Legendary.class);
        List<Legendary> legendaries = session.createQuery(query).getResultList();

        session.close();

        return legendaries;
    }


}
