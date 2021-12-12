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

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

public class DrapDropActivity extends AppCompatActivity implements DrapDropContract.DrapDropView {
    private BasePresenter mPresenter;
    private  WebView mWebView;
    static final String LOAD_TMP = "need to load tmpMem";
    public static final String mBroadcastGetData = "VrobotGetData";
    private IntentFilter mIntentFilter;

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

        JavaInterface mJavascriptInterface = new JavaInterface(this);

        // set up the Presenter
        DrapDropPresenter mPresenter = new DrapDropPresenter();

        // set up the Model for DrapDrop Action
        Model mModel = new RangeOneModel(null);

        //
        mPresenter.setModel(mModel);
        mPresenter.setView(this);

        //
        mJavascriptInterface.setPresenter(mPresenter);
        mJavascriptInterface.setView(this);

        //
        this.setPresenter(mPresenter);

        mWebView.addJavascriptInterface(mJavascriptInterface, "Android");

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
    public Double byteArray2Float1(byte[] bytes)  {
        long intBits = (((byte)bytes[3] & 0xFF) << 24) |
                (((byte)bytes[2] & 0xFF) << 16) |
                (((byte)bytes[1] & 0xFF) << 8) |
                ((byte)bytes[0] & 0xFF);
//        return Double.intBitsToFloat(intBits);
        return Double.longBitsToDouble(intBits);
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
//                String displayText = Float.toString(shareFunction.byteArray2Float(fbData));
                switch (buffer[2]) {
                    case define.SRF05: {
                        //Log.i("testRegistor", Float.toString(shareFunction.byteArray2Float(tmpFbData)));
                        JavaInterface.getInstance().setModelValue(shareFunction.byteArray2Float(tmpFbData));
                        break;
                    }
                    case define.LINE: {

                        break;
                    }
                    case define.LIGHT: {
                        break;
                    }
                    case define.COLOR: {
                        break;
                    }
                    case define.MODE_BTN:{
                        break;
                    }
                    case define.SOUND: {
                        break;
                    }
                    default:
                        break;
                }
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
}