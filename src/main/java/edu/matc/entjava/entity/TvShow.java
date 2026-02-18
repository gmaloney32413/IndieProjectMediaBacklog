package edu.matc.entjava.entity;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "tv_shows")
public class TvShow extends MediaItem{

    private Integer numberOfSeasons;
    private Integer totalEpisodes;
    private Boolean ongoing;

    public TvShow() {}

    public TvShow(Long tmdbId, String title, String overview, String mediaType, Date releaseDate, String posterUr, Integer numberOfSeasons, Integer totalEpisodes, Boolean ongoing) {
        this.tmdbId = tmdbId;
        this.title = title;
        this.overview = overview;
        this.mediaType = mediaType;
        this.releaseDate = releaseDate;
        this.posterUrl = posterUr;
        this.numberOfSeasons = numberOfSeasons;
        this.totalEpisodes = totalEpisodes;
        this.ongoing = ongoing;
    }

    public void setNumberOfSeasons(Integer numberOfSeasons) {
        this.numberOfSeasons = numberOfSeasons;
    }

    public Integer getNumberOfSeasons() {
        return numberOfSeasons;
    }

    public void setTotalEpisodes(Integer totalEpisodes) {
        this.totalEpisodes = totalEpisodes;
    }

    public Integer getTotalEpisodes() {
        return totalEpisodes;
    }

    public void setOngoing(Boolean ongoing) {
        this.ongoing = ongoing;
    }

    public Boolean getOngoing() {
        return ongoing;
    }

}
