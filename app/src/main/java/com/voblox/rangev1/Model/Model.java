package com.voblox.rangev1.Model;

import com.voblox.rangev1.Utilities.CommunicationWithDevice;

public interface Model {
    void sendCommand(String cmd);
    boolean checkReponse();
    void setCommandList();
    //TO DO
    // a function for setup commmunication way
    void setCommunicationDevice(CommunicationWithDevice Device);
}