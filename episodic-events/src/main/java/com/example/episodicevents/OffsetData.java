package com.example.episodicevents;


import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class OffsetData {
    private Long startOffset;
    private Long endOffset;
    private Long speed;

    @JsonCreator
    public OffsetData(@JsonProperty("startOffset") Long startOffset,
                      @JsonProperty("endOffset") Long endOffset,
                      @JsonProperty("speed") Long speed) {
        this.startOffset = startOffset;
        this.endOffset = endOffset;
        this.speed = speed;
    }

    public Long getStartOffset() {
        return startOffset;
    }

    public Long getEndOffset() {
        return endOffset;
    }

    public Long getSpeed() {
        return speed;
    }
}
