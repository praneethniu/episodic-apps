package com.example.viewings;

import com.example.shows.EpisodeWithTitle;
import com.example.shows.Show;

import java.sql.Timestamp;

/**
 * Created by trainer5 on 5/18/17.
 */
public class ViewingResponse {
    private Show show;
    private EpisodeWithTitle episode;
    private Timestamp updatedAt;
    private Integer timecode;

    public Show getShow() {
        return show;
    }

    public void setShow(Show show) {
        this.show = show;
    }

    public EpisodeWithTitle getEpisode() {
        return episode;
    }

    public void setEpisode(EpisodeWithTitle episode) {
        this.episode = episode;
    }

    public Timestamp getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Timestamp updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Integer getTimecode() {
        return timecode;
    }

    public void setTimecode(Integer timecode) {
        this.timecode = timecode;
    }
}
