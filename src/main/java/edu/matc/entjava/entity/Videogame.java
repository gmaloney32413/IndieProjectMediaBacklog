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

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }

    public String getDeveloper() {
        return developer;
    }

    public void setDeveloper(String developer) {
        this.developer = developer;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getEsrbRating() {
        return esrbRating;
    }

    public void setEsrbRating(String esrbRating) {
        this.esrbRating = esrbRating;
    }
}