package com.example.gunslinger;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Bundle;

import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;


public class MainActivity extends AppCompatActivity {

    AudioPlayer audioPlayer;
    private long buttonPressedTime;
    private Toast toastToExit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new GameMap(this));

        Window window = getWindow();
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        audioPlayer = new AudioPlayer(this, 2);
        audioPlayer.play();

    }

    @Override
    public void onBackPressed() {
        if (buttonPressedTime + 2000 > System.currentTimeMillis()){
            toastToExit.cancel();
            Intent intent = new Intent(MainActivity.this, LeverListActivity.class);
            startActivity(intent);
            finish();
        } else {
            toastToExit = Toast.makeText(getBaseContext(), "Нажмите еще раз, чтобы выбрать уровень.", Toast.LENGTH_SHORT);
            toastToExit.show();
        }
        buttonPressedTime = System.currentTimeMillis();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        audioPlayer.stop();
    }

    @Override
    protected void onStop() {
        super.onStop();
        audioPlayer.stop();
    }

    @Override
    protected void onPause() {
        super.onPause();
        audioPlayer.pause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        audioPlayer.play();
    }

}