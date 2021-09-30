package com.voblox.rangev1.drapdropTask;

import android.content.Context;

import com.voblox.rangev1.BaseInterFace.BasePresenter;
import com.voblox.rangev1.BaseInterFace.BaseView;

public interface DrapDropContract {
    public interface DrapDropView extends BaseView{
//        Context getViewContext();
    }

    public interface DrapDropPresenter extends BasePresenter{
        void sendCmd(int action, int module, int data1, int data2,
                     int data3, int data4, int data5, int data6, int data);
    }
}
