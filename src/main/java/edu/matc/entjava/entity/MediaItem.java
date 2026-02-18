package edu.matc.entjava.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.GenericGenerator;

import java.util.Date;
import java.util.List;

/**
 * The type Media item.
 */
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "media_items")
public abstract class  MediaItem {
    /**
     * The Id.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long tmdbId;


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
    public MediaItem(Long tmdbId, String title, String overview, String mediaType, Date releaseDate, String posterUrl) {
        this.tmdbId = tmdbId;
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
    public Long getTmdbId() {
        return tmdbId;
    }

    /**
     * Sets id.
     *
     * @param tmdbId the id
     */
    public void setTmdbId(Long tmdbId) {
        this.tmdbId = tmdbId;
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