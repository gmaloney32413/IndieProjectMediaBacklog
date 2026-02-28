package edu.matc.entjava.entity;

import jakarta.persistence.*;

import java.util.Date;

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

    public Movie() {}


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

    public Integer getRuntime() {
        return runtime;
    }

    public void setRuntime(Integer runtime) {
        this.runtime = runtime;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }
}
