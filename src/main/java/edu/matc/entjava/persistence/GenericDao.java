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

public class GenericDao<T> {

    private final Logger logger = LogManager.getLogger(this.getClass());
    private final SessionFactory sessionFactory =
            SessionFactoryProvider.getSessionFactory();
    private Class<T> type;

    public GenericDao(Class<T> type) {
        this.type = type;
    }

    public T getById(Long id) {
        Session session = sessionFactory.openSession();
        T entity = session.get(type, id);
        session.close();
        return entity;
    }

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

    public Long insert(T entity) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        Long id = (Long) session.save(entity);

        transaction.commit();
        session.close();
        return id;
    }

    public void update(T entity) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        session.merge(entity);

        transaction.commit();
        session.close();
    }

    public void delete(T entity) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        session.remove(entity);

        transaction.commit();
        session.close();
    }

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