package com.voblox.rangev1.drapdropTask;

import android.content.Context;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.widget.Toast;

import com.voblox.rangev1.BaseInterFace.BaseView;

public class JavaInterface {
    private DrapDropPresenter   mDrapDropPresenter;
    private Context             mContext;
    private BaseView             mView;

    // set moduleValue
    public void setModelValue(double modelValue) {
        this.modelValue = modelValue;
    }

    private double              modelValue = 0;
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
    * follow sound sensor 10
    * ring_led 11
    * servo 12
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

    /*return long = sensor value when want to get data for reserve when color sensor return rgb code
    * return value need to be store in modelValue for display Toast when get sensor data
    * value: velocity - angle - pitch - color code(3 byte hexa for RGB code with RGB led command)
    * value: 12 long (3 byte hexa for RGB coded) for each led on RingLEd at order
    *         2 long (3 byte hexa for RGB coded) for 2 led in RGB led MODUEL left to right
    *         1 long for move speed, note, char at integer as ASCII, special Effect(from 300 -> 309),
    *            angle at servo.
    *  */

    /* SPECAIL Effect for matrix led:
    *    300: l2r arrow
    *    301: r2l arrow
    *    302: up arrow
    *    303: down arrow
    *    304: heart symbol
    *    305: smile symbol
    *    306: star symbol
    *    307: effect 1
    *    308: effect 2
    *    309: effect 3 */
    @JavascriptInterface
    public long sendCmd(int action,int port, int module, int duration,
                        int dirMove, int additionModule, long [] value)
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
    public void JSrequsetShow(String prefix)
    {
        Toast.makeText(mView.getViewContext(), prefix + String.valueOf(modelValue), Toast.LENGTH_LONG).show();
    }

    @JavascriptInterface
    public void closeWebView() {
    }
}