package com.ventura.bracketslib.viewholder;

import android.view.View;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.ventura.bracketslib.R;
import com.ventura.bracketslib.animation.SlideAnimation;


/**
 * Created by Emil on 21/10/17.
 */

public class BracketsCellViewHolder extends RecyclerView.ViewHolder {

    private TextView teamOneName;
    private TextView teamTwoName;
    private TextView teamOneScore;
    private TextView teamTwoScore;
    private Animation animation;
    private RelativeLayout rootLayout;
    private RelativeLayout teamOneLayout;
    private RelativeLayout teamTwoLayout;

    public BracketsCellViewHolder(View itemView, int bracketColor, int textColor) {
        super(itemView);
        teamOneName = (TextView) itemView.findViewById(R.id.team_one_name);
        teamTwoName = (TextView) itemView.findViewById(R.id.team_two_name);
        teamOneScore = (TextView) itemView.findViewById(R.id.team_one_score);
        teamTwoScore = (TextView) itemView.findViewById(R.id.team_two_score);
        rootLayout = (RelativeLayout) itemView.findViewById(R.id.layout_root);
        teamOneLayout = (RelativeLayout) itemView.findViewById(R.id.team_one_layout);
        teamTwoLayout = (RelativeLayout) itemView.findViewById(R.id.team_two_layout);
        setViewColor(bracketColor, textColor);
    }

    private void setViewColor(int bracketColor, int textColor) {
        teamOneLayout.setBackgroundColor(bracketColor);
        teamTwoLayout.setBackgroundColor(bracketColor);
        teamOneName.setTextColor(textColor);
        teamTwoName.setTextColor(textColor);
        teamOneScore.setTextColor(textColor);
        teamTwoScore.setTextColor(textColor);
    }

    public void setAnimation(int height){
        animation = new SlideAnimation(rootLayout, rootLayout.getHeight(),
                height);
        animation.setInterpolator(new LinearInterpolator());
        animation.setDuration(200);
        rootLayout.setAnimation(animation);
        rootLayout.startAnimation(animation);
    }

    public TextView getTeamTwoName() {
        return teamTwoName;
    }

    public TextView getTeamOneScore() {
        return teamOneScore;
    }

    public TextView getTeamTwoScore() {
        return teamTwoScore;
    }

    public TextView getTeamOneName() {
        return teamOneName;
    }

    public RelativeLayout getTeamOneLayout() {
        return teamOneLayout;
    }

    public RelativeLayout getTeamTwoLayout() {
        return teamTwoLayout;
    }
}
