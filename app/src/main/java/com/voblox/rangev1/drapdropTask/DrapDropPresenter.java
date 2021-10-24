package com.voblox.rangev1.drapdropTask;

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
   public int sendCmd(int action,int port, int module, int duration, int value,
                       int dirMove, int additionModule, String [] ringLedColo) {
        Toast.makeText(mView.getViewContext(), "Send cmd", Toast.LENGTH_LONG).show();
//        mModel.sendCommand(cmd);
        return 1;
    }
}
