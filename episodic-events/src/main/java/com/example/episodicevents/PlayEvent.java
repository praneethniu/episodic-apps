package com.example.episodicevents;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class PlayEvent extends Event{
    private final Data data;

    @JsonCreator
    public PlayEvent(
                     @JsonProperty("userId") Long userId,
                     @JsonProperty("showId") Long showId,
                     @JsonProperty("episodeId") Long episodeId,
                     @JsonProperty("createdAt") String createdAt,
                     @JsonProperty("data") Data data) {
        super( userId, showId, episodeId, createdAt);
        this.data = data;
    }


    public Data getData() {
        return data;
    }
}
