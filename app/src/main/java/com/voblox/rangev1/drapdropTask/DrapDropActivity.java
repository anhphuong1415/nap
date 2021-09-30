package com.voblox.rangev1.drapdropTask;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
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

public class DrapDropActivity extends AppCompatActivity implements DrapDropContract.DrapDropView {
    private BasePresenter mPresenter;
    private  WebView mWebView;
    static final String LOAD_TMP = "need to load tmpMem";

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

        // set up the view and JavaScript Interface for Webview
        mWebView = (WebView) findViewById(R.id.simpleWebView);
        WebSettings mWebViewSetting = mWebView.getSettings();
        mWebViewSetting.setJavaScriptEnabled(true);
        mWebViewSetting.setDomStorageEnabled(true);
        mWebView.setWebChromeClient(new WebChromeClient());

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
//        super.onPause();
        mWebView.evaluateJavascript("javascript:handleTmpSave();", null);
        super.onPause();
    }

    @Override
    protected void onStop()
    {
//        super.onStop();
        mWebView.evaluateJavascript("javascript:handleTmpSave();", null);
        super.onStop();
    }

    @Override
    protected void onResume()
    {
//        super.onResume();
        mWebView.evaluateJavascript("javascript:handleTmpLOAD();", null);
        super.onResume();
    }

    @Override
    protected void onStart()
    {
//        super.onStart();
        mWebView.evaluateJavascript("javascript:handleTmpLOAD();", null);
        super.onStart();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
//        mWebView.evaluateJavascript("javascript:handleTmpSave();", null);
        outState.putBoolean(LOAD_TMP, true);
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onDestroy()
    {
//        super.onStop();
        mWebView.evaluateJavascript("javascript:handleTmpSave();", null);
        super.onDestroy();
    }
}