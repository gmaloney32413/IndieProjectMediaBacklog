package edu.matc.entjava.entity;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import org.hibernate.annotations.GenericGenerator;

import java.util.Date;

public abstract class  MediaItem {
    String title;

    @Column()
    String description;

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO, generator="native")
    @GenericGenerator(name = "native",strategy = "native")
    String id;

    String mediaType;

    Date releaseDate;

    String posterUrl;

    public MediaItem() {}

    public MediaItem(String title, String description, String mediaType, Date releaseDate, String posterUrl) {
        this.title = title;
        this.description = description;
        this.mediaType = mediaType;
        this.releaseDate = releaseDate;
        this.posterUrl = posterUrl;
    }

    /**
     *
     * @return
     */
    public String getTitle() {
        return title;
    }

    /**
     *
     * @param title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    /**
     *
     * @return
     */
    public String getMediaType() {
        return mediaType;
    }

    /**
     *
     * @param mediaType
     */
    public void setMediaType(String mediaType) {
        this.mediaType = mediaType;
    }

    public Date getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getPosterUrl() {
        return posterUrl;
    }

    public void setPosterUrl(String posterUrl) {
        this.posterUrl = posterUrl;
    }
}