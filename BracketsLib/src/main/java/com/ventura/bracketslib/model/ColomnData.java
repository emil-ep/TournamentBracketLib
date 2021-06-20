package com.ventura.bracketslib.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Emil on 21/10/17.
 */

public class ColomnData implements Serializable{

    public ColomnData(List<MatchData> matches) {
        this.matches = matches;
    }

    private List<MatchData> matches;

    public void setMatches(ArrayList<MatchData> matches) {
        this.matches = matches;
    }

    public List<MatchData> getMatches() {
        return matches;
    }
}
