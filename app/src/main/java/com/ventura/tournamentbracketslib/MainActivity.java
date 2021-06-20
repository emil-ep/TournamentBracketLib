package com.ventura.tournamentbracketslib;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.ventura.bracketslib.BracketsView;
import com.ventura.bracketslib.fragment.BracketsFragment;
import com.ventura.bracketslib.model.ColomnData;
import com.ventura.bracketslib.model.CompetitorData;
import com.ventura.bracketslib.model.MatchData;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initialiseBracketsFragment();
    }

    private void initialiseBracketsFragment() {
        BracketsView bracketsView = new BracketsView(this);

        ArrayList<ColomnData> sectionList = new ArrayList<>();
        ArrayList<MatchData> semiFinal = new ArrayList<>();
        ArrayList<MatchData> finals = new ArrayList<>();
        ArrayList<MatchData> winner = new ArrayList<>();
        CompetitorData manchesterUnitedFc = new CompetitorData("Manchester United Fc", "2");
        CompetitorData arsenal = new CompetitorData("Arsenal", "1");
        CompetitorData chelsea = new CompetitorData("Chelsea", "2");
        CompetitorData tottenham = new CompetitorData("Tottenham", "1");
        CompetitorData manchesterFc = new CompetitorData("Manchester FC", "2");
        CompetitorData liverpool = new CompetitorData("Liverpool", "4");
        CompetitorData westHam = new CompetitorData("West ham ", "2");
        CompetitorData bayernMunich = new CompetitorData("Bayern munich", "1");
        MatchData matchData1 = new MatchData(manchesterUnitedFc, arsenal);
        MatchData matchData2 = new MatchData(chelsea, tottenham);
        MatchData matchData3 = new MatchData(manchesterFc, liverpool);
        MatchData matchData4 = new MatchData(westHam, bayernMunich);
        semiFinal.add(matchData1);
        semiFinal.add(matchData2);
        semiFinal.add(matchData3);
        semiFinal.add(matchData4);
        ColomnData colomnData1 = new ColomnData(semiFinal);
        sectionList.add(colomnData1);
        CompetitorData manchesterUnitedFcFinal = new CompetitorData("Manchester United Fc", "2");
        CompetitorData chelseaFinal = new CompetitorData("Chelsea", "4");
        CompetitorData liverpoolFinal = new CompetitorData("Liverpool", "2");
        CompetitorData westhamFinal = new CompetitorData("westham", "1");
        MatchData matchData5 = new MatchData(manchesterUnitedFcFinal, chelseaFinal);
        MatchData matchData6 = new MatchData(liverpoolFinal, westhamFinal);
        finals.add(matchData5);
        finals.add(matchData6);
        ColomnData colomnData2 = new ColomnData(finals);
        sectionList.add(colomnData2);
        CompetitorData competitorThirteen = new CompetitorData("Chelsea", "2");
        CompetitorData competitorForteen = new CompetitorData("Liverpool", "1");
        MatchData matchData7 = new MatchData(competitorThirteen, competitorForteen);
        winner.add(matchData7);
        ColomnData colomnData3 = new ColomnData(winner);
        sectionList.add(colomnData3);

        bracketsView.setBracketsData(sectionList);
    }
}