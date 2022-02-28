package com.voblox.rangev1.Main.play;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import com.marcinmoskala.arcseekbar.ArcSeekBar;
import com.marcinmoskala.arcseekbar.ProgressListener;
import com.voblox.rangev1.R;
import com.voblox.rangev1.Utilities.classicBluetooth.LocalBinder;
import com.voblox.rangev1.Utilities.classicBluetooth;
import com.voblox.rangev1.Utilities.joystick;
import com.voblox.rangev1.Utilities.shareFunction;
import com.voblox.rangev1.Utilities.define;
import com.voblox.rangev1.Utilities.connectingBluetooth;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.core.view.WindowInsetsControllerCompat;

import com.voblox.rangev1.Utilities.define;
import android.app.PendingIntent;
import android.bluetooth.BluetoothAdapter;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.content.pm.PackageManager;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.IBinder;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
//import android.support.v4.app.ActivityCompat;
//import android.support.v4.content.ContextCompat;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Timer;
import java.util.TimerTask;

import android.view.View.OnTouchListener;

import at.grabner.circleprogress.CircleProgressView;

public class Control extends AppCompatActivity {
    ImageButton backCtrBtn;
    ImageButton btnDance, btnLed, btnConnect, btnBuzzer, btnLedMatrix, btnRingLed;
    ImageButton btnGetSrf05, btnGetLine, btnGetColor, btnGetSound, btnGetLight, btnGetBtn;
    ImageButton btnModeSrf05, btnModeSoundDetect, btnModeFollwingLine, btnModeRunCircle;
    TextView textSrf05, textLight, textColor, textSound, textDC1, textDC2, textServo, lineRight, lineLeft;
    ImageButton showMode1, showMode2, showMode3;
    pl.droidsonroids.gif.GifImageView soundSignal;
    CircleProgressView magnitudeSpeed;
    SeekBar DC1, DC2;
    ArcSeekBar servo;
    boolean state_led = false;
    int ledColor = 0;
    int leftSpeed = 0, rightSpeed = 0;
    int buzzerFreq = 0, buzzerDuration = 0;
    int xPos, yPos, volumeSpeed;
    int index = 0;
    boolean stateGetSrf05, stateGetLightSensor, stateGetLine, stateGetButton, stateGetColor, stateGetSound;
    boolean stateRunFollowingLine, stateRunInCircle, stateRunSrf05, stateRunSoundMode;
    int val = 0;
    int cnt_effect, cnt_effect_ring;
    Timer timer;
    RelativeLayout layout_joystick;
    ImageView image_joystick, image_border;
    TextView textView1, textView2, textView3, textView4, textView5;
    joystick js;
    byte[] fbData = {0, 0, 0, 0};
    public static final String mBroadcastGetData = "VrobotGetData";
    private IntentFilter mIntentFilter;
    int value_speed = 0;

