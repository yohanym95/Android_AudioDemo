package com.example.yohan.audiodemo;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.SeekBar;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {
    MediaPlayer mplayer;
    AudioManager audioManager;
    

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mplayer =  MediaPlayer.create(this,R.raw.laugh);
        audioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
        int max = audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
        int current = audioManager.getStreamVolume(AudioManager.STREAM_MUSIC);
        SeekBar control = findViewById(R.id.seekBar);
        control.setMax(max);
        control.setProgress(current);

        control.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
          //   Log.i("SeekBar value",Integer.toString(progress))  ;
              audioManager.setStreamVolume(AudioManager.STREAM_MUSIC,progress,0);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }

        });


      final    SeekBar controlAudio = findViewById(R.id.seekBar2);

        controlAudio.setMax(mplayer.getDuration());
        new Timer().scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                controlAudio.setProgress(mplayer.getCurrentPosition());
           }
       },0,1000);


        controlAudio.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
            //  Log.i("Seekbar value",Integer.toString(progress));
            mplayer.seekTo(progress);

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

    }

    public void playaudio(View view) {
        mplayer =  MediaPlayer.create(this,R.raw.laugh);
        mplayer.start();


    }

    public void pauseaudio(View view) {


        mplayer.pause();

    }
}
