package org.pshishkanov.sherlock.core.process.algorithm.rkrgst.model;

/**
 * Created by pshishkanov on 29/05/15.
 */

public class MatchValue {

    private Integer pattern_position;

    private Integer text_position;

    private Integer length_match;

    public MatchValue(Integer pattern_position, Integer text_position, Integer length_match) {
        this.pattern_position = pattern_position;
        this.text_position = text_position;
        this.length_match = length_match;
    }

    public Integer getPatternPosition() {
        return pattern_position;
    }

    public Integer getTextPosition() {
        return text_position;
    }

    public Integer getLengthMatch() {
        return length_match;
    }
}
