package com.dramascript.dlibs.view;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.dramascript.dlibrary.base.DInject;
import com.dramascript.dlibrary.base.adapter.listadapter.CommonAdapter;
import com.dramascript.dlibrary.base.adapter.listadapter.ViewHolder;
import com.dramascript.dlibrary.utils.NoMultiItemClickListener;
import com.dramascript.dlibs.R;
import com.dramascript.dlibs.imp.ImpBaseActivity;
import com.dramascript.dlibs.view.activity.HttpRxActivity;
import com.dramascript.dlibs.view.activity.StateInFragmentActivity;
import com.dramascript.dlibs.view.activity.StatebarActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

@DInject(
        contentViewId = R.layout.ac_main
)
public class MainActivity extends ImpBaseActivity {

    @BindView(R.id.lv)
    ListView lv;

    private List<String> lists;

    @Override
    protected void initToolbar(String title, boolean isWhite) {
        super.initToolbar("", isWhite);// title不为null显示toolbar
    }

    @Override
    protected void init() {
        super.init();
        setToolbarColor(getResources().getColor(R.color.colorPrimary));
        setToolbarTitle("首页");
        setToolbarTitleColor(getResources().getColor(R.color.white));
        getToolbar().setNavigationIcon(null);// 隐藏返回按钮
        lists = new ArrayList<>();
        initData();
        lv.setAdapter(new CommonAdapter<String>(this,R.layout.item_test,lists) {
            @Override
            protected void convert(ViewHolder viewHolder, String item, int position) {
                viewHolder.setText(R.id.tv_test,item);
            }
        });
        lv.setOnItemClickListener(new NoMultiItemClickListener() {
            @Override
            public void onNoMultiClick(AdapterView<?> parent, View v, int position, long id) {
                switch (position){
                    case 0:
                        startActivity(StateInFragmentActivity.class);
                        break;
                    case 1:
                        startActivity(StatebarActivity.class);
                        break;
                    case 2:
                        startActivity(HttpRxActivity.class);
                        break;
                }
            }
        });
    }

    private void initData() {
        lists.add("fragment沉浸式");
        lists.add("activity沉浸式");
        lists.add("http请求-MVP");
    }

}
