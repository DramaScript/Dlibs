package com.dramascript.dlibs.view.activity;

import android.view.View;
import android.widget.ListView;

import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.NetworkUtils;
import com.blankj.utilcode.util.TimeUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.dramascript.dlibrary.base.DInject;
import com.dramascript.dlibrary.base.adapter.listadapter.CommonAdapter;
import com.dramascript.dlibrary.base.adapter.listadapter.ViewHolder;
import com.dramascript.dlibrary.utils.StringCheckUtils;
import com.dramascript.dlibrary.widget.LoadDataLayout;
import com.dramascript.dlibs.R;
import com.dramascript.dlibs.imp.ImpMvpActivity;
import com.dramascript.dlibs.model.bean.TestBean;
import com.dramascript.dlibs.presenter.HttpPresenter;
import com.dramascript.dlibs.presenter.contract.HttpContract;
import com.dramascript.dlibs.widget.ComRefashLoadLayout;
import com.lcodecore.tkrefreshlayout.RefreshListenerAdapter;
import com.lcodecore.tkrefreshlayout.TwinklingRefreshLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

@DInject(
        contentViewId = R.layout.ac_http
)
public class HttpRxActivity extends ImpMvpActivity<HttpPresenter> implements HttpContract.View {

    @BindView(R.id.lv)
    ListView lv;
    @BindView(R.id.refreshLayout)
    ComRefashLoadLayout refreshLayout;
    @BindView(R.id.load_data)
    LoadDataLayout loadData;
    private int page = 1;
    private List<TestBean> lists = new ArrayList<>();
    private CommonAdapter<TestBean> adapter;

    @Override
    protected void init() {
        super.init();
        adapter = new CommonAdapter<TestBean>(this, R.layout.item_http, lists) {

            @Override
            protected void convert(ViewHolder viewHolder, TestBean item, int position) {
                viewHolder.setText(R.id.tv, TimeUtils.millis2String(item.getCreatetime()));
            }
        };
        lv.setAdapter(adapter);
        initRefresh();
        loadData.setOnReloadListener(new LoadDataLayout.OnReloadListener() {
            @Override
            public void onReload(View v, int status) {
                refreshLayout.startRefresh();
            }
        });

    }

    private void initRefresh() {
        loadData.setEnabled(false);// 先禁止loaddata的下拉刷新
        loadData.setStatus(LoadDataLayout.SUCCESS);
        refreshLayout.setOnRefreshListener(new RefreshListenerAdapter() {
            @Override
            public void onRefresh(TwinklingRefreshLayout refreshLayout) {
                if (NetworkUtils.isConnected()) {
                    page = 1;
                    mPresenter.getList(page);
                }else {
                    refreshLayout.finishRefreshing();
                    loadData.setStatus(LoadDataLayout.NO_NETWORK);
                }
            }

            @Override
            public void onLoadMore(TwinklingRefreshLayout refreshLayout) {
                if (NetworkUtils.isConnected()) {
                    page++;
                    mPresenter.getList(page);
                } else {
                    refreshLayout.finishLoadmore();
                    ToastUtils.showShort("网络异常");
                }
            }
        });
        refreshLayout.startRefresh();
    }

    @Override
    protected void initToolbar(String title, boolean isWhite) {
        super.initToolbar("网络请求示例", true);
    }

    @Override
    protected HttpPresenter initPresenter() {
        return new HttpPresenter();
    }

    @Override
    public void refashSuccess(List<TestBean> result) {
        refreshLayout.finishRefreshing();
        lists.clear();
        lists.addAll(result);
        if (lists.size()==0){
            loadData.setStatus(LoadDataLayout.EMPTY);
        }else {
            loadData.setStatus(LoadDataLayout.SUCCESS);
            adapter.notifyDataSetChanged();
        }
    }

    @Override
    public void loadSuccess(List<TestBean> result) {
        lists.addAll(result);
        refreshLayout.finishLoadmore();
        adapter.notifyDataSetChanged();
    }

    @Override
    public void error(String msg) {
        if (page == 1) {
            refreshLayout.finishRefreshing();
            loadData.setStatus(LoadDataLayout.ERROR);
        } else {
            refreshLayout.finishLoadmore();
            ToastUtils.showShort(msg);
        }
    }
}
