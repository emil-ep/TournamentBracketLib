package com.ventura.bracketslib.fragment;

import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ventura.bracketslib.R;
import com.ventura.bracketslib.adapter.BracketsCellAdapter;
import com.ventura.bracketslib.model.ColomnData;
import com.ventura.bracketslib.model.MatchData;

import java.util.ArrayList;

/**
 * Created by Emil on 21/10/17.
 */

public class BracketsColomnFragment extends Fragment {

    private ColomnData colomnData;
    private int sectionNumber = 0;
    private int previousBracketSize;
    private ArrayList<MatchData> list;
    private RecyclerView bracketsRV;

    private BracketsCellAdapter adapter;
    private int bracketColor;
    private int textColor;

    public BracketsColomnFragment(int bracketColor, int textColor) {
        this.bracketColor = bracketColor;
        this.textColor = textColor;
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_brackets_colomn, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initViews();
        getExtras();
        initAdapter();
    }

    private void initViews() {

        bracketsRV = (RecyclerView) getView().findViewById(R.id.rv_score_board);
    }

    public ArrayList<MatchData> getColomnList() {
        return list;
    }

    private void getExtras() {
        if (getArguments() != null) {
            list = new ArrayList<>();
            colomnData = (ColomnData) getArguments().getSerializable("colomn_data");
            sectionNumber = getArguments().getInt("section_number");
            previousBracketSize = getArguments().getInt("previous_section_size");
            list.addAll(colomnData.getMatches());
            setInitialHeightForList();
        }
    }
    public int getSectionNumber() {
        return sectionNumber;
    }

    private void setInitialHeightForList() {
        for (MatchData data : list){
            if (sectionNumber == 0){
                data.setHeight(dpToPx(131));
            }else if (sectionNumber == 1 && previousBracketSize != list.size()){
                data.setHeight(dpToPx(262));
            }else if (sectionNumber == 1 && previousBracketSize == list.size()) {
                data.setHeight(dpToPx(131));
            } else if (previousBracketSize > list.size()) {
                data.setHeight(dpToPx(262));
            }else if (previousBracketSize == list.size()) {
                data.setHeight(dpToPx(131));
            }
        }

    }

    private int dpToPx(int dp) {
        DisplayMetrics displayMetrics = getContext().getResources().getDisplayMetrics();
        return Math.round(dp * (displayMetrics.xdpi / DisplayMetrics.DENSITY_DEFAULT));
    }

    public void expandHeight(int height) {

        for (MatchData data : list) {
            data.setHeight(height);
        }
        adapter.setList(list);
    }

    public void shrinkView(int height) {
        for (MatchData data : list) {
            data.setHeight(height);
        }
        adapter.setList(list);
    }
    private void initAdapter() {

//        pBar.setVisibility(View.GONE);
         adapter = new BracketsCellAdapter(this, getContext(), list);
         adapter.setBracketColor(bracketColor);
         adapter.setTextColor(textColor);
        if (bracketsRV != null) {
            bracketsRV.setHasFixedSize(true);
            bracketsRV.setNestedScrollingEnabled(false);
            bracketsRV.setAdapter(adapter);
            bracketsRV.smoothScrollToPosition(0);
            final LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
            layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
            bracketsRV.setLayoutManager(layoutManager);
            bracketsRV.setItemAnimator(new DefaultItemAnimator());
        }
    }

    public int getCurrentBracketSize() {
        return list.size();
    }
    public int getPreviousBracketSize() {
        return previousBracketSize;
    }
}
