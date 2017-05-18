package com.example.shows;


public class EpisodeWithTitle {
    private Long id;
    private Integer seasonNumber;
    private Integer episodeNumber;
    private String title;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }


    public Long getId() {
        return id;
    }


    public void setId(Long id) {
        this.id = id;
    }


    public Integer getSeasonNumber() {
        return seasonNumber;
    }

    public void setSeasonNumber(Integer seasonNumber) {
        this.seasonNumber = seasonNumber;
    }


    public Integer getEpisodeNumber() {
        return episodeNumber;
    }

    public void setEpisodeNumber(Integer episodeNumber) {
        this.episodeNumber = episodeNumber;
    }

    public void setEpisode(Episode episode){
        setId(episode.getId());
        setEpisodeNumber(episode.getEpisodeNumber());
        setSeasonNumber(episode.getSeasonNumber());
    }
}
