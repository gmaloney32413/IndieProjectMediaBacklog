package edu.matc.entjava.entity;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import org.hibernate.annotations.GenericGenerator;

import java.util.Date;

/**
 * The type Media item.
 */
public abstract class  MediaItem {
    /**
     * The Id.
     */
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO, generator="native")
    @GenericGenerator(name = "native",strategy = "native")
    String id;


     // The Title.

    String title;

     //The Description.
    @Column()
    String overview;


     // The Media type.
    String mediaType;

    //The Release date.
    Date releaseDate;


     // The Poster url.
    String posterUrl;

    /**
     * Instantiates a new Media item.
     */
    public MediaItem() {}

    /**
     * Instantiates a new Media item.
     *
     * @param title       the title
     * @param overview the description
     * @param mediaType   the media type
     * @param releaseDate the release date
     * @param posterUrl   the poster url
     */
    public MediaItem(String title, String overview, String mediaType, Date releaseDate, String posterUrl) {
        this.title = title;
        this.overview = overview;
        this.mediaType = mediaType;
        this.releaseDate = releaseDate;
        this.posterUrl = posterUrl;
    }

    /**
     * Gets id.
     *
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * Sets id.
     *
     * @param id the id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Gets title.
     *
     * @return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * Sets title.
     *
     * @param title the title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Gets description.
     *
     * @return the description
     */
    public String getOverview() {
        return overview;
    }

    /**
     * Sets description.
     *
     * @param overview the description
     */
    public void setOverview(String overview) {
        this.overview = overview;
    }

    /**
     * Gets media type.
     *
     * @return the media type
     */
    public String getMediaType() {
        return mediaType;
    }

    /**
     * Sets media type.
     *
     * @param mediaType the media type
     */
    public void setMediaType(String mediaType) {
        this.mediaType = mediaType;
    }

    /**
     * Gets release date.
     *
     * @return the release date
     */
    public Date getReleaseDate() {
        return releaseDate;
    }

    /**
     * Sets release date.
     *
     * @param releaseDate the release date
     */
    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }

    /**
     * Gets poster url.
     *
     * @return the poster url
     */
    public String getPosterUrl() {
        return posterUrl;
    }

    /**
     * Sets poster url.
     *
     * @param posterUrl the poster url
     */
    public void setPosterUrl(String posterUrl) {
        this.posterUrl = posterUrl;
    }
}