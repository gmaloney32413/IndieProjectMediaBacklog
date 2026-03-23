package edu.matc.entjava.entity;

import jakarta.persistence.*;

/**
 * The type Videogame entity.
 */
@Entity
@Table(name = "videogames")
@DiscriminatorValue("videogame") // matches the media_type value in media_items
@PrimaryKeyJoinColumn(name = "id") // links to MediaItem's id
public class Videogame extends MediaItem {

    @Column(name = "platform")
    private String platform;

    @Column(name = "developer")
    private String developer;

    @Column(name = "publisher")
    private String publisher;

    @Column(name = "esrb_rating")
    private String esrbRating;

    /**
     * Default constructor.
     */
    public Videogame() {}

    /**
     * Full constructor.
     *
     * @param tmdbId      the tmdb id
     * @param title       the title
     * @param overview    the overview
     * @param releaseDate the release date
     * @param posterUrl   the poster url
     * @param platform    the platform
     * @param developer   the developer
     * @param publisher   the publisher
     * @param esrbRating  the esrb rating
     */
    public Videogame(Long tmdbId, String title, String overview, java.util.Date releaseDate, String posterUrl,
                     String platform, String developer, String publisher, String esrbRating) {
        super(tmdbId, title, overview, releaseDate, posterUrl);
        this.platform = platform;
        this.developer = developer;
        this.publisher = publisher;
        this.esrbRating = esrbRating;
    }

    // Getters and setters

    @Override
    public String getMediaType() {
        return "TV Show";
    }

    /**
     * Gets platform.
     *
     * @return the platform
     */
    public String getPlatform() {
        return platform;
    }

    /**
     * Sets platform.
     *
     * @param platform the platform
     */
    public void setPlatform(String platform) {
        this.platform = platform;
    }

    /**
     * Gets developer.
     *
     * @return the developer
     */
    public String getDeveloper() {
        return developer;
    }

    /**
     * Sets developer.
     *
     * @param developer the developer
     */
    public void setDeveloper(String developer) {
        this.developer = developer;
    }

    /**
     * Gets publisher.
     *
     * @return the publisher
     */
    public String getPublisher() {
        return publisher;
    }

    /**
     * Sets publisher.
     *
     * @param publisher the publisher
     */
    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    /**
     * Gets esrb rating.
     *
     * @return the esrb rating
     */
    public String getEsrbRating() {
        return esrbRating;
    }

    /**
     * Sets esrb rating.
     *
     * @param esrbRating the esrb rating
     */
    public void setEsrbRating(String esrbRating) {
        this.esrbRating = esrbRating;
    }
}