    final BluetoothAdapter bAdapter = BluetoothAdapter.getDefaultAdapter();

//    static int _module = 0;
//    public void getData(int module) {
//
//        TimerTask timerTask = new TimerTask() {
//            @Override
//            public void run() {
//                shareFunction.sendGetCommand(module, define.ON_MODULE);
//                Log.i("hhhh", "Sent request read sensor data");
//            }
//        };
//        if (timer != null)
//            timer.cancel();
//        timer = new Timer("Timer");
//        if (module != 0) {
//            timer.schedule(timerTask, 0, 50);
//            _module = module;
//        }
//        else {
//            shareFunction.sendGetCommand(_module, define.OFF_MODULE);
//            textSrf05.setText("");
//            textLight.setText("");
//            if (timer != null) {
//                timer.cancel();
//            }
//        }
//    }
    public void check_connected() {
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                try {
                    if (shareFunction.getStateConnectBluetooth()) {
                        Log.i("TAG"," connectedr");
                        btnConnect.setBackgroundResource(R.drawable.ic_ble_on);
                        timer.cancel();
                    } else {
                        btnConnect.setBackgroundResource(R.drawable.ic_ble_off);
                        Log.i("TAG"," not connect");
                    }
                } catch (NullPointerException ex) {
                }
            }
        };
        if (timer != null)
            timer.cancel();
        timer = new Timer("Timer");
        timer.schedule(timerTask, 0, 1000);
    }

    void resetState(boolean state)
    {
        if (stateGetSrf05 != state)
            stateGetSrf05 = false;
        if (stateGetLightSensor != state)
            stateGetLightSensor = false;
        if (stateGetLine != state)
            stateGetLine = false;
        if (stateGetButton != state)
            stateGetButton = false;
        if (stateGetColor != state)
            stateGetColor = false;
        if (stateGetSound != state)
            stateGetSound = false;
        if (stateRunFollowingLine != state)
            stateRunFollowingLine = false;
        if (stateRunInCircle != state)
            stateRunInCircle = false;
        if (stateRunSrf05 != state)
            stateRunSrf05 = false;
        if (stateRunSoundMode != state)
            stateRunSoundMode = false;
    }

    void resetBackground()
    {
        btnModeSrf05.setBackgroundResource(R.drawable.ic_srf05_mode);
        btnModeSoundDetect.setBackgroundResource(R.drawable.ic_sound_mode);
        btnModeFollwingLine.setBackgroundResource(R.drawable.ic_line_mode);
        btnModeRunCircle.setBackgroundResource(R.drawable.ic_round_mode);
        btnGetSrf05.setBackgroundResource(R.drawable.ic_read_srf05);
        btnGetLine.setBackgroundResource(R.drawable.ic_read_line);
        btnGetLight.setBackgroundResource(R.drawable.ic_read_light);
        btnGetBtn.setBackgroundResource(R.drawable.ic_read_button);
        btnGetSound.setBackgroundResource(R.drawable.ic_read_sound);
        lineLeft.setBackgroundResource(R.drawable.ic_line_off);
        lineRight.setBackgroundResource(R.drawable.ic_line_off);
        btnGetColor.setBackgroundResource(R.drawable.ic_read_color);
        textColor.setText("");
        textLight.setText("");
        textSrf05.setText("");
        shareFunction.getInstance().getData(define.NONE);
    }

    private View decorView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_control);
        decorView = getWindow().getDecorView();
        decorView.setOnSystemUiVisibilityChangeListener(new View.OnSystemUiVisibilityChangeListener() {
            @Override
            public void onSystemUiVisibilityChange(int visibility) {
                if (visibility == 0)
                    decorView.setSystemUiVisibility(hideSystemBars());
            }
        });

        layout_joystick = (RelativeLayout) findViewById(R.id.layout_joystick);

        btnDance = (ImageButton)findViewById(R.id.btnDanceMode);
        btnLed = (ImageButton) findViewById(R.id.btn_ledRGB);
        btnLedMatrix = (ImageButton) findViewById(R.id.btn_ledmatrix);
        btnRingLed = (ImageButton) findViewById(R.id.btn_ringled);
        backCtrBtn = (ImageButton) findViewById(R.id.btnCtrBack);
        btnConnect = (ImageButton) findViewById(R.id.btnConnectBluetooth);
        btnBuzzer = (ImageButton) findViewById(R.id.btn_buzzer);

        btnGetSrf05 = (ImageButton) findViewById(R.id.btnIconSrf05);
        btnGetColor = (ImageButton) findViewById(R.id.btnIconColor);
        btnGetLight = (ImageButton) findViewById(R.id.btnIconLight);
        btnGetLine = (ImageButton) findViewById(R.id.btnIconLine);
        btnGetBtn = (ImageButton) findViewById(R.id.btnIconButton);
        btnGetColor = (ImageButton) findViewById(R.id.btnIconColor);
        btnGetSound = (ImageButton) findViewById(R.id.btnIconSound);

        btnModeFollwingLine = (ImageButton) findViewById(R.id.btnLineMode);
        btnModeRunCircle = (ImageButton) findViewById(R.id.btnRoundMode);
        btnModeSoundDetect = (ImageButton) findViewById(R.id.btnSoundMode);
        btnModeSrf05 = (ImageButton) findViewById(R.id.btnSRF05Mode);

        showMode1 = (ImageButton) findViewById(R.id.btnMode1);
        showMode2 = (ImageButton) findViewById(R.id.btnMode2);
        showMode3 = (ImageButton) findViewById(R.id.btnMode3);

        textSrf05 = (TextView) findViewById(R.id.text_srf05);
        textSrf05.setTypeface(null, Typeface.BOLD);

        lineLeft = (TextView)findViewById(R.id.line_left);
        lineRight = (TextView)findViewById(R.id.line_right);

        textLight = (TextView) findViewById(R.id.text_light);
        textLight.setTypeface(null, Typeface.BOLD);

        textColor = (TextView) findViewById(R.id.text_color);
        textColor.setTypeface(null, Typeface.BOLD);

        textServo = (TextView) findViewById(R.id.val_servo);
        textServo.setTypeface(null, Typeface.BOLD);
        servo = (ArcSeekBar) findViewById(R.id.sbServo);

        textDC1 = (TextView) findViewById(R.id.val_dc1);
        textDC1.setTypeface(null, Typeface.BOLD);
        DC1 = (SeekBar) findViewById(R.id.sbDC1);

        textDC2 = (TextView) findViewById(R.id.val_dc2);
        textDC2.setTypeface(null, Typeface.BOLD);
        DC2 = (SeekBar) findViewById(R.id.sbDC2);
        soundSignal = (pl.droidsonroids.gif.GifImageView) findViewById(R.id.btnSound);
        magnitudeSpeed = findViewById(R.id.circlePr);
        check_connected();
