package com.dramascript.dlibs.presenter.contract;

import com.dramascript.dlibrary.base.BasePresenter;
import com.dramascript.dlibrary.base.BaseView;
import com.dramascript.dlibs.model.bean.TestBean;

import java.util.List;
import java.util.Map;

public interface HttpContract {

    interface View extends BaseView{
        void refashSuccess(List<TestBean> result);
        void loadSuccess(List<TestBean> result);
        void error(String msg);
    }

    interface Presenter extends BasePresenter<View>{
        void getList(int page);
    }

}
