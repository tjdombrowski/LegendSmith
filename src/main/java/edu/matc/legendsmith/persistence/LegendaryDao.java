package edu.matc.legendsmith.persistence;

import edu.matc.legendsmith.entity.Legendary;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Root;
import java.util.List;


/**
 * The type Legendary dao.
 */
public class LegendaryDao {

    SessionFactory sessionFactory = SessionFactoryProvider.getSessionFactory();
    private final Logger logger = LogManager.getLogger(this.getClass());

    /**
     * Gets legendaries by name. If nothing is specified, retrieve everything.
     *
     * TODO alter this so that it retrieves by weapon type as well
     *
     * @param searchTerm the search term
     * @return the legendaries by name
     */
    public List<Legendary> getLegendariesByName(String searchTerm) {
        Session session = sessionFactory.openSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Legendary> query = builder.createQuery(Legendary.class);
        Root<Legendary> root = query.from(Legendary.class);
        List<Legendary> legendaries;

        if (searchTerm.isEmpty()) {
            legendaries = session.createQuery(query).getResultList();
        } else {
            Expression<String> propertyPath = root.get("name"); //beginning of 'where'
            query.where(builder.like(propertyPath, "%" + searchTerm + "%"));

            legendaries = session.createQuery(query).getResultList();
        }

        session.close();

        return legendaries;
    }

    /**
     * Gets legendary by id.
     *
     * @param id the id
     * @return the legendary by id
     */
    public Legendary getLegendaryById(int id) {
        Session session = sessionFactory.openSession();

        Legendary legendary = session.get(Legendary.class, id);

        session.close();


        return legendary;
    }

    /**
     * Update a legendary.
     *
     * @param legendary to be inserted or updated
     */
    public void saveOrUpdate(Legendary legendary) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.saveOrUpdate(legendary);
        transaction.commit();
        session.close();
    }

    /**
     * Insert a legendary.
     *
     * @param legendary Legendary to be inserted
     * @return the id
     */
    public int insert(Legendary legendary) {
        int id = 0;
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        id = (int)session.save(legendary);
        transaction.commit();
        session.close();
        return id;
    }

    /**
     * Delete a legendary.
     *
     * @param legendary Legendary to be deleted
     */
    public void delete(Legendary legendary) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.delete(legendary);
        transaction.commit();
        session.close();
    }


}
