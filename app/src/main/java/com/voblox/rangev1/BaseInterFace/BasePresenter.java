package com.voblox.rangev1.BaseInterFace;

import com.voblox.rangev1.Model.Model;

public interface BasePresenter {
    void start();
    void setModel(Model model);
    void setView(BaseView view);
}
