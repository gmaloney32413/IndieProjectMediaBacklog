package edu.matc.entjava.persistence;

import edu.matc.entjava.entity.BacklogEntry;
import edu.matc.entjava.entity.BacklogStatus;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.criteria.HibernateCriteriaBuilder;

import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;

import java.util.List;

public class BacklogEntryDao {

    private final Logger logger = LogManager.getLogger(this.getClass());
    private final SessionFactory sessionFactory = SessionFactoryProvider.getSessionFactory();

    /**
     * Get BacklogEntry by id
     */
    public BacklogEntry getById(Long id) {
        Session session = sessionFactory.openSession();
        BacklogEntry entry = session.get(BacklogEntry.class, id);
        session.close();
        return entry;
    }

    /**
     * Insert a new BacklogEntry
     */
    public Long insert(BacklogEntry entry) {
        Long id = null;
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.persist(entry);
        transaction.commit();
        id = entry.getId();
        session.close();
        return id;
    }

    /**
     * Update an existing BacklogEntry
     */
    public void update(BacklogEntry entry) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.merge(entry);
        transaction.commit();
        session.close();
    }

    /**
     * Delete a BacklogEntry
     */
    public void delete(BacklogEntry entry) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.remove(entry);
        transaction.commit();
        session.close();
    }

    /**
     * Return a list of all BacklogEntries
     */
    public List<BacklogEntry> getAll() {
        Session session = sessionFactory.openSession();

        HibernateCriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<BacklogEntry> query = builder.createQuery(BacklogEntry.class);
        Root<BacklogEntry> root = query.from(BacklogEntry.class);
        query.select(root);
        List<BacklogEntry> entries = session.createQuery(query).getResultList();

        logger.debug("The list of backlog entries: {}", entries);
        session.close();
        return entries;
    }

    /**
     * Return all BacklogEntries for a specific user
     */
    public List<BacklogEntry> getByUserId(Long userId) {
        Session session = sessionFactory.openSession();
        List<BacklogEntry> entries = session.createQuery(
                        "FROM BacklogEntry b WHERE b.user.id = :userId",
                        BacklogEntry.class)
                .setParameter("userId", userId)
                .getResultList();
        session.close();
        return entries;
    }

    /**
     * Count BacklogEntries by status for a specific user
     */
    public Long countByStatusForUser(BacklogStatus status, Long userId) {
        Session session = sessionFactory.openSession();
        Long count = session.createQuery(
                        "SELECT COUNT(b) FROM BacklogEntry b WHERE b.status = :status AND b.user.id = :userId",
                        Long.class)
                .setParameter("status", status)
                .setParameter("userId", userId)
                .uniqueResult();
        session.close();
        return count;
    }
}