//        String srf05Val = getIntent().getStringExtra("Srf05");
//        textSrf05.setText(srf05Val);

        /* Handle back button when clicked */
        backCtrBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Control.this, Play.class));
            }
        });
        mIntentFilter = new IntentFilter();
        mIntentFilter.addAction(mBroadcastGetData);
        /* Handle connect button when clicked*/
        btnConnect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                check_connected();
                Toast.makeText(Control.this,
                        "start",
                        Toast.LENGTH_LONG).show();
                startActivity(new Intent(Control.this, connectingBluetooth.class));
            }
        });

        btnDance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (int i = 0; i <= 255 ; i = i + 1 ) {
                    leftSpeed = -(255 - i);
                    rightSpeed = -(255 - i);
                    shareFunction.runJoystick(0, 0, 0, leftSpeed, rightSpeed);
                    for (int j = 0; j< 10000; j++) {
                        for (int k = 0; k < 2000; k++) {}
                    }
                }
            }
        });
        btnLed.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                switch (ledColor) {
                    case define.RED:
                        btnLed.setBackgroundResource(R.drawable.ic_rgb_red);
                        shareFunction.runRGB(0, 0, 0, define.RED_COLOR);
                        break;
                    case define.GREEN:
                        btnLed.setBackgroundResource(R.drawable.ic_rgb_green);
                        shareFunction.runRGB(0, 0, 0, define.GREEN_COLOR);
                        break;
                    case define.BLUE:
                        btnLed.setBackgroundResource(R.drawable.ic_rgb_blue);
                        shareFunction.runRGB(0, 0, 0, define.BLUE_COLOR);
                        break;
                    case define.YELLOW:
                        btnLed.setBackgroundResource(R.drawable.ic_rgb_yellow);
                        shareFunction.runRGB(0, 0, 0, define.YELLOW_COLOR);
                        break;
                    case define.PURPLE:
                        btnLed.setBackgroundResource(R.drawable.ic_rgb_purple);
                        shareFunction.runRGB(0, 0, 0, define.PURPLE_COLOR);
                        break;
                    case define.PINK:
                        btnLed.setBackgroundResource(R.drawable.ic_rgb_pink);
                        shareFunction.runRGB(0, 0, 0, define.PINK_COLOR);
                        break;
                    case define.WHITE:
                        btnLed.setBackgroundResource(R.drawable.ic_rgb_white);
                        shareFunction.runRGB(0, 0, 0, define.WHITE_COLOR);
                        break;
                    default:
                        btnLed.setBackgroundResource(R.drawable.ic_rgb_off);
                        shareFunction.runRGB(0, 0, 0, define.BLACK_COLOR);
                        break;
                }
                ledColor++;
                if (ledColor > define.WHITE)
                    ledColor = 0;
            }
        });

        btnRingLed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cnt_effect_ring++;
                if (cnt_effect_ring >= define.NUM_RING_EFFECT)
                    cnt_effect_ring = 0;
                shareFunction.runRingLed(0,0,0, define.ring_led_effect[cnt_effect_ring]);
            }
        });
        btnLedMatrix.setOnClickListener(new View.OnClickListener() {
            int duration = 1;

            @Override
            public void onClick(View v) {
                cnt_effect++;
                if (cnt_effect >= 16)
                    cnt_effect = 0;
                shareFunction.runMaTrix(0, 0, 0, define.motion_effect[cnt_effect], duration);
            }
        });
        btnBuzzer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (index >= 25)
                    index = 0;
                buzzerFreq = define.hpbdSong[index];
                buzzerDuration = 1;
                shareFunction.runBuzzer(0, 0, 0, buzzerFreq, buzzerDuration);
                index++;
            }
        });

        btnModeSrf05.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetBackground();
                resetState(stateRunSrf05);
                stateRunSrf05 = !stateRunSrf05;
                if (stateRunSrf05) {
                    define.cmdRunModule[5] = define.SRF05_RUN_MODE;
                    btnModeSrf05.setBackgroundResource(R.drawable.ic_srf05_mode_select);
                }
                else {
                    define.cmdRunModule[5] = define.NORMAL_MODE;
                }
            }
        });

        btnModeSoundDetect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetBackground();
                resetState(stateRunSoundMode);
                stateRunSoundMode = !stateRunSoundMode;
                if (stateRunSoundMode) {
                    define.cmdRunModule[5] = define.SOUND_FOLLOW_MODE;
                    btnModeSoundDetect.setBackgroundResource(R.drawable.ic_sound_mode_select);
                }
                else {
                    define.cmdRunModule[5] = define.NORMAL_MODE;
                }
            }
        });

        btnModeFollwingLine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetBackground();
                resetState(stateRunFollowingLine);
                stateRunFollowingLine = !stateRunFollowingLine;
                if (stateRunFollowingLine) {
                    define.cmdRunModule[5] = define.LINE_DETECT_MODE;
                    btnModeFollwingLine.setBackgroundResource(R.drawable.ic_line_mode_select);
                }
                else {
                    define.cmdRunModule[5] = define.NORMAL_MODE;
                }
            }
        });

        btnModeRunCircle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetBackground();
                resetState(stateRunInCircle);
                stateRunInCircle = !stateRunInCircle;
                if (stateRunInCircle) {
                    define.cmdRunModule[5] = define.LINE_CIRCLE_MODE;
                    btnModeRunCircle.setBackgroundResource(R.drawable.ic_round_mode_select);
                }
                else {
                    define.cmdRunModule[5] = define.NORMAL_MODE;
                }
            }
        });
        btnGetSrf05.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetBackground();
                resetState(stateGetSrf05);
                stateGetSrf05 = !stateGetSrf05;
                if (stateGetSrf05) {
                    shareFunction.getInstance().getData(define.SRF05);
                    btnGetSrf05.setBackgroundResource(R.drawable.ic_read_srf05_select);
                } else {
                    shareFunction.getInstance().getData(define.NONE);
                }
            }
        });

        btnGetLine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetBackground();
                resetState(stateGetLine);
                stateGetLine = !stateGetLine;
                if (stateGetLine) {
                    shareFunction.getInstance().getData(define.LINE);
                    btnGetLine.setBackgroundResource(R.drawable.ic_read_line_select);
                } else {
                    shareFunction.getInstance().getData(define.NONE);
                }
            }
        });
        btnGetLight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetBackground();
                resetState(stateGetLightSensor);
                stateGetLightSensor = !stateGetLightSensor;
                if (stateGetLightSensor) {
                    shareFunction.getInstance().getData(define.LIGHT);
                    btnGetLight.setBackgroundResource(R.drawable.ic_read_light_select);
                } else {
                    shareFunction.getInstance().getData(define.NONE);
                }
            }
        });
        btnGetBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetBackground();
                resetState(stateGetButton);
                stateGetButton = !stateGetButton;
                if (stateGetButton) {
                    shareFunction.getInstance().getData(define.MODE_BTN);
                    btnGetBtn.setBackgroundResource(R.drawable.ic_read_button_select);
                } else {
                    shareFunction.getInstance().getData(define.NONE);
                }
            }
        });
        btnGetSound.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetBackground();
                resetState(stateGetSound);
                stateGetSound = !stateGetSound;
                if (stateGetSound) {
                    shareFunction.getInstance().getData(define.SOUND);
                    btnGetSound.setBackgroundResource(R.drawable.ic_read_sound_select);
                }
                else {
                    shareFunction.getInstance().getData(define.NONE);
                }
            }
        });
        btnGetColor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetBackground();
                resetState(stateGetColor);
                stateGetColor = !stateGetColor;
                if (stateGetColor) {
                    shareFunction.getInstance().getData(define.COLOR);
                    btnGetColor.setBackgroundResource(R.drawable.ic_read_color_select);
                } else {
                    shareFunction.getInstance().getData(define.NONE);
                }
            }
        });
        if (!getPackageManager().hasSystemFeature(PackageManager.FEATURE_BLUETOOTH)) {
            Toast.makeText(Control.this,
                    "FEATURE_BLUETOOTH NOT support",
                    Toast.LENGTH_LONG).show();
            finish();
            return;
        }
