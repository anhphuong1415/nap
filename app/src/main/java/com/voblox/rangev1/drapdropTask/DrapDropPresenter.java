package com.voblox.rangev1.drapdropTask;

import android.util.Log;
import android.widget.Toast;

import com.voblox.rangev1.BaseInterFace.BaseView;
import com.voblox.rangev1.Model.Model;

public class DrapDropPresenter implements DrapDropContract.DrapDropPresenter {
    private BaseView mView;
    private Model mModel;

    public DrapDropPresenter(){};

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
   public void sendCmd(int action, int module, int data1, int data2,
                       int data3, int data4, int data5, int data6, int data) {
        Toast.makeText(mView.getViewContext(), "Send cmd", Toast.LENGTH_LONG).show();
//        mModel.sendCommand(cmd);
        Log.i("TAG","action:" + Integer.toString(action)  + " module:" + Integer.toString(module) + " data1:" +Integer.toString(data1) + " data2:" +Integer.toString(data2) +
                " data3:" + Integer.toString(data3)  + " data4:" + Integer.toString(data4) + " data5:" + Integer.toString(data5) + " data6:" + Integer.toString(data6) + " data:" + Integer.toString(data));
    }
}
