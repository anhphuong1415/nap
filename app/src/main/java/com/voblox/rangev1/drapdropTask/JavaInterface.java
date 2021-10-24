package com.voblox.rangev1.drapdropTask;

import android.content.Context;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.widget.Toast;

import com.voblox.rangev1.BaseInterFace.BaseView;

public class JavaInterface {
    private DrapDropPresenter   mDrapDropPresenter;
    private Context             mContext;
    private BaseView                mView;
    JavaInterface(Context c){mContext = c;}

    void setPresenter(DrapDropPresenter p){mDrapDropPresenter = p;}

    void setView(BaseView view) {
        mView = view;
    }

    /* action
    * STOP 0
    * GET 1
    * RUN 2
    * RESET 4
    * START 5 */

    /*Module
    * untrasonic sound_sensor 1
    * path_detect linefollower 2
    * light_sensor 3
    * color_sensor 4
    * joystich 5
    * led_matrix 7
    * rgb_led 8
    * speaker 9
    * ring_led 10
    * servo 11
    * follow sound sensor 12
    * */

    /*diretion for move
    * tiến 1
    * lùi 2
    * trái 3
    * phải 4*/

    /*addition Module for chose motor, servo
    * left 1
    * right 2
    * both 3*/

    /*return int = sensor value when want to get data*/
    @JavascriptInterface
    public int sendCmd(int action,int port, int module, int duration, int value,
                        int dirMove, int additionModule, String [] ringLedColo)
    {
        Toast.makeText(mView.getViewContext(), "Send cmd", Toast.LENGTH_LONG).show();
//        mDrapDropPresenter.sendCmd(action, module, data1, data2, data3, data4, data5, data6, data7);
        return 1;
    }

    @JavascriptInterface
    public void wait(int second)
    {

    }

    @JavascriptInterface
    public void closeWebView() {
    }
}