package com.voblox.rangev1.Utilities;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.media.Ringtone;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.voblox.rangev1.R;
import com.voblox.rangev1.Utilities.classicBluetooth;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.Vector;

public class shareFunction extends AppCompatActivity {

    private static classicBluetooth blue;
    private static Timer timer;
    boolean stateBond = false;
    private static shareFunction mShareFunction;
    static classicBluetooth blueshare;

    ServiceConnection shareConnection = new ServiceConnection()
    {
        @Override
        public void onServiceConnected(ComponentName className, IBinder service) {
            classicBluetooth.LocalBinder binder = (classicBluetooth.LocalBinder) service;
            blueshare = binder.getService();
            stateBond = true;
        }
        @Override
        public void onServiceDisconnected(ComponentName name) {
            stateBond = false;
        }
    };

    public shareFunction(){
        Intent intent = new Intent(this, classicBluetooth.class);
        bindService(intent, shareConnection, Context.BIND_AUTO_CREATE);
    };
    public static void delay_ms(int ms) {
        try {
            Thread.sleep(ms);
        } catch(
                Exception e) {
            e.printStackTrace();
        }
    }
    public static void sendData() {
        if (blueshare.getInstance() != null) {
            blueshare.getInstance().write(define.cmdRunModule);
        }
    }
    public static shareFunction getInstance ()
    {
        if (mShareFunction == null)
            mShareFunction = new shareFunction();
        return mShareFunction;
    }

    public static float byteArray2Float(byte[] bytes)  {
        int intBits = (((byte)bytes[3] & 0xFF) << 24) |
                (((byte)bytes[2] & 0xFF) << 16) |
                (((byte)bytes[1] & 0xFF) << 8) |
                ((byte)bytes[0] & 0xFF);
        return Float.intBitsToFloat(intBits);
    }

    public static void runBuzzer(int id, int port, int slot, int freq, int duration)
    {
        define.cmdRunModule[2] =  0x09;
        define.cmdRunModule[3] = (byte)id;
        define.cmdRunModule[5] = define.BUZZER;
        define.cmdRunModule[6] = (byte)port;
        define.cmdRunModule[7] = (byte)slot;
        define.cmdRunModule[8] = (byte)(freq  & (byte)0xff);
        define.cmdRunModule[9] = (byte)((freq  >> 8) & (byte)0xff);
        define.cmdRunModule[10] = (byte)(duration  & (byte)0xff);
        define.cmdRunModule[11] = (byte)((duration  >> 8) & (byte)0xff);
        sendData();
    }
    public static void runRGB(int id, int port, int slot, byte[] color)
    {
        define.cmdRunModule[2] =  0x09;
        define.cmdRunModule[3] = (byte)id;
        define.cmdRunModule[5] = define.LED_RGB;
        define.cmdRunModule[6] = (byte)port;
        define.cmdRunModule[7] = (byte)slot;
        define.cmdRunModule[8] = (byte)id;
        System.arraycopy(color, 0, define.cmdRunModule, 9, color.length);
        sendData();
    }
    public static void runRingLed(int id, int port, int slot, long[]affectLed)
    {
        //Vector<Byte> ringCode = new Vector<Byte>();
        byte[] tmp = new byte [36];

        List<Byte> ringCode = new ArrayList();
        define.cmdRunModule[2] =  0x2d;
        define.cmdRunModule[3] = (byte)id;
        define.cmdRunModule[5] = define.RING_LED;
        define.cmdRunModule[6] = (byte)port;
        define.cmdRunModule[7] = (byte)slot;
        define.cmdRunModule[8] = (byte)id;

        for (int i = 0; i < 12; i++) {
            tmp[i*3 +0] = (byte)(affectLed[i]>>16);
            tmp[i*3 +1] = (byte)(affectLed[i]>>8);
            tmp[i*3 +2] = (byte)(affectLed[i]);
        }
        Log.i("code", String.format("%02X", tmp[0]) + String.format("%02X", tmp[1]) + String.format("%02X", tmp[2])
                + String.format("%02X", tmp[3]) + String.format("%02X", tmp[4]) + String.format("%02X", tmp[5]));
        System.arraycopy(tmp, 0, define.cmdRunModule, 9, tmp.length);
        sendData();
    }
    public static void runMaTrix(int id, int port, int slot, byte[] effect, int duration)
    {
        define.cmdRunModule[2] =  0x0e;
        define.cmdRunModule[3] = (byte)id;
        define.cmdRunModule[5] = define.LED_MATRIX;
        define.cmdRunModule[6] = (byte)port;
        define.cmdRunModule[7] = (byte)slot;
        System.arraycopy(effect, 0, define.cmdRunModule, 8, effect.length);
        define.cmdRunModule[8 + effect.length] = (byte)duration;
        sendData();
    }

    public static void runJoystick(int id, int port, int slot, int leftSpeed, int  rightSpeed)
    {
        define.cmdRunModule[2] =  0x09;
        define.cmdRunModule[3] = (byte)id;
        define.cmdRunModule[5] = define.JOYSTICK;
        define.cmdRunModule[6] = (byte)port;
        define.cmdRunModule[7] = (byte)slot;
        define.cmdRunModule[8] = (byte)(leftSpeed  & (byte)0xff);
        define.cmdRunModule[9] = (byte)((leftSpeed  >> 8) & (byte)0xff);
        define.cmdRunModule[10] = (byte)(rightSpeed  & (byte)0xff);
        define.cmdRunModule[11] = (byte)((rightSpeed  >> 8) & (byte)0xff);
        sendData();
    }
    public static void sendGetCommand(int module, int stateModule) {
        if (blueshare.getInstance() != null) {
            define.cmd_get_valModule[5] = (byte) module;
            define.cmd_get_valModule[6] = (byte) stateModule;
            blueshare.getInstance().write(define.cmd_get_valModule);
        }
    }
    public  static boolean getStateConnectBluetooth() {
        return blueshare.get_state_blue_connect();
    }
//        TimerTask timerTask = new TimerTask() {
//            @Override
//            public void run() {
//                try {
//                    if (blueshare.get_state_blue_connect()) {
//                        Log.i("TAG"," connectedr");
//                        timer.cancel();
//                        statusConnect =  true;
//                    } else {
//                        Log.i("TAG"," not connect");
//                        statusConnect =  false;
//                    }
//                } catch (NullPointerException ex) {
//                }
//            }
//        };
//        if (timer != null)
//            timer.cancel();
//        timer = new Timer("Timer");
//        timer.schedule(timerTask, 0, 1000);
//        return  statusConnect;
//    }
}
