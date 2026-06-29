package com.ventura.bracketslib.fragment;

import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.ventura.bracketslib.databinding.FragmentBracketsColomnBinding;
import com.ventura.bracketslib.adapter.BracketsCellAdapter;
import com.ventura.bracketslib.model.ColomnData;
import com.ventura.bracketslib.model.MatchData;
import com.ventura.bracketslib.utility.BracketsUtility;

import java.util.ArrayList;

/**
 * Created by Emil on 21/10/17.
 */

public class BracketsColomnFragment extends Fragment {

    private FragmentBracketsColomnBinding binding;
    private ColomnData colomnData;
    private int sectionNumber = 0;
    private int previousBracketSize;
    private ArrayList<MatchData> list;
    private BracketsCellAdapter adapter;
    private int bracketColor;
    private int textColor;

    public BracketsColomnFragment() {
    }

    public BracketsColomnFragment(int bracketColor, int textColor) {
        this.bracketColor = bracketColor;
        this.textColor = textColor;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getExtras();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentBracketsColomnBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initAdapter();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    public ArrayList<MatchData> getColomnList() {
        return list;
    }

    private void getExtras() {
        if (getArguments() != null) {
            list = new ArrayList<>();
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                colomnData = getArguments().getSerializable("colomn_data", ColomnData.class);
            } else {
                colomnData = (ColomnData) getArguments().getSerializable("colomn_data");
            }
            sectionNumber = getArguments().getInt("section_number");
            previousBracketSize = getArguments().getInt("previous_section_size");
            if (colomnData != null && colomnData.getMatches() != null) {
                list.addAll(colomnData.getMatches());
            }
            setInitialHeightForList();
        }
    }

    public int getSectionNumber() {
        return sectionNumber;
    }

    private void setInitialHeightForList() {
        if (list == null) return;
        for (MatchData data : list) {
            if (sectionNumber == 0) {
                data.setHeight(BracketsUtility.dpToPx(131));
            } else if (sectionNumber == 1 && previousBracketSize != list.size()) {
                data.setHeight(BracketsUtility.dpToPx(262));
            } else if (sectionNumber == 1 && previousBracketSize == list.size()) {
                data.setHeight(BracketsUtility.dpToPx(131));
            } else if (previousBracketSize > list.size()) {
                data.setHeight(BracketsUtility.dpToPx(262));
            } else if (previousBracketSize == list.size()) {
                data.setHeight(BracketsUtility.dpToPx(131));
            }
        }
    }

    public void expandHeight(int height) {
        if (list == null) return;
        for (MatchData data : list) {
            data.setHeight(height);
        }
        if (adapter != null) {
            adapter.setList(list);
        }
    }

    public void shrinkView(int height) {
        if (list == null) return;
        for (MatchData data : list) {
            data.setHeight(height);
        }
        if (adapter != null) {
            adapter.setList(list);
        }
    }

    private void initAdapter() {
        if (binding == null || binding.rvScoreBoard == null) return;
        adapter = new BracketsCellAdapter(this, getContext(), list);
        adapter.setBracketColor(bracketColor);
        adapter.setTextColor(textColor);
        binding.rvScoreBoard.setHasFixedSize(true);
        binding.rvScoreBoard.setNestedScrollingEnabled(false);
        binding.rvScoreBoard.setAdapter(adapter);
        binding.rvScoreBoard.smoothScrollToPosition(0);
        final LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        binding.rvScoreBoard.setLayoutManager(layoutManager);
        binding.rvScoreBoard.setItemAnimator(new DefaultItemAnimator());
    }

    public int getCurrentBracketSize() {
        return list != null ? list.size() : 0;
    }

    public int getPreviousBracketSize() {
        return previousBracketSize;
    }
}
