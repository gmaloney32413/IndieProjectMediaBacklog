package edu.matc.entjava.entity;

import jakarta.persistence.*;

import java.util.Date;

/**
 * The type Tv show.
 */
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

    /**
     * Instantiates a new Tv show.
     */
    public TvShow() {}

    /**
     * Instantiates a new Tv show.
     *
     * @param tmdbId          the tmdb id
     * @param title           the title
     * @param overview        the overview
     * @param releaseDate     the release date
     * @param posterUrl       the poster url
     * @param numberOfSeasons the number of seasons
     * @param totalEpisodes   the total episodes
     * @param ongoing         the ongoing
     */
    public TvShow(Long tmdbId, String title, String overview, Date releaseDate, String posterUrl, Integer numberOfSeasons, Integer totalEpisodes, Boolean ongoing) {
        super(tmdbId, title, overview, releaseDate, posterUrl);
        this.numberOfSeasons = numberOfSeasons;
        this.totalEpisodes = totalEpisodes;
        this.ongoing = ongoing;
    }

    @Override
    public String getMediaType() {
        return "tv_show"; // must match @DiscriminatorValue
    }


    /**
     * Sets number of seasons.
     *
     * @param numberOfSeasons the number of seasons
     */
    public void setNumberOfSeasons(Integer numberOfSeasons) {
        this.numberOfSeasons = numberOfSeasons;
    }

    /**
     * Gets number of seasons.
     *
     * @return the number of seasons
     */
    public Integer getNumberOfSeasons() {
        return numberOfSeasons;
    }

    /**
     * Sets total episodes.
     *
     * @param totalEpisodes the total episodes
     */
    public void setTotalEpisodes(Integer totalEpisodes) {
        this.totalEpisodes = totalEpisodes;
    }

    /**
     * Gets total episodes.
     *
     * @return the total episodes
     */
    public Integer getTotalEpisodes() {
        return totalEpisodes;
    }

    /**
     * Sets ongoing.
     *
     * @param ongoing the ongoing
     */
    public void setOngoing(Boolean ongoing) {
        this.ongoing = ongoing;
    }

    /**
     * Gets ongoing.
     *
     * @return the ongoing
     */
    public Boolean getOngoing() {
        return ongoing;
    }

}
