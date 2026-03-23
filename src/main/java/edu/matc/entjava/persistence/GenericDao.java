package edu.matc.entjava.persistence;

import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.SessionFactory;
import org.hibernate.query.criteria.HibernateCriteriaBuilder;

import java.util.List;

/**
 * The type Generic dao.
 *
 * @param <T> the type parameter
 */
public class GenericDao<T> {

    private final Logger logger = LogManager.getLogger(this.getClass());
    private final SessionFactory sessionFactory =
            SessionFactoryProvider.getSessionFactory();
    private Class<T> type;

    /**
     * Instantiates a new Generic dao.
     *
     * @param type the type
     */
    public GenericDao(Class<T> type) {
        this.type = type;
    }

    /**
     * Gets by id.
     *
     * @param id the id
     * @return the by id
     */
    public T getById(Long id) {
        Session session = sessionFactory.openSession();
        T entity = session.get(type, id);
        session.close();
        return entity;
    }

    /**
     * Gets all.
     *
     * @return the all
     */
    public List<T> getAll() {
        Session session = sessionFactory.openSession();

        HibernateCriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<T> query = builder.createQuery(type);
        Root<T> root = query.from(type);
        query.select(root);

        List<T> list = session.createQuery(query).getResultList();

        session.close();
        return list;
    }

    /**
     * Insert long.
     *
     * @param entity the entity
     * @return the long
     */
    public Long insert(T entity) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        Long id = (Long) session.save(entity);

        transaction.commit();
        session.close();
        return id;
    }

    /**
     * Update.
     *
     * @param entity the entity
     */
    public void update(T entity) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        session.merge(entity);

        transaction.commit();
        session.close();
    }

    /**
     * Delete.
     *
     * @param entity the entity
     */
    public void delete(T entity) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        session.remove(entity);

        transaction.commit();
        session.close();
    }

    /**
     * Gets by property equal.
     *
     * @param propertyName the property name
     * @param value        the value
     * @return the by property equal
     */
    public List<T> getByPropertyEqual(String propertyName, Object value) {

        Session session = sessionFactory.openSession();

        HibernateCriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<T> query = builder.createQuery(type);
        Root<T> root = query.from(type);

        query.select(root)
                .where(builder.equal(root.get(propertyName), value));

        List<T> list = session.createQuery(query).getResultList();

        session.close();
        return list;
    }
}