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

    public TvShow(Long tmdbId, String title, String overview, String mediaType, Date releaseDate, String posterUrl, Integer numberOfSeasons, Integer totalEpisodes, Boolean ongoing) {
        super(tmdbId, title, overview, mediaType, releaseDate, posterUrl);
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
