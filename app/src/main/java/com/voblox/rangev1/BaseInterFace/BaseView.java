package com.voblox.rangev1.BaseInterFace;

import android.content.Context;

public interface BaseView {
    void setPresenter(BasePresenter presenter);
    BasePresenter getPresenter();
    Context getViewContext();
}
