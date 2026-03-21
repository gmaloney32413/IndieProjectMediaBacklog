package edu.matc.entjava.persistence;

import edu.matc.entjava.entity.BacklogEntry;
import edu.matc.entjava.entity.BacklogStatus;
import edu.matc.entjava.entity.MediaItem;
import edu.matc.entjava.entity.User;

import java.util.List;

public class BacklogEntryDao extends GenericDao<BacklogEntry> {
    public BacklogEntryDao() { super(BacklogEntry.class); }

    public List<BacklogEntry> getByUser(User user) {
        return getByPropertyEqual("user", user);
    }

    public BacklogEntry getByUserAndMedia(User user, MediaItem mediaItem) {
        return getByPropertyEqual("user", user).stream()
                .filter(b -> b.getMediaItem().equals(mediaItem))
                .findFirst()
                .orElse(null);
    }

    public long countByStatusForUser(User user, BacklogStatus status) {
        return getByPropertyEqual("user", user).stream()
                .filter(b -> b.getStatus() == status)
                .count();
    }
}