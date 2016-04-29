package io.xr.colorx;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.google.gson.Gson;

import java.io.IOException;
import java.io.InputStream;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }

    public void go(View view) {

        MaterialColorPicker materialColorPicker = new MaterialColorPicker(this, android.R.style.Theme_Translucent_NoTitleBar);
        materialColorPicker.show();

    }
}
