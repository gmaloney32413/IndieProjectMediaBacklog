package edu.matc.entjava.persistence;

import edu.matc.entjava.entity.User;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.criteria.HibernateCriteriaBuilder;

import java.util.List;

/**
 * The type User dao.
 */
public class UserDao {

    /**
     * Logger for this class.
     */
    private final Logger logger = LogManager.getLogger(this.getClass());

    /**
     * Hibernate SessionFactory used to create database sessions.
     */
    SessionFactory sessionFactory = SessionFactoryProvider.getSessionFactory();


    /**
     * Gets by id.
     *
     * @param id the id
     * @return the by id
     */
    public User getById(int id) {
        Session session = sessionFactory.openSession();
        User user = session.get(User.class, id);
        session.close();
        return user;
    }


    /**
     * Update.
     *
     * @param user the user
     */
    public void update(User user) {
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        session.merge(user);
        tx.commit();
        session.close();
    }


    /**
     * Insert int.
     *
     * @param user the user
     * @return the int
     */
    public Long insert(User user) {
        Long id;
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();

        session.persist(user);
        id = user.getId();

        tx.commit();
        session.close();
        return id;
    }


    /**
     * Delete.
     *
     * @param user the user
     */
    public void delete(User user) {
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        session.delete(user);
        tx.commit();
        session.close();
    }


    /**
     * Gets all.
     *
     * @return the all
     */
    public List<User> getAll() {

        Session session = sessionFactory.openSession();

        HibernateCriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<User> criteria = builder.createQuery(User.class);
        Root<User> root = criteria.from(User.class);

        List<User> users = session.createQuery(criteria).getResultList();

        logger.debug("List of users retrieved: {}", users);

        session.close();
        return users;
    }
}
