package com.ventura.bracketslib;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.ventura.bracketslib.fragment.BracketsFragment;
import com.ventura.bracketslib.model.ColomnData;

import java.util.List;

public class BracketsView extends FrameLayout {

    private View view;
    private Context mContext;
    private BracketsFragment bracketFragment;
    private AppCompatActivity mActivity;

    public BracketsView(AppCompatActivity activity) {
        super(activity);
        this.mContext = activity;
        this.mActivity = activity;
        init(this.mContext);
    }

    public BracketsView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mContext = context;
        this.mActivity = (AppCompatActivity) this.mContext;
        init(context);
    }


    public BracketsView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mContext = context;
        this.mActivity = (AppCompatActivity) this.mContext;
        init(context);
    }

    private void init(Context context) {
        this.view = this;
        inflate(mContext, R.layout.layout_bracket_view, this);
        initialiseBracketsFragment();
    }

    private void initialiseBracketsFragment() {
        bracketFragment = new BracketsFragment();
        FragmentManager manager = this.mActivity.getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.container, bracketFragment, "brackets_home_fragment");
        transaction.commit();
        manager.executePendingTransactions();
    }

    public void setBracketsData(List<ColomnData> colomnList){
        bracketFragment.setBrackets(colomnList);
    }
}
