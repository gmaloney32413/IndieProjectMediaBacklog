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

/**
 * The type Media item dao.
 */
public class MediaItemDao {

    private final Logger logger = LogManager.getLogger(this.getClass());
    private final SessionFactory sessionFactory =
            SessionFactoryProvider.getSessionFactory();

    /**
     * Gets by id.
     *
     * @param id the id
     * @return the by id
     */
    public MediaItem getById(Long id) {
        Session session = sessionFactory.openSession();
        MediaItem item = session.get(MediaItem.class, id);
        session.close();
        return item;
    }

    /**
     * Insert long.
     *
     * @param item the item
     * @return the long
     */
    public Long insert(MediaItem item) {
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();

        session.persist(item);
        tx.commit();

        Long id = item.getId();
        session.close();
        return id;
    }

    /**
     * Update.
     *
     * @param item the item
     */
    public void update(MediaItem item) {
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        session.merge(item);
        tx.commit();
        session.close();
    }

    /**
     * Delete.
     *
     * @param item the item
     */
    public void delete(MediaItem item) {
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        session.remove(item);
        tx.commit();
        session.close();
    }

    /**
     * Gets all.
     *
     * @return the all
     */
    public List<MediaItem> getAll() {
        Session session = sessionFactory.openSession();
        HibernateCriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<MediaItem> query = builder.createQuery(MediaItem.class);
        Root<MediaItem> root = query.from(MediaItem.class);
        List<MediaItem> items = session.createSelectionQuery( query ).getResultList();

        logger.debug("The list of media items " + items);
        session.close();
        return items;
    }

    /**
     * Search by title list.
     *
     * @param searchTerm the search term
     * @return the list
     */
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


    /**
     * Search by title and type list.
     *
     * @param searchTerm the search term
     * @param mediaType  the media type
     * @return the list
     */
    public List<MediaItem> searchByTitleAndType(String searchTerm, String mediaType) {
        logger.debug("DAO searchByTitleAndType called with searchTerm: '" + searchTerm + "', mediaType: '" + mediaType + "'");
        Session session = sessionFactory.openSession();
        HibernateCriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<MediaItem> query = builder.createQuery(MediaItem.class);
        Root<MediaItem> root = query.from(MediaItem.class);

        // Basic title search (case-insensitive)
        var titlePredicate = builder.like(builder.lower(root.get("title")), "%" + searchTerm.toLowerCase() + "%");

        if (mediaType != null && !mediaType.equalsIgnoreCase("any")) {
            // Map form value to DB value
            String dbType;
            switch (mediaType.toLowerCase()) {
                case "movie":
                    dbType = "movie";
                    break;
                case "tv":
                    dbType = "tv_show";
                    break;
                default:
                    dbType = mediaType;
            }
            logger.debug("Filtering by DB mediaType: '" + dbType + "'");
            var typePredicate = builder.equal(root.get("mediaType"), dbType);
            query.where(builder.and(titlePredicate, typePredicate));
        } else {
            query.where(titlePredicate);
        }

        List<MediaItem> items = session.createSelectionQuery(query).getResultList();
        logger.debug("DAO search returned " + items.size() + " items");

        session.close();
        return items;
    }
}
