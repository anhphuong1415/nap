package com.voblox.rangev1.Main.play;

import androidx.appcompat.app.AppCompatActivity;
import com.voblox.rangev1.Utilities.classicBluetooth.LocalBinder;
import com.voblox.rangev1.Utilities.classicBluetooth;
import com.voblox.rangev1.Utilities.shareFunction;
import com.voblox.rangev1.Utilities.define;
import com.voblox.rangev1.Utilities.connectingBluetooth;
import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.ActivityInfo;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.view.VerifiedInputEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.Toast;

import java.util.Set;
import java.util.Timer;
import java.util.TimerTask;
import com.voblox.rangev1.R;

public class Music extends AppCompatActivity {
    ImageButton MusicBackBtn, musicConnectBluetooth;
    ImageButton btnMCSong, btnHpbd_song, btnChickenSong, btnStarSong;
    View c_s, d_s, e_s, f_s, g_s, a_s, b_s;
    View c_d_s, d_e_s, f_g_s, g_a_s, a_b_s;
    BluetoothAdapter bluetoothAdapter;
    private static final int REQUEST_ENABLE_BT = 1;
    Timer timer;
    int index = 0;
    int buzzerFreq = 0;

    private void check_connected() {
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                try {
                    if (shareFunction.getStateConnectBluetooth()) {
                        musicConnectBluetooth.setBackgroundResource(R.drawable.ic_ble_on);
//                        timer.cancel();
                    } else {
                        musicConnectBluetooth.setBackgroundResource(R.drawable.ic_ble_off);
                    }
                } catch (NullPointerException ex) {
                }
            }
        };
        if (timer != null)
            timer.cancel();
        timer = new Timer("Timer");
        timer.schedule(timerTask, 0, 2000);
    }
    private View decorView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music);
        decorView = getWindow().getDecorView();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            decorView.setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                            | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
            );
        }
        decorView.setOnSystemUiVisibilityChangeListener(new View.OnSystemUiVisibilityChangeListener() {
            @Override
            public void onSystemUiVisibilityChange(int visibility) {
                if (visibility == 0)
                    decorView.setSystemUiVisibility(hideSystemBars());
            }
        });

        MusicBackBtn = (ImageButton) findViewById(R.id.btnMusicBack);
        musicConnectBluetooth = (ImageButton)findViewById(R.id.btnConnectBluetooth);
        btnMCSong = (ImageButton) findViewById(R.id.btn_merry_chrismas);
        btnHpbd_song = (ImageButton) findViewById(R.id.btn_hpbd);
        btnChickenSong = (ImageButton) findViewById(R.id.btn_chicken);
        btnStarSong = (ImageButton) findViewById(R.id.btn_star);
        c_s = (View)findViewById(R.id.c_sound);
        d_s = (View)findViewById(R.id.d_sound);
        e_s = (View)findViewById(R.id.e_sound);
        f_s = (View)findViewById(R.id.f_sound);
        g_s = (View)findViewById(R.id.g_sound);
        a_s = (View)findViewById(R.id.a_sound);
        b_s = (View)findViewById(R.id.b_sound);

        c_d_s = (View)findViewById(R.id.c_d_sound);
        d_e_s = (View)findViewById(R.id.d_e_sound);
        f_g_s = (View)findViewById(R.id.f_g_sound);
        g_a_s = (View)findViewById(R.id.g_a_sound);
        a_b_s = (View)findViewById(R.id.a_b_sound);

        check_connected();

        MusicBackBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Music.this, Play.class));
            }
        });

        /* Handle connect button when clicked*/
        musicConnectBluetooth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                check_connected();
                startActivity(new Intent(Music.this, connectingBluetooth.class));

            }
        });
        c_s.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                shareFunction.runBuzzer(0,0,0, define.C, 1);
            }
        });
        d_s.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                shareFunction.runBuzzer(0,0,0, define.D, 1);
            }
        });
        e_s.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                shareFunction.runBuzzer(0,0,0, define.E, 1);
            }
        });
        f_s.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                shareFunction.runBuzzer(0,0,0, define.F, 1);
            }
        });
        g_s.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                shareFunction.runBuzzer(0,0,0, define.G, 1);
            }
        });
        a_s.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                shareFunction.runBuzzer(0,0,0, define.A, 1);
            }
        });
        b_s.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                shareFunction.runBuzzer(0,0,0, define.B, 1);
            }
        });
        c_d_s.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                shareFunction.runBuzzer(0,0,0, define.C_D, 1);
            }
        });
        d_e_s.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                shareFunction.runBuzzer(0,0,0, define.D_E, 1);
            }
        });
        f_g_s.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                shareFunction.runBuzzer(0,0,0, define.F_G, 1);
            }
        });
        g_a_s.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                shareFunction.runBuzzer(0,0,0, define.G_A, 1);
            }
        });
        a_b_s.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                shareFunction.runBuzzer(0,0,0, define.A_B, 1);
            }
        });
        btnMCSong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (index = 0; index < 44; index++) {
                    buzzerFreq = define.merryChrimasSong[index];
                    switch (index) {
                        case 2:
                        case 5:
                        case 20:
                        case 21:
                        case 24:
                        case 27:
                            shareFunction.runBuzzer(0, 0, 0, buzzerFreq, 2);
                            break;
                        case 10:
                        case 32:
                        case 43:
                            shareFunction.runBuzzer(0, 0, 0, buzzerFreq, 4);
                            break;
                        default:
                            shareFunction.runBuzzer(0, 0, 0, buzzerFreq, 1);
                            break;
                    }
                    shareFunction.delay_ms(300);
                }
            }
        });

        btnHpbd_song.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (index = 0; index < 25; index++) {
                    buzzerFreq = define.hpbdSong[index];
                    switch (index) {
                        case 2:
                        case 5:
                        case 8:
                        case 11:
                        case 15:
                        case 19:
                            shareFunction.runBuzzer(0, 0, 0, buzzerFreq, 2);
                            break;
                        case 3:
                        case 9:
                        case 14:
                        case 18:
                        case 22:
                            shareFunction.runBuzzer(0, 0, 0, buzzerFreq, 3);
                            break;
                        case 24:
                            shareFunction.runBuzzer(0, 0, 0, buzzerFreq, 4);
                            break;
                        default:
                            shareFunction.runBuzzer(0, 0, 0, buzzerFreq, 1);
                            break;
                    }
                    shareFunction.delay_ms(300);
                }
            }
        });
        btnChickenSong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (index = 0; index < 41; index++) {
                    buzzerFreq = define.chickenSong[index];
                    switch (index) {
                        case 8:
                        case 9:
                        case 18:
                        case 19:
                        case 30:
                        case 31:
                        case 39:
                        case 40:
                            shareFunction.runBuzzer(0, 0, 0, buzzerFreq, 3);
                            break;
                        case 20:
                            shareFunction.runBuzzer(0, 0, 0, buzzerFreq, 2);
                            break;
                        default:
                            shareFunction.runBuzzer(0, 0, 0, buzzerFreq, 1);
                            break;
                    }
                    shareFunction.delay_ms(300);
                }
            }
        });
        btnStarSong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (index = 0; index < 34; index++) {
                    buzzerFreq = define.starLightSong[index];
                    switch (index) {
                        case 2:
                        case 6:
                        case 10:
                        case 18:
                        case 19:
                        case 31:
                        case 24:
                        case 29:
                        case 3:
                        case 7:
                        case 11:
                        case 16:
                        case 20:
                        case 25:
                        case 30:
                            shareFunction.runBuzzer(0, 0, 0, buzzerFreq, 2);
                            break;
                        case 33:
                            shareFunction.runBuzzer(0, 0, 0, buzzerFreq, 3);
                            break;
                        default:
                            shareFunction.runBuzzer(0, 0, 0, buzzerFreq, 1);
                            break;
                    }
                    shareFunction.delay_ms(300);
                }
            }
        });
    }

    private int hideSystemBars() {
        return    View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_FULLSCREEN
                | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY;
    }
}

