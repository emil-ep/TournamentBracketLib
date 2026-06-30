package com.ventura.bracketslib.adapter;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.ventura.bracketslib.databinding.LayoutCellBracketsBinding;
import com.ventura.bracketslib.fragment.BracketsColomnFragment;
import com.ventura.bracketslib.model.MatchData;
import com.ventura.bracketslib.viewholder.BracketsCellViewHolder;

import java.lang.ref.WeakReference;
import java.util.ArrayList;

/**
 * Created by Emil on 21/10/17.
 */

public class BracketsCellAdapter extends RecyclerView.Adapter<BracketsCellViewHolder> {

    private final BracketsColomnFragment fragment;
    private final Context context;
    private ArrayList<MatchData> list;
    private int bracketColor;
    private int textColor;
    private final Handler mainHandler = new Handler(Looper.getMainLooper());

    public BracketsCellAdapter(BracketsColomnFragment bracketsColomnFragment, Context context,
                               ArrayList<MatchData> list) {

        this.fragment = bracketsColomnFragment;
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public BracketsCellViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutCellBracketsBinding binding = LayoutCellBracketsBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new BracketsCellViewHolder(binding, bracketColor, textColor);
    }

    @Override
    public void onBindViewHolder(@NonNull BracketsCellViewHolder holder, int position) {
        setFields(holder, position);
    }

    private void setFields(final BracketsCellViewHolder viewHolder, final int position) {
        final MatchData match = list.get(position);
        final int height = match.getHeight();
        final WeakReference<BracketsCellViewHolder> weakViewHolder = new WeakReference<>(viewHolder);

        mainHandler.postDelayed(() -> {
            BracketsCellViewHolder holder = weakViewHolder.get();
            if (holder != null && holder.getBindingAdapterPosition() != RecyclerView.NO_POSITION) {
                holder.setAnimation(height);
            }
        }, 100);

        // Match name — visible only when non-empty
        String matchName = match.getMatchName();
        if (!TextUtils.isEmpty(matchName)) {
            viewHolder.getMatchTitle().setText(matchName);
            viewHolder.getMatchTitle().setVisibility(View.VISIBLE);
        } else {
            viewHolder.getMatchTitle().setVisibility(View.GONE);
        }

        // Competitor names and scores
        viewHolder.getTeamOneName().setText(match.getCompetitorOne().getName());
        viewHolder.getTeamTwoName().setText(match.getCompetitorTwo().getName());
        viewHolder.getTeamOneScore().setText(match.getCompetitorOne().getScore());
        viewHolder.getTeamTwoScore().setText(match.getCompetitorTwo().getScore());

        // Competitor images — visible only when a URL is provided
        String imageUrlOne = match.getCompetitorOne().getImageUrl();
        if (!TextUtils.isEmpty(imageUrlOne)) {
            viewHolder.getTeamOneImage().setVisibility(View.VISIBLE);
            Glide.with(context)
                    .load(imageUrlOne)
                    .circleCrop()
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .into(viewHolder.getTeamOneImage());
        } else {
            viewHolder.getTeamOneImage().setVisibility(View.GONE);
        }

        String imageUrlTwo = match.getCompetitorTwo().getImageUrl();
        if (!TextUtils.isEmpty(imageUrlTwo)) {
            viewHolder.getTeamTwoImage().setVisibility(View.VISIBLE);
            Glide.with(context)
                    .load(imageUrlTwo)
                    .circleCrop()
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .into(viewHolder.getTeamTwoImage());
        } else {
            viewHolder.getTeamTwoImage().setVisibility(View.GONE);
        }
    }

    @Override
    public int getItemCount() {
        return this.list != null ? this.list.size() : 0;
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

    @Override
    public void onDetachedFromRecyclerView(@NonNull RecyclerView recyclerView) {
        super.onDetachedFromRecyclerView(recyclerView);
        mainHandler.removeCallbacksAndMessages(null);
    }
}
