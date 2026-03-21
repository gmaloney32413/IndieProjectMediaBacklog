package edu.matc.entjava.persistence;

import edu.matc.entjava.entity.MediaItem;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.criteria.HibernateCriteriaBuilder;

import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import java.util.List;

/**
 * DAO for MediaItem with search methods
 */
public class MediaItemDao extends GenericDao<MediaItem> {

    private final SessionFactory sessionFactory;

    public MediaItemDao() {
        super(MediaItem.class);
        sessionFactory = SessionFactoryProvider.getSessionFactory();
    }

    /**
     * Search MediaItems by title (case-insensitive)
     */
    public List<MediaItem> searchByTitle(String searchTerm) {
        try (Session session = sessionFactory.openSession()) {
            HibernateCriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<MediaItem> query = builder.createQuery(MediaItem.class);
            Root<MediaItem> root = query.from(MediaItem.class);

            Predicate titlePredicate = builder.like(
                    builder.lower(root.get("title")),
                    "%" + searchTerm.toLowerCase() + "%"
            );

            query.select(root).where(titlePredicate);

            return session.createQuery(query).getResultList();
        }
    }

    /**
     * Search MediaItems by title and mediaType (case-insensitive)
     */
    public List<MediaItem> searchByTitleAndType(String searchTerm, String mediaType) {
        try (Session session = sessionFactory.openSession()) {
            HibernateCriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<MediaItem> query = builder.createQuery(MediaItem.class);
            Root<MediaItem> root = query.from(MediaItem.class);

            Predicate titlePredicate = builder.like(
                    builder.lower(root.get("title")),
                    "%" + searchTerm.toLowerCase() + "%"
            );

            if (mediaType != null && !mediaType.equalsIgnoreCase("any")) {
                String dbType;
                switch (mediaType.toLowerCase()) {
                    case "movie": dbType = "movie"; break;
                    case "tv": dbType = "tv_show"; break;
                    default: dbType = mediaType;
                }
                Predicate typePredicate = builder.equal(
                        builder.lower(root.get("mediaType")),
                        dbType.toLowerCase()
                );
                query.where(builder.and(titlePredicate, typePredicate));
            } else {
                query.where(titlePredicate);
            }

            return session.createQuery(query).getResultList();
        }
    }
}