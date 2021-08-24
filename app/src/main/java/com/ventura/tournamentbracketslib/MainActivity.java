package com.ventura.tournamentbracketslib;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;

import android.os.Bundle;

import com.ventura.bracketslib.BracketsView;
import com.ventura.bracketslib.fragment.BracketsFragment;
import com.ventura.bracketslib.model.ColomnData;
import com.ventura.bracketslib.model.CompetitorData;
import com.ventura.bracketslib.model.MatchData;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends FragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initialiseBracketsFragment();
    }

    private void initialiseBracketsFragment() {
//        BracketsView bracketsView = new BracketsView(this);
        BracketsView bracketsView = findViewById(R.id.bracket_view);
//        bracketsView.setBracketBackground();
        CompetitorData brazilSemiFinal = new CompetitorData("Brazil", "3");
        CompetitorData englandSemiFinal = new CompetitorData("England", "1");
        CompetitorData argentinaSemiFinal = new CompetitorData("Argentina", "3");
        CompetitorData russiaSemiFinal = new CompetitorData("Russia", "2");
        CompetitorData brazilFinal = new CompetitorData("Brazil", "4");
        CompetitorData argentinaFinal = new CompetitorData("Argentina", "2");

        MatchData match1SemiFinal = new MatchData(brazilSemiFinal, englandSemiFinal);
        MatchData match2SemiFinal = new MatchData(argentinaSemiFinal, russiaSemiFinal);
        MatchData match3Final = new MatchData(brazilFinal, argentinaFinal);

//        ArrayList<MatchData> semiFinals = new ArrayList<>(Arrays.asList(match1SemiFinal, match2SemiFinal));
//        ArrayList<MatchData> finals = new ArrayList<>(Arrays.asList(match3Final));

        ColomnData semiFinalColomn = new ColomnData(Arrays.asList(match1SemiFinal, match2SemiFinal));
        ColomnData finalColomn = new ColomnData(Arrays.asList(match3Final));

        bracketsView.setBracketsData(Arrays.asList(semiFinalColomn, finalColomn));






//        ArrayList<ColomnData> sectionList = new ArrayList<>();
//        ArrayList<MatchData> semiFinal = new ArrayList<>();
//        ArrayList<MatchData> finals = new ArrayList<>();
//        ArrayList<MatchData> winner = new ArrayList<>();
//        CompetitorData manchesterUnitedFc = new CompetitorData("Manchester United Fc", "2");
//        CompetitorData arsenal = new CompetitorData("Arsenal", "1");
//        CompetitorData chelsea = new CompetitorData("Chelsea", "2");
//        CompetitorData tottenham = new CompetitorData("Tottenham", "1");
//        CompetitorData manchesterFc = new CompetitorData("Manchester FC", "2");
//        CompetitorData liverpool = new CompetitorData("Liverpool", "4");
//        CompetitorData westHam = new CompetitorData("West ham ", "2");
//        CompetitorData bayernMunich = new CompetitorData("Bayern munich", "1");
//        MatchData matchData1 = new MatchData(manchesterUnitedFc, arsenal);
//        MatchData matchData2 = new MatchData(chelsea, tottenham);
//        MatchData matchData3 = new MatchData(manchesterFc, liverpool);
//        MatchData matchData4 = new MatchData(westHam, bayernMunich);
//        semiFinal.add(matchData1);
//        semiFinal.add(matchData2);
//        semiFinal.add(matchData3);
//        semiFinal.add(matchData4);
//        ColomnData colomnData1 = new ColomnData(semiFinal);
//        sectionList.add(colomnData1);
//        CompetitorData manchesterUnitedSemiFinal = new CompetitorData("Manchester United Fc", "2");
//        CompetitorData chelseaSemiFinal = new CompetitorData("Chelsea", "4");
//        CompetitorData liverpoolSemiFinal = new CompetitorData("Liverpool", "2");
//        CompetitorData westhamSemiFinal = new CompetitorData("westham", "1");
//        MatchData matchData5 = new MatchData(manchesterUnitedSemiFinal, chelseaSemiFinal);
//        MatchData matchData6 = new MatchData(liverpoolSemiFinal, westhamSemiFinal);
//        finals.add(matchData5);
//        finals.add(matchData6);
//        ColomnData colomnData2 = new ColomnData(finals);
//        sectionList.add(colomnData2);
//        CompetitorData chelseaFinal = new CompetitorData("Chelsea", "2");
//        CompetitorData liverpoolFinal = new CompetitorData("Liverpool", "1");
//        MatchData matchData7 = new MatchData(chelseaFinal, liverpoolFinal);
//        winner.add(matchData7);
//        ColomnData colomnData3 = new ColomnData(winner);
//        sectionList.add(colomnData3);
//
//        bracketsView.setBracketsData(sectionList);
    }
}