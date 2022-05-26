package com.example.gunslinger;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class SettingsFragment extends Fragment {

    CheckBox checkBoxMusic;
    int checkPlayMusicFragment;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.settings_window, null);

       checkBoxMusic = view.findViewById(R.id.checkBox_music);

       checkBoxMusic.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
           @Override
           public void onCheckedChanged(CompoundButton compoundButton, boolean bool) {
               checkPlayMusicFragment = (bool == true? 1:0);
               Intent intent = new Intent(getContext(), MainMenu.class);
               intent.putExtra("INT_CHECK_PLAY_MUSIC", checkPlayMusicFragment);
           }
       });

        return view;
    }
}
