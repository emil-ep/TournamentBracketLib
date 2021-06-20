package com.ventura.bracketslib.adapter;

import android.content.Context;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import com.ventura.bracketslib.R;
import com.ventura.bracketslib.fragment.BracketsColomnFragment;
import com.ventura.bracketslib.model.MatchData;
import com.ventura.bracketslib.viewholder.BracketsCellViewHolder;

import java.util.ArrayList;

/**
 * Created by Emil on 21/10/17.
 */

public class BracketsCellAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    private BracketsColomnFragment fragment;
    private Context context;
    private ArrayList<MatchData> list;
    private boolean handler;
    private int bracketColor;
    private int textColor;

    public BracketsCellAdapter(BracketsColomnFragment bracketsColomnFragment, Context context,
                               ArrayList<MatchData> list) {

        this.fragment = bracketsColomnFragment;
        this.context = context;
        this.list = list;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.layout_cell_brackets, parent, false);
        return new BracketsCellViewHolder(view, bracketColor, textColor);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        BracketsCellViewHolder viewHolder = null;
        if (holder instanceof BracketsCellViewHolder){
            viewHolder = (BracketsCellViewHolder) holder;
            setFields(viewHolder, position);
        }
    }

    private void setFields(final BracketsCellViewHolder viewHolder, final int position) {
        handler = new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                viewHolder.setAnimation(list.get(position).getHeight());
            }
        }, 100);

        viewHolder.getTeamOneName().setText(list.get(position).getCompetitorOne().getName());
        viewHolder.getTeamTwoName().setText(list.get(position).getCompetitorTwo().getName());
        viewHolder.getTeamOneScore().setText(list.get(position).getCompetitorOne().getScore());
        viewHolder.getTeamTwoScore().setText(list.get(position).getCompetitorTwo().getScore());
    }

    @Override
    public int getItemCount() {
        return this.list.size();
    }

    public void setList(ArrayList<MatchData> colomnList) {
        this.list = colomnList;
        notifyDataSetChanged();
    }

    public void setBracketColor(int bracketColor) {
        this.bracketColor = bracketColor;
    }

    public void setTextColor(int textColor) {
        this.textColor = textColor;
    }
}
