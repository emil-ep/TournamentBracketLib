package com.ventura.bracketslib.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.ventura.bracketslib.databinding.FragmentBracktsBinding;
import com.ventura.bracketslib.adapter.BracketsSectionAdapter;
import com.ventura.bracketslib.model.ColomnData;
import com.ventura.bracketslib.utility.BracketsUtility;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by Emil on 21/10/17.
 */

public class BracketsFragment extends Fragment implements ViewPager.OnPageChangeListener {

    private FragmentBracktsBinding binding;
    private BracketsSectionAdapter sectionAdapter;
    private ArrayList<ColomnData> sectionList;
    private int mNextSelectedScreen;
    private int mCurrentPagerState;
    private int backgroundColor, bracketColor, textColor;

    public BracketsFragment() {
    }

    public BracketsFragment(int backgroundColor, int bracketColor, int textColor){
        this.backgroundColor = backgroundColor;
        this.bracketColor = bracketColor;
        this.textColor = textColor;
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentBracktsBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initViews();
        if (sectionList != null && !sectionList.isEmpty()) {
            intialiseViewPagerAdapter();
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    public void setBrackets(List<ColomnData> columnDataList) {
        sectionList = new ArrayList<>();
        sectionList.addAll(columnDataList);
        if (binding != null) {
            intialiseViewPagerAdapter();
        }
    }

    private void intialiseViewPagerAdapter() {
        sectionAdapter = new BracketsSectionAdapter(getChildFragmentManager(),
                this.sectionList, bracketColor, textColor);
        binding.container.setOffscreenPageLimit(10);
        binding.container.setAdapter(sectionAdapter);
        binding.container.setCurrentItem(0);
        binding.container.setPageMargin(-200);
        binding.container.setHorizontalFadingEdgeEnabled(true);
        binding.container.setFadingEdgeLength(50);

        binding.container.addOnPageChangeListener(this);
    }

    private void initViews() {
        if (backgroundColor != 0)
            binding.container.setBackgroundColor(backgroundColor);
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        if (mCurrentPagerState != ViewPager.SCROLL_STATE_SETTLING) {
            BracketsColomnFragment currentFragment = getBracketsFragment(position);
            BracketsColomnFragment nextFragment = getBracketsFragment(position + 1);

            // We are moving to next screen on right side
            if (positionOffset > 0.5) {
                // Closer to next screen than to current
                if (position + 1 != mNextSelectedScreen) {
                    mNextSelectedScreen = position + 1;
                    //update view here
                    if (currentFragment != null && currentFragment.getColomnList() != null && !currentFragment.getColomnList().isEmpty()) {
                        if (currentFragment.getColomnList().get(0).getHeight() != BracketsUtility.dpToPx(131)) {
                            currentFragment.shrinkView(BracketsUtility.dpToPx(131));
                        }
                    }
                    if (nextFragment != null && nextFragment.getColomnList() != null && !nextFragment.getColomnList().isEmpty()) {
                        if (nextFragment.getColomnList().get(0).getHeight() != BracketsUtility.dpToPx(131)) {
                            nextFragment.shrinkView(BracketsUtility.dpToPx(131));
                        }
                    }
                }
            } else {
                // Closer to current screen than to next
                if (position != mNextSelectedScreen) {
                    mNextSelectedScreen = position;
                    //updateViewhere

                    if (nextFragment != null && currentFragment != null) {
                        if (nextFragment.getCurrentBracketSize() == nextFragment.getPreviousBracketSize()) {
                            nextFragment.shrinkView(BracketsUtility.dpToPx(131));
                            currentFragment.shrinkView(BracketsUtility.dpToPx(131));
                        } else {
                            int currentFragmentSize = nextFragment.getCurrentBracketSize();
                            int previousFragmentSize = nextFragment.getPreviousBracketSize();
                            if (currentFragmentSize != previousFragmentSize) {
                                nextFragment.expandHeight(BracketsUtility.dpToPx(262));
                                currentFragment.shrinkView(BracketsUtility.dpToPx(131));
                            }
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
        BracketsColomnFragment selectedFragment = getBracketsFragment(position);
        BracketsColomnFragment nextFragment = getBracketsFragment(position + 1);

        if (selectedFragment != null) {
            selectedFragment.shrinkView(BracketsUtility.dpToPx(131));
        }
        if (nextFragment != null) {
            if (nextFragment.getCurrentBracketSize() != nextFragment.getPreviousBracketSize()) {
                nextFragment.expandHeight(BracketsUtility.dpToPx(262));
            } else {
                nextFragment.shrinkView(BracketsUtility.dpToPx(131));
            }
        }
        mNextSelectedScreen = position;
    }

    @Override
    public void onPageScrollStateChanged(int state) {
        mCurrentPagerState = state;
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
