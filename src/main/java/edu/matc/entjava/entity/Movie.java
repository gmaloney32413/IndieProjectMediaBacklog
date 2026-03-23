package edu.matc.entjava.entity;

import jakarta.persistence.*;

import java.util.Date;

/**
 * The type Movie.
 */
@Entity
@Table(name = "movies")
@DiscriminatorValue("movie")
@PrimaryKeyJoinColumn(name = "id")
public class Movie extends MediaItem{

    @Column(name = "runtime")
    private Integer runtime;

    @Column(name = "director")
    private String director;

    @Column(name = "rating")
    private String rating;

    /**
     * Instantiates a new Movie.
     */
    public Movie() {}


    /**
     * Instantiates a new Movie.
     *
     * @param tmdbId      the tmdb id
     * @param title       the title
     * @param overview    the overview
     * @param releaseDate the release date
     * @param posterUrl   the poster url
     * @param runtime     the runtime
     * @param director    the director
     * @param rating      the rating
     */
    public Movie(Long tmdbId, String title, String overview, Date releaseDate, String posterUrl, Integer runtime, String director, String rating) {
        super(tmdbId, title, overview, releaseDate, posterUrl);
        this.runtime = runtime;
        this.director = director;
        this.rating = rating;

    }

    @Override
    public String getMediaType() {
        return "Movie";
    }

    /**
     * Gets runtime.
     *
     * @return the runtime
     */
    public Integer getRuntime() {
        return runtime;
    }

    /**
     * Sets runtime.
     *
     * @param runtime the runtime
     */
    public void setRuntime(Integer runtime) {
        this.runtime = runtime;
    }

    /**
     * Gets director.
     *
     * @return the director
     */
    public String getDirector() {
        return director;
    }

    /**
     * Sets director.
     *
     * @param director the director
     */
    public void setDirector(String director) {
        this.director = director;
    }

    /**
     * Gets rating.
     *
     * @return the rating
     */
    public String getRating() {
        return rating;
    }

    /**
     * Sets rating.
     *
     * @param rating the rating
     */
    public void setRating(String rating) {
        this.rating = rating;
    }


}