//        servo.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
//            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
//                textServo.setText("Servo quay: " + progress + " độ");
//                shareFunction.runServo(0,0,0,progress);
//            }
//            public void onStartTrackingTouch(SeekBar seekBar) {
//                // TODO Auto-generated method stub
//            }
//
//            public void onStopTrackingTouch(SeekBar seekBar) {
//            }
//        });
        servo.setOnProgressChangedListener(new ProgressListener() {
            @Override
            public void invoke(int i) {
                textServo.setText("Servo quay: " + i + " độ");
            }
        });

        DC1.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                textDC1.setText("Tốc độ động cơ 1: " + (int)(progress/2.55) + "%");
                shareFunction.runJoystick(0, 0, 0, progress, 0);
            }

            public void onStartTrackingTouch(SeekBar seekBar) {
                // TODO Auto-generated method stub
            }

            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });

        DC2.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                textDC2.setText("Tốc độ động cơ 2: " + (int)(progress/2.55) + "%");
                shareFunction.runJoystick(0, 0, 0, 0, progress);
            }

            public void onStartTrackingTouch(SeekBar seekBar) {
                // TODO Auto-generated method stub
            }

            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });

        js = new joystick(getApplicationContext(), layout_joystick, R.drawable.joystick_center);
        int width = js.getLayoutWidth() / 3;
        int height = js.getStickHeight() / 3;
        js.setStickSize(width, width);
