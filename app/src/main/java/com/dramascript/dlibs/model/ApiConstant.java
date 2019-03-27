package com.dramascript.dlibs.model;

import com.dramascript.dlibs.model.bean.TestBean;

public class ApiConstant {

    public static final String BASE_URL = "http://183.131.205.41";

    public static final String ROBOT_NEAR_WIN = BASE_URL + "/Robot/GetList";

    public static final String ONSTAOCK_LIST = BASE_URL + "/Bbs";

    public static void setId(TestBean bean){
        bean.setId(101);
    }

}
