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
    @JavascriptInterface
    public void sendCmd(int action, int module, int data1, int data2,
                        int data3, int data4, int data5, int data6, int data7)
    {
        Toast.makeText(mView.getViewContext(), "Send cmd", Toast.LENGTH_LONG).show();
        mDrapDropPresenter.sendCmd(action, module, data1, data2, data3, data4, data5, data6, data7);
    }

    @JavascriptInterface
    public void closeWebView() {
    }
}