//        js.setLayoutSize(420, 420);
        js.setLayoutAlpha(255);
        js.setStickAlpha(255);
        js.setOffset(90);
        js.setMinimumDistance(20);

        layout_joystick.setOnTouchListener(new OnTouchListener() {
            public boolean onTouch(View arg0, MotionEvent arg1) {
                js.drawStick(arg1);
                if (arg1.getAction() == MotionEvent.ACTION_DOWN
                        || arg1.getAction() == MotionEvent.ACTION_MOVE) {
                    xPos = (int) js.getX();
                    if (xPos > 255)
                        xPos = 255;

                    yPos = (int) js.getY();
                    if (yPos > 255)
                        yPos = 255;

                    volumeSpeed = (int) js.getDistance();
                    if (volumeSpeed > 255)
                        volumeSpeed = 255;

                    int direction = js.get8Direction();
                    switch (direction) {
                        case joystick.STICK_UP: {
                            leftSpeed = volumeSpeed - yPos;
                            rightSpeed = -(volumeSpeed - yPos);
                            break;
                        }
                        case joystick.STICK_UPRIGHT: {
                            leftSpeed = volumeSpeed - (-1 * xPos);
                            rightSpeed = -volumeSpeed;
                            break;
                        }
                        case joystick.STICK_RIGHT: {
                            leftSpeed = volumeSpeed + xPos;
                            rightSpeed = volumeSpeed;
                            break;
                        }
                        case joystick.STICK_DOWNRIGHT: {
                            leftSpeed = -(volumeSpeed - (-1 * xPos));
                            rightSpeed = volumeSpeed;
                            break;
                        }
                        case joystick.STICK_DOWN: {
                            leftSpeed = -(volumeSpeed + yPos);
                            rightSpeed = volumeSpeed + yPos;
                            break;
                        }
                        case joystick.STICK_DOWNLEFT: {
                            leftSpeed = -volumeSpeed;
                            rightSpeed = (volumeSpeed - xPos);
                            break;
                        }
                        case joystick.STICK_LEFT: {
                            leftSpeed = -volumeSpeed;
                            rightSpeed = -(volumeSpeed - xPos);
                            break;
                        }
                        case joystick.STICK_UPLEFT: {
                            leftSpeed = volumeSpeed;
                            rightSpeed = -(volumeSpeed - xPos);
                            break;
                        }
                        case joystick.STICK_NONE: {
                            leftSpeed = 0;
                            rightSpeed = 0;
                            break;
                        }
                        default:
                            leftSpeed = 0;
                            rightSpeed = 0;
                            break;
                    }
                }
                if (arg1.getAction() == MotionEvent.ACTION_UP) {
                    leftSpeed = 0;
                    rightSpeed = 0;
                }
                shareFunction.runJoystick(0, 0, 0, leftSpeed, rightSpeed);
                if (leftSpeed >  rightSpeed)
                    value_speed = Math.abs(leftSpeed);
                else
                    value_speed = Math.abs(rightSpeed);

                magnitudeSpeed.setValue(value_speed);
                return true;
            }
        });

    }
    
    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus) {
            decorView.setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                            | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
            );
        }
    }
    private int hideSystemBars() {
        return    View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_FULLSCREEN
                | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY;
    }
    @Override
    protected void onStart() {
        super.onStart();
    }

    byte[] handleData(byte[] inBuffer) {
        byte[] bufGet = {0, 0, 0, 0};

        for (int i = 0; i < 4; i++) {
            bufGet[i] = inBuffer[i + 3];
        }
        return bufGet;
    }

    @Override
    public void onResume() {
        super.onResume();
        registerReceiver(mReceiver, mIntentFilter);
        Log.i("testRegistor", "onResume Control");
    }
    int state =  0;
    private BroadcastReceiver mReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            Charset charset = null;
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.KITKAT) {
                charset = StandardCharsets.ISO_8859_1;
            }
            state++;
            if (intent.getAction().equals(mBroadcastGetData)) {
                String inputData = intent.getStringExtra("fbData");
                byte[] buffer = inputData.getBytes(charset);
//                byte[] buffer1 = blueControl.getInstance().read();
//                final String msgReceived = String.format(String.format(String.format("%02X", buffer1[0]) + String.format("%02X", buffer1[1]) + String.format("%02X", buffer1[2])
//                        + String.format("%02X", buffer1[3]) + String.format("%02X", buffer1[4]) + String.format("%02X", buffer1[5])
//                        + String.format("%02X", buffer1[6]) + String.format("%02X", buffer1[7]) + String.format("%02X", buffer1[8])));
//                Log.i("mByte ", "++" + msgReceived);
                fbData = handleData(buffer);
                String displayText = Float.toString(shareFunction.byteArray2Float(fbData));
                switch (buffer[2]) {
                    case define.SRF05: {
                        if (stateGetSrf05) {
                            textSrf05.setText(displayText);
//                            textColor.setText("");
//                            textLight.setText("");
                        }
                        break;
                    }
                    case define.LINE: {
                        if (stateGetLine) {
//                            textSrf05.setText("");
//                            textColor.setText("");
//                            textLight.setText("");
                            switch ((int) shareFunction.byteArray2Float(fbData)) {
                                case define.ALL_ON:
                                    lineLeft.setBackgroundResource(R.drawable.ic_line_on);
                                    lineRight.setBackgroundResource(R.drawable.ic_line_on);
                                    break;
                                case define.LEFT_ON:
                                    lineLeft.setBackgroundResource(R.drawable.ic_line_on);
                                    lineRight.setBackgroundResource(R.drawable.ic_line_off);
                                    break;
                                case define.RIGHT_ON:
                                    lineLeft.setBackgroundResource(R.drawable.ic_line_off);
                                    lineRight.setBackgroundResource(R.drawable.ic_line_on);
                                    break;
                                default:
                                    lineLeft.setBackgroundResource(R.drawable.ic_line_off);
                                    lineRight.setBackgroundResource(R.drawable.ic_line_off);
                                    break;
                            }
                            break;
                        }
                    }
                    case define.LIGHT: {
                        if (stateGetLightSensor) {
                            textLight.setText(displayText + "%");
                        }
                        break;
                    }
                    case define.COLOR: {
                        if (stateGetColor) {
                            switch ((int) shareFunction.byteArray2Float(fbData)) {
                                case define.RED:
                                    btnGetColor.setBackgroundResource(R.drawable.ic_read_color_red);
                                    textColor.setText("Màu đỏ");
                                    break;
                                case define.GREEN:
                                    btnGetColor.setBackgroundResource(R.drawable.ic_read_color_green);
                                    textColor.setText("Màu xanh lá");
                                    break;
                                case define.BLUE:
                                    btnGetColor.setBackgroundResource(R.drawable.ic_read_color_blue);
                                    textColor.setText("Màu xanh lam");
                                    break;
                                case define.YELLOW:
                                    btnGetColor.setBackgroundResource(R.drawable.ic_read_color_yellow);
                                    textColor.setText("Màu vàng");
                                    break;
                                case define.WHITE:
                                    btnGetColor.setBackgroundResource(R.drawable.ic_read_color_white);
                                    textColor.setText("Màu trắng");
                                    break;
                                case define.BLACK:
                                    btnGetColor.setBackgroundResource(R.drawable.ic_read_color_black);
                                    textColor.setText("Màu đen");
                                    break;
                                default: {
                                    btnGetColor.setBackgroundResource(R.drawable.ic_read_color_select);
                                    textColor.setText("Màu ?");
                                    break;
                                }
                            }
                        }
                        break;
                    }
                    case define.MODE_BTN:{
                        if (stateGetButton) {
                            switch ((int) shareFunction.byteArray2Float(fbData)) {
                                case define.MODE_1:
                                    showMode1.setBackgroundResource(R.drawable.ic_mode2);
                                    showMode2.setBackgroundResource(R.drawable.ic_mode1);
                                    showMode3.setBackgroundResource(R.drawable.ic_mode1);
                                    break;
                                case define.MODE_2:
                                    showMode1.setBackgroundResource(R.drawable.ic_mode2);
                                    showMode2.setBackgroundResource(R.drawable.ic_mode2);
                                    showMode3.setBackgroundResource(R.drawable.ic_mode1);
                                    break;
                                case define.MODE_3:
                                    showMode1.setBackgroundResource(R.drawable.ic_mode2);
                                    showMode2.setBackgroundResource(R.drawable.ic_mode2);
                                    showMode3.setBackgroundResource(R.drawable.ic_mode2);
                                    break;
                                default:
                                    showMode1.setBackgroundResource(R.drawable.ic_mode1);
                                    showMode2.setBackgroundResource(R.drawable.ic_mode1);
                                    showMode3.setBackgroundResource(R.drawable.ic_mode1);
                                    break;
                            }
                        }
                        break;
                    }
                    case define.SOUND: {
                        if (stateGetSound) {
                            if (shareFunction.byteArray2Float(fbData) >= 1) {
                                soundSignal.setBackgroundResource(R.drawable.have_sound);
                            } else {
                                soundSignal.setBackgroundResource(R.drawable.ic_sound);
                            }
                        }
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
