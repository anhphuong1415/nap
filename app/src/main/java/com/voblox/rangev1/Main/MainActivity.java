package com.voblox.rangev1.Main;

import androidx.appcompat.app.AppCompatActivity;
/*Bacpd1 is your friends*/
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;

import com.voblox.rangev1.Main.mechanical.Mechanical;
import com.voblox.rangev1.Main.play.Play;
import com.voblox.rangev1.R;
import com.voblox.rangev1.drapdropTask.DrapDropActivity;

public class MainActivity extends AppCompatActivity {
    private  ImageView imageMachanical, imagePlay, imageGuidle, imageProgram, imageExit;
    private View decorView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        decorView = getWindow().getDecorView();
        decorView.setOnSystemUiVisibilityChangeListener(new View.OnSystemUiVisibilityChangeListener() {
            @Override
            public void onSystemUiVisibilityChange(int visibility) {
                if (visibility == 0)
                    decorView.setSystemUiVisibility(hideSystemBars());
            }
        });

        imageMachanical = (ImageView)findViewById((R.id.btn_mechanical));
        imagePlay = (ImageView)findViewById((R.id.btn_play));
        imageProgram = (ImageView)findViewById((R.id.btn_program));
        imageGuidle = (ImageView)findViewById((R.id.btn_guidle));
        imageExit = (ImageView)findViewById((R.id.btn_exit));

        imageExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.this.finishAffinity();
                System.exit(1);
            }
        });
        imageGuidle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openGuidle();
            }
        });
        imageProgram.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openProgram();
            }
        });
        imageMachanical.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openMechanical();
            }
        });
        imagePlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openPlay();
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
    public void openMechanical() {
        Intent intent = new Intent(this, Mechanical.class);
        startActivity(intent);
    }
    public void openProgram() {
        Intent intent = new Intent(this, DrapDropActivity.class);
        startActivity(intent);
    }
    public void openPlay() {
        Intent intent = new Intent(this, Play.class);
        startActivity(intent);
    }
    public void openGuidle() {
//        Intent intent = new Intent(this, Guidle.class);
//        startActivity(intent);
    }

}