package com.ventura.bracketslib.viewholder;

import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ventura.bracketslib.animation.SlideAnimation;
import com.ventura.bracketslib.databinding.LayoutCellBracketsBinding;

/**
 * Created by Emil on 21/10/17.
 */

public class BracketsCellViewHolder extends RecyclerView.ViewHolder {

    private final LayoutCellBracketsBinding binding;
    private Animation animation;

    public BracketsCellViewHolder(@NonNull LayoutCellBracketsBinding binding, int bracketColor, int textColor) {
        super(binding.getRoot());
        this.binding = binding;
        if (bracketColor != 0 || textColor != 0) {
            setViewColor(bracketColor, textColor);
        }
    }

    private void setViewColor(int bracketColor, int textColor) {
        if (bracketColor != 0) {
            binding.teamOneLayout.setBackgroundColor(bracketColor);
            binding.teamTwoLayout.setBackgroundColor(bracketColor);
        }
        if (textColor != 0) {
            binding.teamOneName.setTextColor(textColor);
            binding.teamTwoName.setTextColor(textColor);
            binding.teamOneScore.setTextColor(textColor);
            binding.teamTwoScore.setTextColor(textColor);
        }
    }

    public void setAnimation(int height) {
        int currentHeight = binding.layoutRoot.getLayoutParams().height;
        if (currentHeight <= 0) {
            currentHeight = binding.layoutRoot.getHeight();
        }
        animation = new SlideAnimation(binding.layoutRoot, currentHeight, height);
        animation.setInterpolator(new LinearInterpolator());
        animation.setDuration(200);
        binding.layoutRoot.startAnimation(animation);
    }

    public TextView getTeamTwoName() {
        return binding.teamTwoName;
    }

    public TextView getTeamOneScore() {
        return binding.teamOneScore;
    }

    public TextView getTeamTwoScore() {
        return binding.teamTwoScore;
    }

    public TextView getTeamOneName() {
        return binding.teamOneName;
    }
}
