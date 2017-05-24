package com.example.episodicevents;


import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;

public class ProgressEvent extends Event{
    private final Data data;

    @JsonCreator
    public ProgressEvent(
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