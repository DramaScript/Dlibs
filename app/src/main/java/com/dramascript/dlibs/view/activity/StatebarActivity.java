package com.dramascript.dlibs.view.activity;

import com.dramascript.dlibrary.base.DInject;
import com.dramascript.dlibs.R;
import com.dramascript.dlibs.imp.ImpBaseActivity;

@DInject(
        contentViewId = R.layout.ac_statebar
)
public class StatebarActivity extends ImpBaseActivity {

    @Override
    protected void initToolbar(String title, boolean isWhite) {
        super.initToolbar("我是白色bar", true);
    }
}
