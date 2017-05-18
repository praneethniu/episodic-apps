package com.example.viewings;

import java.sql.Timestamp;

/**
 * Created by trainer5 on 5/18/17.
 */
public class ViewingRequest {
    private Long episodeId;
    private Timestamp updatedAt;
    private Integer timecode;

    public Long getEpisodeId() {
        return episodeId;
    }

    public void setEpisodeId(Long episodeId) {
        this.episodeId = episodeId;
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
