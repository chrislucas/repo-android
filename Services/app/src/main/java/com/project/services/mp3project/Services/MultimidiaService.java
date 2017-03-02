package com.project.services.mp3project.Services;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

import com.project.services.mp3project.MultimidiaInterface;
import com.project.services.mp3project.PlayerMultimidiaInterface;

public class MultimidiaService extends Service implements MultimidiaInterface {
    public MultimidiaService() {
    }


    private PlayerMultimidiaInterface playerMp3;
    private String fileMp3;

    public class Mp3ServiceBinder extends Binder {
        public Mp3ServiceBinder() {}

        public MultimidiaInterface getInterfaceMp3() {
            return MultimidiaService.this;
        }
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        return new Mp3ServiceBinder();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        stop();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        playerMp3 = new PlayerMultimidiaInterface();
    }

    @Override
    public void play(String fileMp3) {
        this.fileMp3 = fileMp3;
        try {
            playerMp3.play(fileMp3);
        } catch (Exception e) {

        }
    }

    @Override
    public void pause() {
        playerMp3.pause();
    }

    @Override
    public void stop() {
        playerMp3.stop();
    }

    @Override
    public boolean isPlaying() {
        return playerMp3.isPlaying();
    }

    @Override
    public String pathFile() {
        return null;
    }
}
