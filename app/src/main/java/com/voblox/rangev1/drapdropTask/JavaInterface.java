package com.voblox.rangev1.drapdropTask;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.widget.Toast;

import com.voblox.rangev1.BaseInterFace.BaseView;
import com.voblox.rangev1.Utilities.shareFunction;
import com.voblox.rangev1.Utilities.define;

public class JavaInterface {
    private DrapDropPresenter   mDrapDropPresenter;
    private Context             mContext;
    private BaseView            mView;

    JavaInterface(Context c){
        mContext = c;
    }

    void setPresenter(DrapDropPresenter p){
        mDrapDropPresenter = p;
    }

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
    public void handle_buzzer(int freq, int duration) {
        Toast.makeText(mView.getViewContext(), "run buzzer", Toast.LENGTH_LONG).show();
        switch (freq) {
            case 1:
                shareFunction.runBuzzer(0,0,0, define.C, duration);
                break;
            case 2:
                shareFunction.runBuzzer(0,0,0, define.C_D, duration);
                break;
            case 3:
                shareFunction.runBuzzer(0,0,0, define.D, duration);
                break;
            case 4:
                shareFunction.runBuzzer(0,0,0, define.D_E, duration);
                break;
            case 5:
                shareFunction.runBuzzer(0,0,0, define.E, duration);
                break;
            case 6:
                shareFunction.runBuzzer(0,0,0, define.F, duration);
                break;
            case 7:
                shareFunction.runBuzzer(0,0,0, define.F_G, duration);
                break;
            case 8:
                shareFunction.runBuzzer(0,0,0, define.G, duration);
                break;
            case 9:
                shareFunction.runBuzzer(0,0,0, define.G_A, duration);
                break;
            case 10:
                shareFunction.runBuzzer(0,0,0, define.A, duration);
                break;
            case 11:
                shareFunction.runBuzzer(0,0,0, define.A_B, duration);
                break;
            case 12:
                shareFunction.runBuzzer(0,0,0, define.B, duration);
                break;
        }
    }
    void handleMotor(int direct, int speed, int timeRun) {
        switch (direct) {
            case define.FORWARD:
                shareFunction.runJoystick(0, 0, 0, speed, -speed);
                shareFunction.delay_ms(timeRun*1000);
                break;
            case define.BACKWARD:
                shareFunction.runJoystick(0, 0, 0, -speed, speed);
                shareFunction.delay_ms(timeRun*1000);
                break;
            case define.TURNLEFT:
                shareFunction.runJoystick(0, 0, 0, -speed, -speed);
                shareFunction.delay_ms(timeRun*1000);
                break;
            case define.TURNRIGHT:
                shareFunction.runJoystick(0, 0, 0, speed, speed);
                shareFunction.delay_ms(timeRun*1000);
                break;
        }
        shareFunction.runJoystick(0, 0, 0, 0, 0);
    }
    /*return int = sensor value when want to get data*/
    @JavascriptInterface
    public int sendCmd(int action,int port, int module, int duration, int value,
                        int dirMove, int additionModule, String [] ringLedColo)
    {
//        Toast.makeText(mView.getViewContext(), "Send cmd+++", Toast.LENGTH_LONG).show();
        Log.i("TAG","action:" + Integer.toString(action)  + " port:" + Integer.toString(port) +
                " module:" +Integer.toString(module) + " duration:" +Integer.toString(duration) +
                " value:" + Integer.toString(value)  + " dirMove:" + Integer.toString(dirMove) +
                " additionModule:" + Integer.toString(additionModule));
//        mDrapDropPresenter.sendCmd(action, module, data1, data2, data3, data4, data5, data6, data7);

        switch (module) {
            case define.SRF05:
                break;
            case define.LINE:
                break;
            case define.LIGHT:
                break;
            case define.COLOR:
                break;
            case define.JOYSTICK:
                handleMotor(dirMove, value, duration);
                break;
            case define.MODE_BTN:
                break;
            case define.LED_MATRIX:
                break;
            case define.LED_RGB:
                break;
            case define.BUZZER:
                handle_buzzer(value, duration);
                break;
            case define.SOUND:
                break;
            case define.RING_LED:
                break;
            default:
                break;
        }
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