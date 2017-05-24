package com.example.episodicevents;


import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;

public class PauseEvent extends Event {
    private Data data;

    @JsonCreator
    public PauseEvent(
            @JsonProperty("userId") Long userId,
            @JsonProperty("showId") Long showId,
            @JsonProperty("episodeId") Long episodeId,
            @JsonProperty("createdAt") Date createdAt,
            @JsonProperty("data") Data data) {
        super(userId, showId, episodeId, createdAt);
        this.data = data;
    }

    public Data getData() {
        return data;
    }
}
