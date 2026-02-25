package edu.matc.entjava.entity;

import jakarta.persistence.*;

import java.util.Date;

/**
 * The type Backlog entry.
 */
@Entity
@Table(name = "backlog_entries")
public class BacklogEntry {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "media_id", nullable = false)
    private MediaItem mediaItem;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private BacklogStatus status;

    @Column(length = 2000, name = "notes")
    private String notes;

    @Column(name = "user_rating")
    private Integer userRating;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "date_added")
    private Date dateAdded;

    /**
     * Instantiates a new Backlog entry.
     */
    public BacklogEntry() {}


    // getters and setters


    /**
     * Gets id.
     *
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * Sets id.
     *
     * @param id the id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Gets user.
     *
     * @return the user
     */
    public User getUser() {
        return user;
    }

    /**
     * Sets user.
     *
     * @param user the user
     */
    public void setUser(User user) {
        this.user = user;
    }

    /**
     * Gets media item.
     *
     * @return the media item
     */
    public MediaItem getMediaItem() {
        return mediaItem;
    }

    /**
     * Sets media item.
     *
     * @param mediaItem the media item
     */
    public void setMediaItem(MediaItem mediaItem) {
        this.mediaItem = mediaItem;
    }

    /**
     * Gets status.
     *
     * @return the status
     */
    public BacklogStatus getStatus() {
        return status;
    }

    /**
     * Sets status.
     *
     * @param status the status
     */
    public void setStatus(BacklogStatus status) {
        this.status = status;
    }

    /**
     * Gets notes.
     *
     * @return the notes
     */
    public String getNotes() {
        return notes;
    }

    /**
     * Sets notes.
     *
     * @param notes the notes
     */
    public void setNotes(String notes) {
        this.notes = notes;
    }

    /**
     * Gets user rating.
     *
     * @return the user rating
     */
    public Integer getUserRating() {
        return userRating;
    }

    /**
     * Sets user rating.
     *
     * @param userRating the user rating
     */
    public void setUserRating(Integer userRating) {
        this.userRating = userRating;
    }

    /**
     * Gets date added.
     *
     * @return the date added
     */
    public Date getDateAdded() {
        return dateAdded;
    }

    /**
     * Sets date added.
     *
     * @param dateAdded the date added
     */
    public void setDateAdded(Date dateAdded) {
        this.dateAdded = dateAdded;
    }
}