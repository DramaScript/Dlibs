package com.dramascript.dlibs.view.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.TextView;

import com.dramascript.dlibrary.base.DInject;
import com.dramascript.dlibs.R;
import com.dramascript.dlibs.imp.ImpBaseFragment;

import butterknife.BindView;

@DInject(
        contentViewId = R.layout.fg_mode_tow
)
public class NormalStatebarFragment extends ImpBaseFragment {

    @BindView(R.id.fake_status_bar)
    View mFakeStatusBar;
    @BindView(R.id.tv_title)
    TextView mTvTitle;

    public static Fragment newInstance(int type) {
        Bundle args = new Bundle();
        NormalStatebarFragment fragment = new NormalStatebarFragment();
        args.putInt("type", type);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected void init(View view) {
        super.init(view);
        int type = getArguments().getInt("type");
        if (type == 0) {
            mTvTitle.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
            mFakeStatusBar.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
        } else {
            mTvTitle.setBackgroundColor(getResources().getColor(R.color.white));
            mFakeStatusBar.setBackgroundColor(getResources().getColor(R.color.white));
        }
    }

}
