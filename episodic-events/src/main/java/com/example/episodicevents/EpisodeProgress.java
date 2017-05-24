package com.example.episodicevents;

import java.util.Date;

public class EpisodeProgress {
    private Long userId;
    private Long episodeId;
    private Date createdAt;
    private Integer offset;

    public EpisodeProgress(Long userId, Long episodeId, Date createdAt, Integer offset) {
        this.userId = userId;
        this.episodeId = episodeId;
        this.createdAt = createdAt;
        this.offset = offset;
    }

    public Long getUserId() {
        return userId;
    }

    public Long getEpisodeId() {
        return episodeId;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public Integer getOffset() {
        return offset;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public void setEpisodeId(Long episodeId) {
        this.episodeId = episodeId;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public void setOffset(Integer offset) {
        this.offset = offset;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        EpisodeProgress that = (EpisodeProgress) o;

        if (userId != null ? !userId.equals(that.userId) : that.userId != null) return false;
        if (episodeId != null ? !episodeId.equals(that.episodeId) : that.episodeId != null) return false;
        if (createdAt != null ? !createdAt.equals(that.createdAt) : that.createdAt != null) return false;
        return offset != null ? offset.equals(that.offset) : that.offset == null;
    }

    @Override
    public int hashCode() {
        int result = userId != null ? userId.hashCode() : 0;
        result = 31 * result + (episodeId != null ? episodeId.hashCode() : 0);
        result = 31 * result + (createdAt != null ? createdAt.hashCode() : 0);
        result = 31 * result + (offset != null ? offset.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "EpisodeProgress{" +
                "userId=" + userId +
                ", episodeId=" + episodeId +
                ", createdAt='" + createdAt + '\'' +
                ", offset=" + offset +
                '}';
    }
}