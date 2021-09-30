package com.voblox.rangev1.Model;

import android.widget.Toast;

import androidx.annotation.Nullable;

import com.voblox.rangev1.Utilities.CommunicationWithDevice;

public class RangeOneModel implements Model {
    CommunicationWithDevice mCommunicationDevice;

    public RangeOneModel(@Nullable CommunicationWithDevice communicationDevice) {
        mCommunicationDevice = communicationDevice;
    }
    @Override
    public void sendCommand(String cmd) {
        mCommunicationDevice.sendCommand(cmd);
    }

    @Override
    public boolean checkReponse() {
        return false;
    }

    @Override
    public void setCommandList() {
    }

    @Override
    public void setCommunicationDevice(CommunicationWithDevice Device) {
        mCommunicationDevice = Device;
    }
}
