package edu.matc.entjava.persistence;

import edu.matc.entjava.entity.BacklogEntry;
import edu.matc.entjava.entity.BacklogStatus;
import edu.matc.entjava.entity.MediaItem;
import edu.matc.entjava.entity.User;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.hibernate.Session;
import org.hibernate.query.criteria.HibernateCriteriaBuilder;

import java.util.List;

public class BacklogEntryDao extends GenericDao<BacklogEntry> {

    public BacklogEntryDao() {
        super(BacklogEntry.class);
    }

    public List<BacklogEntry> getByUser(User user) {
        return getByPropertyEqual("user", user);
    }

    public BacklogEntry getByUserAndMedia(User user, MediaItem mediaItem) {

        Session session = SessionFactoryProvider
                .getSessionFactory()
                .openSession();

        HibernateCriteriaBuilder builder = session.getCriteriaBuilder();

        CriteriaQuery<BacklogEntry> query =
                builder.createQuery(BacklogEntry.class);

        Root<BacklogEntry> root = query.from(BacklogEntry.class);

        Predicate userPredicate =
                builder.equal(root.get("user"), user);

        Predicate mediaPredicate =
                builder.equal(root.get("mediaItem"), mediaItem);

        query.select(root)
                .where(builder.and(userPredicate, mediaPredicate));

        BacklogEntry result = session
                .createQuery(query)
                .uniqueResult();

        session.close();

        return result;
    }

    public long countByStatusForUser(User user, BacklogStatus status) {
        return getByPropertyEqual("user", user).stream()
                .filter(b -> b.getStatus() == status)
                .count();
    }
}