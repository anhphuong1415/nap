package com.voblox.rangev1.Main;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.service.controls.templates.ControlButton;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.voblox.rangev1.R;
import com.voblox.rangev1.drapdropTask.DrapDropActivity;

public class MainActivity extends AppCompatActivity {
    public static final String EXTRA_MESSAGE = "com.example.Voblox.MESSAGE";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final Button mButton = findViewById(R.id.button);
    }

    public void onButtonClick(View view) {
        Toast.makeText(this, "BUTTON CLICK",Toast.LENGTH_LONG).show();
        Intent intent = new Intent(this, DrapDropActivity.class);
//        EditText editText = (EditText) findViewById(R.id.editTextTextPersonName);
//        String message = editText.getText().toString();
//        intent.putExtra(EXTRA_MESSAGE, message);
        startActivity(intent);
    }
}