package com.example.simplesoundboard;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Build;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.View;
import com.example.simplesoundboard.R.id;

public class MainActivity extends AppCompatActivity{

    private SoundPool soundPool;
    private int sound1, sound2, sound3, sound4, sound5, sound6;

    private Vibrator vibrator;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);

        // old way is use constructor
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){

            AudioAttributes audioAttributes = new AudioAttributes.Builder()
                    .setUsage(AudioAttributes.USAGE_ASSISTANCE_SONIFICATION)
                    .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
                    .build();

            soundPool = new SoundPool.Builder().setMaxStreams(6)
                    .setAudioAttributes(audioAttributes).build();

        }

        else
            soundPool = new SoundPool(6, AudioManager.STREAM_MUSIC, 0);

        sound1 = soundPool.load(this, R.raw.sound1, 1);
        sound2 = soundPool.load(this, R.raw.sound2, 1);
        sound3 = soundPool.load(this, R.raw.sound3, 1);
        sound4 = soundPool.load(this, R.raw.sound4, 1);
        sound5 = soundPool.load(this, R.raw.sound5, 1);
        sound6 = soundPool.load(this, R.raw.sound6, 1);

    }

    public void playSound(View view){

        if(vibrator != null)
            vibrator.vibrate(100);

        int soundId = 0;

        if(view.getId() == id.button_sound1)
            soundId = sound1;

        else if(view.getId() == id.button_sound2)
            soundId = sound2;

        else if(view.getId() == id.button_sound3)
            soundId = sound3;

        else if(view.getId() == id.button_sound4)
            soundId = sound4;

        else if(view.getId() == id.button_sound5)
            soundId = sound5;

        else if(view.getId() == id.button_sound6)
            soundId = sound6;

        soundPool.play(soundId, 1, 1, 0, 0, 1);

    }

    @Override
    protected void onDestroy(){

        super.onDestroy();
        soundPool.release();
        soundPool = null;

    }

}