package com.voblox.rangev1.drapdropTask;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;

import com.voblox.rangev1.BaseInterFace.BasePresenter;
import com.voblox.rangev1.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link DrapDropViewFagment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DrapDropViewFagment extends Fragment implements DrapDropContract.DrapDropView{

    BasePresenter mPresenter;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public DrapDropViewFagment() {
        // Required empty public constructor
//        WebView mWebView = (WebView) findViewById(R.id.simpleWebView);
//        WebSettings mWebViewSetting = mWebView.getSettings();
//        mWebViewSetting.setJavaScriptEnabled(true);
//        mWebView.setWebChromeClient(new WebChromeClient());
//        JavaInterface mJavascriptInterface = new JavaInterface(this);
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment DrapDropViewFagment.
     */
    // TODO: Rename and change types and number of parameters
    public static DrapDropViewFagment newInstance(String param1, String param2) {
        DrapDropViewFagment fragment = new DrapDropViewFagment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_drap_drop_view_fagment, container, false);
    }

    @Override
    public void setPresenter(BasePresenter presenter){
        mPresenter = presenter;
    }

    @Override
    public BasePresenter getPresenter(){
        return mPresenter;
    }

    public Context getViewContext() {
        return this.getContext();
    }
}