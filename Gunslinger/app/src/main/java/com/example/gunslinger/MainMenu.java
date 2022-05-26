package com.example.gunslinger;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.Toast;

public class MainMenu extends AppCompatActivity {

    ImageButton exitButton, playButton, settingsButton;
    Button noDialog_btn, yesDialog_btn;
    private long buttonPressedTime;
    private Toast toastToExit;
    Dialog dialogWindow;
    AudioPlayer audioPlayer;
    SettingsFragment settingsFragment;
    FragmentManager fragmentManager;
    boolean checkFragment = false;
    int checkPlayMusic, checkPlayMusicMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        exitButton = findViewById(R.id.button_for_exit);
        playButton = findViewById(R.id.button_for_play);
        settingsButton = findViewById(R.id.settings_button);

        dialogWindow = new Dialog(this);
        dialogWindow.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialogWindow.setContentView(R.layout.dialog_preview);
        dialogWindow.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialogWindow.setCancelable(false);

        noDialog_btn = dialogWindow.findViewById(R.id.dialog_window_no_button);
        yesDialog_btn = dialogWindow.findViewById(R.id.dialog_window_yes_btn);

        audioPlayer = new AudioPlayer(MainMenu.this, 0);

        settingsFragment = new SettingsFragment();
        fragmentManager = getSupportFragmentManager();

        View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (view.getId()){
                    case R.id.button_for_exit:
                        dialogWindow.show(); break;

                    case R.id.button_for_play:
                        Intent intent = new Intent(MainMenu.this, LeverListActivity.class);
                        startActivity(intent); finish(); audioPlayer.stop(); break;

                    case R.id.dialog_window_yes_btn:
                        MainMenu.super.onBackPressed(); break;

                    case R.id.dialog_window_no_button:
                        dialogWindow.dismiss(); break;

                    case R.id.settings_button:
                        if (!checkFragment) {
                            exitButton.setClickable(false);
                            playButton.setClickable(false);
                            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                            fragmentTransaction.add(R.id.container, settingsFragment);
                            fragmentTransaction.commit();
                            checkFragment = true;
                        } else {
                            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                            fragmentTransaction.remove(settingsFragment);
                            fragmentTransaction.commit();
                            playButton.setClickable(true);
                            exitButton.setClickable(true);
                            checkFragment = false;
                        }
                }
            }
        };

        exitButton.setOnClickListener(onClickListener);
        playButton.setOnClickListener(onClickListener);
        noDialog_btn.setOnClickListener(onClickListener);
        yesDialog_btn.setOnClickListener(onClickListener);
        settingsButton.setOnClickListener(onClickListener);

        Window window = getWindow();
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        checkPlayMusic = getIntent().getIntExtra("INT_CHECK_PLAY_MUSIC", 0);

        audioPlayer = new AudioPlayer(this, 0);

        if (checkPlayMusic == 1){
            audioPlayer.play();
        } else {
            audioPlayer.stop();
        }

    }


    @Override
    public void onBackPressed() {
        if (buttonPressedTime + 2000 > System.currentTimeMillis()){
            toastToExit.cancel();
            super.onBackPressed();
            return;
        } else {
            toastToExit = Toast.makeText(getBaseContext(), "Нажмите еще раз, чтобы выйти.", Toast.LENGTH_SHORT);
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