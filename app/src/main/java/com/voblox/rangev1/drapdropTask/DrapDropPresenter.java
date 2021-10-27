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
        Toast.makeText(mView.getViewContext(), "Send cmd", Toast.LENGTH_LONG).show();
//        mModel.sendCommand(cmd);
//        Log.i("TAG","action:" + Integer.toString(action)  + " module:" + Integer.toString(module) + " data1:" +Integer.toString(data1) + " data2:" +Integer.toString(data2) +
//                " data3:" + Integer.toString(data3)  + " data4:" + Integer.toString(data4) + " data5:" + Integer.toString(data5) + " data6:" + Integer.toString(data6) + " data:" + Integer.toString(data));
//        switch (module) {
//            case define.SRF05:
//                break;
//            case define.LINE:
//                break;
//            case define.LIGHT:
//                break;
//            case define.COLOR:
//                break;
//            case define.JOYSTICK:
//                break;
//            case define.MODE_BTN:
//                break;
//            case define.LED_MATRIX:
//                break;
//            case define.LED_RGB:
//                break;
//            case define.BUZZER:
//                handle_buzzer(data1);
//                break;
//            case define.SOUND:
//                break;
//            case define.RING_LED:
//                break;
//            default:
//                break;
//        }
//    }
//    public void handle_buzzer(int data1) {
//        switch (data1) {
//            case 1:
//                shareFunction.runTone(define.C);
//                break;
//            case 2:
//                shareFunction.runTone(define.C_D);
//                break;
//            case 3:
//                shareFunction.runTone(define.D);
//                break;
//            case 4:
//                shareFunction.runTone(define.D_E);
//                break;
//            case 5:
//                shareFunction.runTone(define.E);
//                break;
//            case 6:
//                shareFunction.runTone(define.F);
//                break;
//            case 7:
//                shareFunction.runTone(define.F_G);
//                break;
//            case 8:
//                shareFunction.runTone(define.G);
//                break;
//            case 9:
//                shareFunction.runTone(define.G_A);
//                break;
//        }
        return 1;
    }
}
