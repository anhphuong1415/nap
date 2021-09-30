package com.voblox.rangev1.Utilities;

public interface CommunicationWithDevice {
    void sendCommand(String cmd);
    String getReponse();
}
