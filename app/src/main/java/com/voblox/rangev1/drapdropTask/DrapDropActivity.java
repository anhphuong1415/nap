package com.voblox.rangev1.drapdropTask;

import com.voblox.rangev1.Utilities.shareFunction;
import com.voblox.rangev1.drapdropTask.JavaInterface;
import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.util.Log;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import androidx.constraintlayout.widget.ConstraintLayout;

import com.voblox.rangev1.BaseInterFace.BasePresenter;
import com.voblox.rangev1.Model.Model;
import com.voblox.rangev1.Model.RangeOneModel;
import com.voblox.rangev1.R;
import com.voblox.rangev1.Utilities.WebChromeClient;
import com.voblox.rangev1.Utilities.define;

import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

public class DrapDropActivity extends AppCompatActivity implements DrapDropContract.DrapDropView {
    private BasePresenter mPresenter;
    private  WebView mWebView;
    static final String LOAD_TMP = "need to load tmpMem";
    public static final String mBroadcastGetData = "VrobotGetData";
    private IntentFilter mIntentFilter;
    private double                  modelValue1 = 0;

    public class JavaScriptWebViewClient extends WebViewClient {
        @Override
        public void onPageFinished(WebView view, String url)
        {
            super.onPageFinished(view, url);
            view.evaluateJavascript("javascript:handleTmpLOAD();", null);
        }
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drap_drop);
        View decorView = getWindow().getDecorView();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            decorView.setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                            | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
            );
        }
        // set up the view and JavaScript Interface for Webview
        mWebView = (WebView) findViewById(R.id.simpleWebView);
        WebSettings mWebViewSetting = mWebView.getSettings();
        mWebViewSetting.setJavaScriptEnabled(true);
        mWebViewSetting.setDomStorageEnabled(true);
        mWebView.setWebChromeClient(new WebChromeClient());

        mIntentFilter = new IntentFilter();
        mIntentFilter.addAction(mBroadcastGetData);

        // set up the Presenter
        DrapDropPresenter mPresenter = new DrapDropPresenter();

        // set up the Model for DrapDrop Action
        Model mModel = new RangeOneModel(null);

        //
        mPresenter.setModel(mModel);
        mPresenter.setView(this);

        //
        this.setPresenter(mPresenter);

        mWebView.addJavascriptInterface(this, "Android");

        // load the URL for the webView
        mWebView.loadUrl("file:///android_asset/webview.html");

        if(savedInstanceState != null)
        {
            if(savedInstanceState.getBoolean(LOAD_TMP)) {
                mWebView.setWebViewClient(new JavaScriptWebViewClient());
            }
        }
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

        final int flags = View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_FULLSCREEN
                | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY;

        getWindow().getDecorView().setSystemUiVisibility(flags);

        // Code below is to handle presses of Volume up or Volume down.
        // Without this, after pressing volume buttons, the navigation bar will
        // show up and won't hide
        final View decorView2 = getWindow().getDecorView();
        decorView2
                .setOnSystemUiVisibilityChangeListener(new View.OnSystemUiVisibilityChangeListener()
                {
                    @Override
                    public void onSystemUiVisibilityChange(int visibility)
                    {
                        if((visibility & View.SYSTEM_UI_FLAG_FULLSCREEN) == 0)
                        {
                            decorView2.setSystemUiVisibility(flags);
                        }
                    }
                });
    }

    @Override
    public void setPresenter(BasePresenter presenter) {
        this.mPresenter = presenter;
    }

    @Override
    public BasePresenter getPresenter() {
        return null;
    }

    @Override
    public Context getViewContext() {
        return this;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    protected void onPause()
    {
        unregisterReceiver(mReceiver);
        mWebView.evaluateJavascript("javascript:handleTmpSave();", null);
        super.onPause();
    }

    @Override
    protected void onStop()
    {
        mWebView.evaluateJavascript("javascript:handleTmpSave();", null);
        super.onStop();
    }

    @Override
    protected void onResume()
    {
        mWebView.evaluateJavascript("javascript:handleTmpLOAD();", null);
        registerReceiver(mReceiver, mIntentFilter);
        Log.i("testRegistor", "onResume Drag and drop");
        super.onResume();
    }

    @Override
    protected void onStart()
    {
        mWebView.evaluateJavascript("javascript:handleTmpLOAD();", null);
        super.onStart();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
//        mWebView.evaluateJavascript("javascript:handleTmpSave();", null);
        outState.putBoolean(LOAD_TMP, true);
        super.onSaveInstanceState(outState);
    }

    byte[] handleData(byte[] inBuffer) {
        byte[] bufGet = {0, 0, 0, 0};

        for (int i = 0; i < 4; i++) {
            bufGet[i] = inBuffer[i + 3];
        }
        return bufGet;
    }
    public float byteArray2Float1(byte[] bytes)  {
        int intBits = (((byte)bytes[3] & 0xFF) << 24) |
                (((byte)bytes[2] & 0xFF) << 16) |
                (((byte)bytes[1] & 0xFF) << 8) |
                ((byte)bytes[0] & 0xFF);
        return Float.intBitsToFloat(intBits);
    }

    byte[] tmpFbData = {0, 0, 0, 0};
    private BroadcastReceiver mReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            Charset charset = null;
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.KITKAT) {
                charset = StandardCharsets.ISO_8859_1;
            }
            if (intent.getAction().equals(mBroadcastGetData)) {
                String inputData = intent.getStringExtra("fbData");
                byte[] buffer = inputData.getBytes(charset);
//                byte[] buffer1 = blueControl.getInstance().read();
//                final String msgReceived = String.format(String.format(String.format("%02X", buffer1[0]) + String.format("%02X", buffer1[1]) + String.format("%02X", buffer1[2])
//                        + String.format("%02X", buffer1[3]) + String.format("%02X", buffer1[4]) + String.format("%02X", buffer1[5])
//                        + String.format("%02X", buffer1[6]) + String.format("%02X", buffer1[7]) + String.format("%02X", buffer1[8])));
//                Log.i("mByte ", "++" + msgReceived);

                tmpFbData = handleData(buffer);
                modelValue1 = byteArray2Float1(tmpFbData);
            }
        }
    };

    @Override
    protected void onDestroy()
    {
//        super.onStop();
        mWebView.evaluateJavascript("javascript:handleTmpSave();", null);
        super.onDestroy();
    }

    @SuppressLint("NewApi")
    @Override
    public void onWindowFocusChanged(boolean hasFocus)
    {
        super.onWindowFocusChanged(hasFocus);
            getWindow().getDecorView().setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                            | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
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
     * servo 12    * */

    /*diretion for move
     * tiến 1
     * lùi 2
     * trái 3
     * phải 4*/

    /*addition Module for chose motor, servo
     * left 1
     * right 2
     * both 3*/

    public void handle_buzzer(int freq, int duration)
    {
        int _duration = duration * 255;
        switch (freq) {
            case 1:
                shareFunction.runBuzzer(0,0,0, define.C, _duration);
                break;
            case 2:
                shareFunction.runBuzzer(0,0,0, define.C_D, _duration);
                break;
            case 3:
                shareFunction.runBuzzer(0,0,0, define.D, _duration);
                break;
            case 4:
                shareFunction.runBuzzer(0,0,0, define.D_E, _duration);
                break;
            case 5:
                shareFunction.runBuzzer(0,0,0, define.E, _duration);
                break;
            case 6:
                shareFunction.runBuzzer(0,0,0, define.F, _duration);
                break;
            case 7:
                shareFunction.runBuzzer(0,0,0, define.F_G, _duration);
                break;
            case 8:
                shareFunction.runBuzzer(0,0,0, define.G, _duration);
                break;
            case 9:
                shareFunction.runBuzzer(0,0,0, define.G_A, _duration);
                break;
            case 10:
                shareFunction.runBuzzer(0,0,0, define.A, _duration);
                break;
            case 11:
                shareFunction.runBuzzer(0,0,0, define.A_B, _duration);
                break;
            case 12:
                shareFunction.runBuzzer(0,0,0, define.B, _duration);
                break;
        }
    }
    void handleMotor(int direct, int speed, int timeRun)
    {
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

    void handleRGB(long colorLed1, long colorLed2, int time)
    {
        byte[] off = {0x00, 0x00, 0x00};
        shareFunction.runRGB(1,0,0,shareFunction.toBytes(colorLed1));
        shareFunction.runRGB(2,0,0,shareFunction.toBytes(colorLed2));
        if (time != 0) {
            shareFunction.delay_ms(time * 1000);
            shareFunction.runRGB(0, 0, 0, off);
        }
    }
    void handleRingLed(long[] color) {
        shareFunction.runRingLed(0,0,0, color);
    }
    void handleLedMatrix(long[] dataDisplay, int duration) {
        byte[] tmpData = {0, 0, 0 ,0 ,0 ,0, 0, 0};
        for (int i = 0; i < 8; i++) {
            tmpData[i] = (byte)(dataDisplay[i] & 0xFF);
        }
        shareFunction.runMaTrix(0, 0, 0, tmpData, duration);
    }
    void handleReadSensor(int module)
    {
        shareFunction.getInstance().getData(module);
//        shareFunction.delay_ms(1000);
//        shareFunction.getInstance().getData(define.NONE);
    }
    /*return int = sensor value when want to get data*/

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
                        int dirMove, int additionModule,
                        long value1, long value2, long value3, long value4,
                        long value5, long value6, long value7, long value8,
                        long value9, long value10, long value11, long value12)
    {
//        Toast.makeText(this.getViewContext(), Integer.toString(module) + Integer.toString(duration) +
//                Long.toString(value1) + Long.toString(value2) +
//                Long.toString(value3) + Long.toString(value4) +
//                Long.toString(value5) + Long.toString(value6) +
//                Long.toString(value7) + Long.toString(value8) +
//                Long.toString(value9) + Long.toString(value10) +
//                Long.toString(value11) + Long.toString(value12), Toast.LENGTH_LONG).show();
//        Log.i("TAG","action:" + Integer.toString(action)  + " port:" + Integer.toString(port) +
//                " module:" +Integer.toString(module) + " duration:" +Integer.toString(duration) +
//                " value:" + Long.toHexString(value[0])  + " dirMove:" + Integer.toString(dirMove) +
//                " additionModule:" + Integer.toString(additionModule));
//        mDrapDropPresenter.sendCmd(action, module, data1, data2, data3, data4, data5, data6, data7);
        long[] value = {value1,  value2,  value3,  value4,
                value5,  value6,  value7,  value8,
                value9,  value10,  value11,  value12};

        switch (module) {
            case define.SRF05:
                handleReadSensor(define.SRF05);
//                Toast.makeText(this.getViewContext(), "handle SRF05", Toast.LENGTH_LONG).show();
                break;
            case define.LINE:
                handleReadSensor(define.LINE);
                break;
            case define.LIGHT:
                handleReadSensor(define.LIGHT);
                break;
            case define.COLOR:
                handleReadSensor(define.COLOR);
                break;
            case define.JOYSTICK:
                handleMotor(dirMove, (int)value[0], duration);
                break;
            case define.MODE_BTN:
                break;
            case define.LED_MATRIX:
                switch ((int)value1) {
                    case define.L2R_ARROW:
                        Toast.makeText(this.getViewContext(), "L2R_ARROW", Toast.LENGTH_LONG).show();
                        break;
                    case define.R2L_ARROW:
                        Toast.makeText(this.getViewContext(), "R2L_ARROW", Toast.LENGTH_LONG).show();
                        break;
                    case define.UP_ARROW:
                        Toast.makeText(this.getViewContext(), "UP_ARROW", Toast.LENGTH_LONG).show();
                        break;
                    case define.DOWN_ARROW:
                        Toast.makeText(this.getViewContext(), "DOWN_ARROW", Toast.LENGTH_LONG).show();
                        break;
                    case define.HEART_SYMB:
                        Toast.makeText(this.getViewContext(), "HEART_SYMB", Toast.LENGTH_LONG).show();
                        break;
                    case define.SMILE_SYMB:
                        Toast.makeText(this.getViewContext(), "SMILE_SYMB", Toast.LENGTH_LONG).show();
                        break;
                    case define.START_SYMB:
                        Toast.makeText(this.getViewContext(), "START_SYMB", Toast.LENGTH_LONG).show();
                        break;
                    case define.EFFECT1:
                        Toast.makeText(this.getViewContext(), "EFFECT1", Toast.LENGTH_LONG).show();
                        break;
                    case define.EFFECT2:
                        Toast.makeText(this.getViewContext(), "EFFECT2", Toast.LENGTH_LONG).show();
                        break;
                    case define.EFFECT3:
                        Toast.makeText(this.getViewContext(), "EFFECT3", Toast.LENGTH_LONG).show();
                        break;
                    default:
                        handleLedMatrix(value, 0xa0);
                        break;
                }

                break;
            case define.LED_RGB:
                handleRGB((long)value[0], (long)value[1], duration);
                break;
            case define.BUZZER:
                handle_buzzer((int)value[0], duration);
                //Toast.makeText(this.getViewContext(), "handle buzzer", Toast.LENGTH_LONG).show();
                break;
            case define.SOUND:
                break;
            case define.RING_LED:
                handleRingLed(value);
                break;
            default:
                break;
        }
//        Log.i("testRegistor+++", Double.toString(modelValue1));
//        Toast.makeText(this.getViewContext(), "module: " + Double.toString(module), Toast.LENGTH_LONG).show();
//        return 1;
//        Toast.makeText(mView.getViewContext(), Integer.toString(module), Toast.LENGTH_SHORT).show();
////        mDrapDropPresenter.sendCmd(action, module, data1, data2, data3, data4, data5, data6, data7);
        return (long)modelValue1;
    }

    @JavascriptInterface
    public void wait(int second)
    {

    }

    @JavascriptInterface
    public void JSrequsetShow(String prefix)
    {
        Toast.makeText(this.getViewContext(), prefix + String.valueOf(modelValue1), Toast.LENGTH_LONG).show();
    }

    @JavascriptInterface
    public void closeWebView() {
    }

    @JavascriptInterface
    public double GetValue() {
        return modelValue1;
    }

    // Call when press Bluetooth button
    @JavascriptInterface
    public void connectBluetooth() {
    }

    // Call when stop, finish code, or change code in workspace
    @JavascriptInterface
    public void finishCmd() {
    }

    public byte[] longToBytes(long x) {
        ByteBuffer buffer = ByteBuffer.allocate(Long.BYTES);
        buffer.putLong(x);
        return buffer.array();
    }
}