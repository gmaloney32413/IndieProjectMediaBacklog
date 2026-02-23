package edu.matc.entjava.entity;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "movies")
public class Movie extends MediaItem{
    private Integer runtime;
    private String director;
    private String rating;

    public Movie() {}


    public Movie(Long tmdbId, String title, String overview, String mediaType, Date releaseDate, String posterUrl, Integer runtime, String director, String rating) {
        super(tmdbId, title, overview, mediaType, releaseDate, posterUrl);
        this.runtime = runtime;
        this.director = director;
        this.rating = rating;

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
