package com.voblox.rangev1.Utilities;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
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
import com.voblox.rangev1.Main.play.Control;
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
//    static Control mControl;
    private IntentFilter mIntentFilter;
    public static final String mBroadcastGetData = "VrobotGetData";

//    ServiceConnection shareConnection = new ServiceConnection()
//    {
//        @Override
//        public void onServiceConnected(ComponentName className, IBinder service) {
//            classicBluetooth.LocalBinder binder = (classicBluetooth.LocalBinder) service;
//            blueshare = binder.getService();
//            stateBond = true;
//        }
//        @Override
//        public void onServiceDisconnected(ComponentName name) {
//            stateBond = false;
//        }
//    };

    public shareFunction(){
//        Intent intent = new Intent(this, classicBluetooth.class);
//        bindService(intent, shareConnection, Context.BIND_AUTO_CREATE);

        mIntentFilter = new IntentFilter();
        mIntentFilter.addAction(mBroadcastGetData);
        Log.i("testRegistor","registerReceiver(mReceiver, mIntentFilter)");
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
    public static byte[] toBytes(long val)
    {
        byte[] result = new byte[3];

        result[0] = (byte) (val >> 16);
        result[1] = (byte) (val >> 8);
        result[2] = (byte) (val);

        return result;
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

    static float handleData(byte[] inBuffer) {
        byte[] bufGet = {0, 0, 0, 0};
        float tmpData = 0;

        for (int i = 0; i < 4; i++) {
            bufGet[i] = inBuffer[i + 3];
        }
        tmpData = shareFunction.byteArray2Float(bufGet);
        return tmpData;
    }

    static int _module = 0;
    public void getData(int module) {

        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                shareFunction.sendGetCommand(module, define.ON_MODULE);
            }
        };
        if (timer != null)
            timer.cancel();
        timer = new Timer("Timer");
        if (module != 0) {
            timer.schedule(timerTask, 0, 50);
            _module = module;
        }
        else {
            sendGetCommand(_module, define.OFF_MODULE);
            if (timer != null) {
                timer.cancel();
            }
        }
    }
