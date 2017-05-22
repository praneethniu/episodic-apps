package com.example.episodicevents;


import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Data {
    private Long offset;

    @JsonCreator
    public Data(
            @JsonProperty("offset") Long offset) {
        this.offset = offset;
    }

    public Long getOffset() {
        return offset;
    }
}
