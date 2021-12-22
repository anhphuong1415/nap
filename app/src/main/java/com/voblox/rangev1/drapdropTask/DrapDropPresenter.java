package com.voblox.rangev1.drapdropTask;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

import com.voblox.rangev1.BaseInterFace.BaseView;
import com.voblox.rangev1.Main.play.Music;
import com.voblox.rangev1.Model.Model;
import com.voblox.rangev1.Utilities.classicBluetooth;
import com.voblox.rangev1.Utilities.define;
import com.voblox.rangev1.Utilities.shareFunction;

public class DrapDropPresenter implements DrapDropContract.DrapDropPresenter {
    private BaseView mView;
    private Model mModel;


    public DrapDropPresenter(){

    };

    @Override
   public void start(){

    }

    @Override
    public void setModel(Model model){
        mModel = model;
    }

    @Override
    public void setView(BaseView view) {
        mView = view;
    }

    @Override
   public int sendCmd(int action,int port, int module, int duration, int value,
                       int dirMove, int additionModule, String [] ringLedColo) {
        return 1;
    }
}
