package com.ventura.tournamentbracketslib;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

public class FragmentStateAdapter extends androidx.viewpager2.adapter.FragmentStateAdapter {


    public FragmentStateAdapter(@NonNull FragmentActivity activity) {
        super(activity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        return new SlideFragment();
    }

    @Override
    public int getItemCount() {
        return 5;
    }
}
