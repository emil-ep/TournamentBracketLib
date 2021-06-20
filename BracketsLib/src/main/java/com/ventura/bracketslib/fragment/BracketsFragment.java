package com.ventura.bracketslib.fragment;

import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.ventura.bracketslib.R;
import com.ventura.bracketslib.adapter.BracketsSectionAdapter;
import com.ventura.bracketslib.customviews.WrapContentHeightViewPager;
import com.ventura.bracketslib.model.ColomnData;
import com.ventura.bracketslib.model.CompetitorData;
import com.ventura.bracketslib.model.MatchData;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by Emil on 21/10/17.
 */

public class BracketsFragment extends Fragment implements ViewPager.OnPageChangeListener {

    private WrapContentHeightViewPager viewPager;
    private BracketsSectionAdapter sectionAdapter;
    private ArrayList<ColomnData> sectionList;
    private int mNextSelectedScreen;
    private int mCurrentPagerState;
    private int backgroundColor, bracketColor, textColor;
    private WrapContentHeightViewPager wrapContentHeightViewPager;

    public BracketsFragment(int backgroundColor, int bracketColor, int textColor){
        this.backgroundColor = backgroundColor;
        this.bracketColor = bracketColor;
        this.textColor = textColor;
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_brackts, container, false);
        return view;
    }


    @Override
    public void onViewCreated(@NonNull  View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initViews(view);
        intialiseViewPagerAdapter();
    }

    public void setBrackets(List<ColomnData> columnDataList) {
        sectionList = new ArrayList<>();
        sectionList.addAll(columnDataList);
    }

    private void intialiseViewPagerAdapter() {

        sectionAdapter = new BracketsSectionAdapter(getChildFragmentManager(),
                this.sectionList, bracketColor, textColor);
        viewPager.setOffscreenPageLimit(10);
        viewPager.setAdapter(sectionAdapter);
        viewPager.setCurrentItem(0);
        viewPager.setPageMargin(-200);
        viewPager.setHorizontalFadingEdgeEnabled(true);
        viewPager.setFadingEdgeLength(50);

        viewPager.addOnPageChangeListener(this);
        sectionAdapter.notifyDataSetChanged();
    }

    private void initViews(View view) {

        viewPager = view.findViewById(R.id.container);
        viewPager.setBackgroundColor(backgroundColor);
    }

    public int dpToPx(int dp) {
        DisplayMetrics displayMetrics = getContext().getResources().getDisplayMetrics();
        return Math.round(dp * (displayMetrics.xdpi / DisplayMetrics.DENSITY_DEFAULT));
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        if (mCurrentPagerState != ViewPager.SCROLL_STATE_SETTLING) {
            // We are moving to next screen on right side
            if (positionOffset > 0.5) {
                // Closer to next screen than to current
                if (position + 1 != mNextSelectedScreen) {
                    mNextSelectedScreen = position + 1;
                    //update view here
                    if (getBracketsFragment(position).getColomnList().get(0).getHeight()
                            != dpToPx(131))
                        getBracketsFragment(position).shrinkView(dpToPx(131));
                    if (getBracketsFragment(position + 1).getColomnList().get(0).getHeight()
                            != dpToPx(131))
                        getBracketsFragment(position + 1).shrinkView(dpToPx(131));
                }
            } else {
                // Closer to current screen than to next
                if (position != mNextSelectedScreen) {
                    mNextSelectedScreen = position;
                    //updateViewhere

                    if (getBracketsFragment(position + 1).getCurrentBracketSize() ==
                            getBracketsFragment(position + 1).getPreviousBracketSize()) {
                        getBracketsFragment(position + 1).shrinkView(dpToPx(131));
                        getBracketsFragment(position).shrinkView(dpToPx(131));
                    } else {
                        int currentFragmentSize = getBracketsFragment(position + 1).getCurrentBracketSize();
                        int previousFragmentSize = getBracketsFragment(position + 1).getPreviousBracketSize();
                        if (currentFragmentSize != previousFragmentSize) {
                            getBracketsFragment(position + 1).expandHeight(dpToPx(262));
                            getBracketsFragment(position).shrinkView(dpToPx(131));
                        }
                    }
                }
            }
        } else {
            // We are moving to next screen left side
            if (positionOffset > 0.5) {
                // Closer to current screen than to next
                if (position + 1 != mNextSelectedScreen) {
                    mNextSelectedScreen = position + 1;
                    //update view for screen

                }
            } else {
                // Closer to next screen than to current
                if (position != mNextSelectedScreen) {
                    mNextSelectedScreen = position;
                    //updateviewfor screem
                }
            }
        }
    }

    @Override
    public void onPageSelected(int position) {

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    public BracketsColomnFragment getBracketsFragment(int position) {
        BracketsColomnFragment bracktsFrgmnt = null;
        if (getChildFragmentManager() != null) {
            List<Fragment> fragments = getChildFragmentManager().getFragments();
            if (fragments != null) {
                for (Fragment fragment : fragments) {
                    if (fragment instanceof BracketsColomnFragment) {
                        bracktsFrgmnt = (BracketsColomnFragment) fragment;
                        if (bracktsFrgmnt.getSectionNumber() == position)
                            break;
                    }
                }
            }
        }
        return bracktsFrgmnt;
    }
}
