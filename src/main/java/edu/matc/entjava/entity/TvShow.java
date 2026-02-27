package edu.matc.entjava.entity;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "tv_shows")
@DiscriminatorValue("tv_show")
@PrimaryKeyJoinColumn(name = "id")
public class TvShow extends MediaItem{

    @Column(name = "number_of_seasons")
    private Integer numberOfSeasons;

    @Column(name = "total_episodes")
    private Integer totalEpisodes;

    @Column(name = "ongoing")
    private Boolean ongoing;

    public TvShow() {}

    public TvShow(Long tmdbId, String title, String overview, Date releaseDate, String posterUrl, Integer numberOfSeasons, Integer totalEpisodes, Boolean ongoing) {
        super(tmdbId, title, overview, releaseDate, posterUrl);
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
