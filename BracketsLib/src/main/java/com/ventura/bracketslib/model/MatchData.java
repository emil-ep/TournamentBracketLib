package com.ventura.bracketslib.model;

import java.io.Serializable;

/**
 * Created by Emil on 21/10/17.
 */

public class MatchData implements Serializable{

    private CompetitorData competitorOne;
    private CompetitorData competitorTwo;
    private int height;

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public MatchData(CompetitorData competitorOne, CompetitorData competitorTwo) {
        this.competitorOne = competitorOne;
        this.competitorTwo = competitorTwo;
    }

    public CompetitorData getCompetitorTwo() {
        return competitorTwo;
    }

    public void setCompetitorTwo(CompetitorData competitorTwo) {
        this.competitorTwo = competitorTwo;
    }

    public CompetitorData getCompetitorOne() {

        return competitorOne;
    }

    public void setCompetitorOne(CompetitorData competitorOne) {
        this.competitorOne = competitorOne;
    }
}
