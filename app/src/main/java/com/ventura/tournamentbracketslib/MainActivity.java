package com.ventura.tournamentbracketslib;

import android.os.Bundle;

import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.widget.ViewPager2;

public class MainActivity extends FragmentActivity {

//    private ViewPager2 viewPager;
//    private FragmentStateAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .setReorderingAllowed(true)
                    .add(R.id.fragment_container_view, ExampleFragment.class, null)
                    .commit();
        }
//        initViews();
//        initialiseBracketsFragment();
    }
//
//    private void initViews() {
//        viewPager = findViewById(R.id.home_view_pager);
//        adapter = new FragmentStateAdapter(this);
//        viewPager.setAdapter(adapter);
//    }

//    private void initialiseBracketsFragment() {
//        BracketsView bracketsView = findViewById(R.id.bracket_view);
//        CompetitorData brazilSemiFinal = new CompetitorData("Brazil", "3");
//        CompetitorData englandSemiFinal = new CompetitorData("England", "1");
//        CompetitorData argentinaSemiFinal = new CompetitorData("Argentina", "3");
//        CompetitorData russiaSemiFinal = new CompetitorData("Russia", "2");
//        CompetitorData brazilFinal = new CompetitorData("Brazil", "4");
//        CompetitorData argentinaFinal = new CompetitorData("Argentina", "2");
//
//        MatchData match1SemiFinal = new MatchData(brazilSemiFinal, englandSemiFinal);
//        MatchData match2SemiFinal = new MatchData(argentinaSemiFinal, russiaSemiFinal);
//        MatchData match3Final = new MatchData(brazilFinal, argentinaFinal);
//
//        ColomnData semiFinalColomn = new ColomnData(Arrays.asList(match1SemiFinal, match2SemiFinal));
//        ColomnData finalColomn = new ColomnData(Arrays.asList(match3Final));
//
//        bracketsView.setBracketsData(Arrays.asList(semiFinalColomn, finalColomn));

//    }
}