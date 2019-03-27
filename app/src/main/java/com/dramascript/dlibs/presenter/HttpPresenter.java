package com.dramascript.dlibs.presenter;

import com.dramascript.dlibrary.base.RxPresenter;
import com.dramascript.dlibrary.http.HttpManager;
import com.dramascript.dlibrary.http.SimpleObserver;
import com.dramascript.dlibs.model.ApiConstant;
import com.dramascript.dlibs.model.bean.TestBean;
import com.dramascript.dlibs.presenter.contract.HttpContract;

import java.util.HashMap;
import java.util.List;

import io.reactivex.disposables.Disposable;

public class HttpPresenter extends RxPresenter<HttpContract.View> implements HttpContract.Presenter {

    @Override
    public void getList(final int page) {
        HashMap<String, String> map = new HashMap<>();
        map.put("page", page + "");
        HttpManager.getInstance()
                .executeGet(ApiConstant.ONSTAOCK_LIST, map)
                .subscribe(new SimpleObserver<TestBean>(null, TestBean[].class) {
                    @Override
                    public void onError(String msg) {
                        mView.error(msg);
                    }

                    @Override
                    public void addDisposable(Disposable d) {
                        HttpPresenter.this.addDisposable(d);
                    }

                    @Override
                    public void onSuccess(String msg, List<TestBean> t) {
                        if (page == 1) {
                            mView.refashSuccess(t);
                        } else {
                            mView.loadSuccess(t);
                        }
                    }
                });
    }

}