//    byte[] handleData(byte[] inBuffer) {
//        byte[] bufGet = {0, 0, 0, 0};
//
//        for (int i = 0; i < 4; i++) {
//            bufGet[i] = inBuffer[i + 3];
//        }
//        return bufGet;
//    }

    @Override
    public void onResume() {
        super.onResume();
        registerReceiver(mReceiver, mIntentFilter);
        Log.i("testRegistor", "Share fucntion on resume");
    }

    int state =  0;
    byte[] tmpFbData = {0, 0, 0, 0};
    private BroadcastReceiver mReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            Charset charset = null;
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.KITKAT) {
                charset = StandardCharsets.ISO_8859_1;
            }
            Log.i("testRegistor"," on Share Function");
            state++;
            if (intent.getAction().equals(mBroadcastGetData)) {
                String inputData = intent.getStringExtra("fbData");
                byte[] buffer = inputData.getBytes(charset);
//                byte[] buffer1 = blueControl.getInstance().read();
                final String msgReceived = String.format(String.format(String.format("%02X", buffer[0]) + String.format("%02X", buffer[1]) + String.format("%02X", buffer[2])
                        + String.format("%02X", buffer[3]) + String.format("%02X", buffer[4]) + String.format("%02X", buffer[5])
                        + String.format("%02X", buffer[6]) + String.format("%02X", buffer[7]) + String.format("%02X", buffer[8])));
                Log.i("mByte ", "++" + msgReceived);
//                tmpFbData = handleData(buffer);
                String displayText = Float.toString(shareFunction.byteArray2Float(tmpFbData));
                switch (buffer[2]) {
                    case define.SRF05: {
//                        Intent intentPassData = new Intent(shareFunction.this, Control.class);
//                        intentPassData.putExtra("Srf05", displayText);
//                        startActivity(intent);
//                        if (stateGetSrf05) {
//                            textSrf05.setText(displayText);
//                            textColor.setText("");
//                            textLight.setText("");
//                        }
                        break;
                    }
                    case define.LINE: {
//                        if (stateGetLine) {
////                            textSrf05.setText("");
////                            textColor.setText("");
////                            textLight.setText("");
//                            switch ((int) shareFunction.byteArray2Float(fbData)) {
//                                case define.ALL_ON:
//                                    lineLeft.setBackgroundResource(R.drawable.ic_line_on);
//                                    lineRight.setBackgroundResource(R.drawable.ic_line_on);
//                                    break;
//                                case define.LEFT_ON:
//                                    lineLeft.setBackgroundResource(R.drawable.ic_line_on);
//                                    lineRight.setBackgroundResource(R.drawable.ic_line_off);
//                                    break;
//                                case define.RIGHT_ON:
//                                    lineLeft.setBackgroundResource(R.drawable.ic_line_off);
//                                    lineRight.setBackgroundResource(R.drawable.ic_line_on);
//                                    break;
//                                default:
//                                    lineLeft.setBackgroundResource(R.drawable.ic_line_off);
//                                    lineRight.setBackgroundResource(R.drawable.ic_line_off);
//                                    break;
//                            }
                            break;
//                        }
                    }
                    case define.LIGHT: {
//                        if (stateGetLightSensor) {
//                            textLight.setText(displayText + "%");
//                        }
                        break;
                    }
                    case define.COLOR: {
//                        if (stateGetColor) {
//                            switch ((int) shareFunction.byteArray2Float(fbData)) {
//                                case define.RED:
//                                    btnGetColor.setBackgroundResource(R.drawable.ic_read_color_red);
//                                    textColor.setText("Màu đỏ");
//                                    break;
//                                case define.GREEN:
//                                    btnGetColor.setBackgroundResource(R.drawable.ic_read_color_green);
//                                    textColor.setText("Màu xanh lá");
//                                    break;
//                                case define.BLUE:
//                                    btnGetColor.setBackgroundResource(R.drawable.ic_read_color_blue);
//                                    textColor.setText("Màu xanh lam");
//                                    break;
//                                case define.YELLOW:
//                                    btnGetColor.setBackgroundResource(R.drawable.ic_read_color_yellow);
//                                    textColor.setText("Màu vàng");
//                                    break;
//                                case define.WHITE:
//                                    btnGetColor.setBackgroundResource(R.drawable.ic_read_color_white);
//                                    textColor.setText("Màu trắng");
//                                    break;
//                                case define.BLACK:
//                                    btnGetColor.setBackgroundResource(R.drawable.ic_read_color_black);
//                                    textColor.setText("Màu đen");
//                                    break;
//                                default: {
//                                    btnGetColor.setBackgroundResource(R.drawable.ic_read_color_select);
//                                    textColor.setText("Màu ?");
//                                    break;
//                                }
//                            }
//                        }
                        break;
                    }
                    case define.MODE_BTN:{
//                        if (stateGetButton) {
//                            switch ((int) shareFunction.byteArray2Float(fbData)) {
//                                case define.MODE_1:
//                                    showMode1.setBackgroundResource(R.drawable.ic_mode2);
//                                    showMode2.setBackgroundResource(R.drawable.ic_mode1);
//                                    showMode3.setBackgroundResource(R.drawable.ic_mode1);
//                                    break;
//                                case define.MODE_2:
//                                    showMode1.setBackgroundResource(R.drawable.ic_mode2);
//                                    showMode2.setBackgroundResource(R.drawable.ic_mode2);
//                                    showMode3.setBackgroundResource(R.drawable.ic_mode1);
//                                    break;
//                                case define.MODE_3:
//                                    showMode1.setBackgroundResource(R.drawable.ic_mode2);
//                                    showMode2.setBackgroundResource(R.drawable.ic_mode2);
//                                    showMode3.setBackgroundResource(R.drawable.ic_mode2);
//                                    break;
//                                default:
//                                    showMode1.setBackgroundResource(R.drawable.ic_mode1);
//                                    showMode2.setBackgroundResource(R.drawable.ic_mode1);
//                                    showMode3.setBackgroundResource(R.drawable.ic_mode1);
//                                    break;
//                            }
//                        }
                        break;
                    }
                    case define.SOUND: {
//                        if (stateGetSound) {
//                            if (shareFunction.byteArray2Float(fbData) >= 1) {
//                                soundSignal.setBackgroundResource(R.drawable.have_sound);
//                            } else {
//                                soundSignal.setBackgroundResource(R.drawable.ic_sound);
//                            }
//                        }
                        break;
                    }
                    default:
                        break;
                }
            }
        }
    };

    @Override
    protected void onPause() {
        unregisterReceiver(mReceiver);
        super.onPause();
    }

}
