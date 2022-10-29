//package com.example.eegaplikacija.services;
//
//import android.app.Service;
//import android.content.Intent;
//import android.media.MediaPlayer;
//import android.os.IBinder;
//
//import androidx.annotation.Nullable;
//
//import com.example.eegaplikacija.R;
//
//public class MyService extends Service implements MediaPlayer.OnPreparedListener {
//    private static final String ACTION_PLAY = "com.example.action.PLAY";
//    MediaPlayer mediaPlayer = null;
//
//    public int onStartCommand(Intent intent, int flags, int startId) {
//        if (intent.getAction().equals(ACTION_PLAY)) {
//            mediaPlayer = MediaPlayer.create(super.createContext(), R.raw.ringtone1);
//            mediaPlayer.setOnPreparedListener(this);
//            mediaPlayer.prepareAsync(); // prepare async to not block main thread
//        }
//    }
//
//    @Nullable
//    @Override
//    public IBinder onBind(Intent intent) {
//        return null;
//    }
//
//    /** Called when MediaPlayer is ready */
//    public void onPrepared(MediaPlayer player) {
//        player.start();
//    }
//}