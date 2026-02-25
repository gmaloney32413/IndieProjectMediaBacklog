package edu.matc.entjava.persistence;

import edu.matc.entjava.entity.BacklogEntry;
import edu.matc.entjava.entity.MediaItem;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.criteria.HibernateCriteriaBuilder;

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
        HibernateCriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<MediaItem> query = builder.createQuery(MediaItem.class);
        Root<MediaItem> root = query.from(MediaItem.class);
        List<MediaItem> items = session.createSelectionQuery( query ).getResultList();

        logger.debug("The list of books " + items);
        session.close();
        return items;
    }

    public List<MediaItem> searchByTitle(String searchTerm) {
        Session session = sessionFactory.openSession();
        HibernateCriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<MediaItem> query = builder.createQuery(MediaItem.class);
        Root<MediaItem> root = query.from(MediaItem.class);

        query.select(root)
                .where(builder.like(builder.lower(root.get("title")), "%" + searchTerm.toLowerCase() + "%"));

        List<MediaItem> items = session.createSelectionQuery(query).getResultList();
        session.close();
        return items;
    }
}
