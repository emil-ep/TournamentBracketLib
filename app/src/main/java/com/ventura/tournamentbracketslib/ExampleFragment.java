package com.ventura.tournamentbracketslib;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.ventura.bracketslib.BracketsView;
import com.ventura.bracketslib.model.ColomnData;
import com.ventura.bracketslib.model.CompetitorData;
import com.ventura.bracketslib.model.MatchData;

import java.util.Arrays;

public class ExampleFragment extends Fragment {

    public ExampleFragment() {
        super(R.layout.example_fragment);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        BracketsView bracketsView = view.findViewById(R.id.bracket_view);

        CompetitorData brazilSemiFinal = new CompetitorData("Brazil", "3");
        CompetitorData englandSemiFinal = new CompetitorData("England", "1");
        CompetitorData argentinaSemiFinal = new CompetitorData("Argentina", "3");
        CompetitorData russiaSemiFinal = new CompetitorData("Russia", "2");
        CompetitorData brazilFinal = new CompetitorData("Brazil", "4");
        CompetitorData argentinaFinal = new CompetitorData("Argentina", "2");

        MatchData match1SemiFinal = new MatchData(brazilSemiFinal, englandSemiFinal);
        MatchData match2SemiFinal = new MatchData(argentinaSemiFinal, russiaSemiFinal);
        MatchData match3Final = new MatchData(brazilFinal, argentinaFinal);

        ColomnData semiFinalColomn = new ColomnData(Arrays.asList(match1SemiFinal, match2SemiFinal));
        ColomnData finalColomn = new ColomnData(Arrays.asList(match3Final));

        bracketsView.setBracketsData(Arrays.asList(semiFinalColomn, finalColomn));

    }
}
