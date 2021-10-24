package com.voblox.rangev1.drapdropTask;

import android.content.Context;

import com.voblox.rangev1.BaseInterFace.BasePresenter;
import com.voblox.rangev1.BaseInterFace.BaseView;

public interface DrapDropContract {
    public interface DrapDropView extends BaseView{
//        Context getViewContext();
    }

    public interface DrapDropPresenter extends BasePresenter{
        int sendCmd(int action,int port, int module, int duration, int value,
                    int dirMove, int additionModule, String [] ringLedColo);
    }
}
