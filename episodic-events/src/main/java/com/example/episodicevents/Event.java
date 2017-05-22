package com.example.episodicevents;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type", visible = true)
@JsonSubTypes({
        @JsonSubTypes.Type(value = PlayEvent.class, name = "play"),
        @JsonSubTypes.Type(value = PauseEvent.class, name = "pause"),
        @JsonSubTypes.Type(value = FastForwardEvent.class, name = "fastForward"),
        @JsonSubTypes.Type(value = RewindEvent.class, name = "rewind"),
        @JsonSubTypes.Type(value = ProgressEvent.class, name = "progress"),
        @JsonSubTypes.Type(value = ScrubEvent.class, name = "scrub")
})
public class Event {
    private Long userId;
    private Long showId;
    private Long episodeId;
    private String createdAt;

    @JsonCreator
    public Event(
            @JsonProperty("userId") Long userId,
            @JsonProperty("showId") Long showId,
            @JsonProperty("episodeId") Long episodeId,
            @JsonProperty("createdAt") String createdAt
            ) {
        this.userId = userId;
        this.showId = showId;
        this.episodeId = episodeId;
        this.createdAt = createdAt;
    }

    public Long getUserId() {
        return userId;
    }

    public Long getShowId() {
        return showId;
    }

    public Long getEpisodeId() {
        return episodeId;
    }

    public String getCreatedAt() {
        return createdAt;
    }
}
