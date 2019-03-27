package com.dramascript.dlibs.widget;

import android.content.Context;
import android.util.AttributeSet;

import com.lcodecore.tkrefreshlayout.TwinklingRefreshLayout;
import com.lcodecore.tkrefreshlayout.footer.LoadingView;
import com.lcodecore.tkrefreshlayout.header.progresslayout.ProgressLayout;

public class ComRefashLoadLayout extends TwinklingRefreshLayout {
    public ComRefashLoadLayout(Context context) {
        super(context);
        initStyle(context);
    }

    public ComRefashLoadLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        initStyle(context);
    }

    public ComRefashLoadLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initStyle(context);
    }

    private void initStyle(Context context){
        ProgressLayout headerView = new ProgressLayout(context);
        headerView.setColorSchemeResources(android.R.color.holo_blue_light,
                android.R.color.holo_red_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_green_light);
        setHeaderView(headerView);
        setOverScrollRefreshShow(false);
        setFloatRefresh(true);
        LoadingView loadingView = new LoadingView(context);
        setBottomView(loadingView);
    }
}
