package edu.matc.entjava.persistence;

import edu.matc.entjava.entity.MediaItem;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;

public class MediaItemDao {

    private final Logger logger = LogManager.getLogger(this.getClass());
    private final SessionFactory sessionFactory =
            SessionFactoryProvider.getSessionFactory();

    public MediaItem getById(Long id) {
        Session session = sessionFactory.openSession();
        MediaItem item = session.get(MediaItem.class, id);
        session.close();
        return item;
    }

    public Long insert(MediaItem item) {
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();

        session.persist(item);
        tx.commit();

        Long id = item.getId();
        session.close();
        return id;
    }

    public void update(MediaItem item) {
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();

        session.merge(item);
        tx.commit();
        session.close();
    }

    public void delete(MediaItem item) {
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();

        session.remove(item);
        tx.commit();
        session.close();
    }

    public List<MediaItem> getAll() {
        Session session = sessionFactory.openSession();
        List<MediaItem> items =
                session.createQuery("FROM MediaItem", MediaItem.class)
                        .getResultList();
        session.close();
        return items;
    }
}