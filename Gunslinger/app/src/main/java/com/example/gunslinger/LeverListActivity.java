package com.example.gunslinger;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;

public class LeverListActivity extends AppCompatActivity {

    ImageButton btnToMainMenu, btnLevelFirst;
    AudioPlayer audioPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lever_list);

        Window window = getWindow();
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        btnToMainMenu = findViewById(R.id.buttonToMainMenu);
        btnLevelFirst = findViewById(R.id.button_level_first);


        View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (view.getId()){
                    case R.id.buttonToMainMenu:
                        Intent intent1 = new Intent(LeverListActivity.this, MainMenu.class);
                        startActivity(intent1); finish(); audioPlayer.stop(); break;
                    case R.id.button_level_first:
                        Intent intent2 = new Intent(LeverListActivity.this, MainActivity.class);
                        startActivity(intent2); finish(); audioPlayer.stop(); break;
                }
            }
        };

        btnLevelFirst.setOnClickListener(onClickListener);
        btnToMainMenu.setOnClickListener(onClickListener);

        audioPlayer = new AudioPlayer(this, 1);
        audioPlayer.play();
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