package com.project.services.mp3project;

import android.media.MediaPlayer;
import android.util.Log;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by r028367 on 01/03/2017.
 */

public class PlayerMultimidiaInterface implements MultimidiaInterface, MediaPlayer.OnCompletionListener {

    public enum StatusPlayer {
        NOVO(1), TOCANDO(2), PAUSADO(3), PARADO(4);

        private int status;

        private static final Map<Integer, StatusPlayer> mapStatus = new HashMap<>();
        static {
            for(StatusPlayer stat : mapStatus.values()) {
                mapStatus.put(stat.getStatus(), stat);
            }
        }

        StatusPlayer(int status) {
            this.status = status;
        }

        public int getStatus() {
            return this.status;
        }

        public StatusPlayer getStatus(int status) {
            return mapStatus.get(status);
        }

    }

    private StatusPlayer statusPlayer;
    private MediaPlayer mediaPlayer;
    private String pathMp3File;

    public PlayerMultimidiaInterface() {
        this.statusPlayer = StatusPlayer.NOVO;
        mediaPlayer = new MediaPlayer();
        mediaPlayer.setOnCompletionListener(this);
    }

    @Override
    public void play(String fileMp3) {
        this.pathMp3File = fileMp3;
        try {
            switch (statusPlayer) {
                case NOVO:
                    mediaPlayer.setDataSource(fileMp3);
                    mediaPlayer.prepare();
                    break;

                case TOCANDO:
                    mediaPlayer.stop();
                    break;

                case PAUSADO:
                    mediaPlayer.start();
                    break;

                case PARADO:
                    mediaPlayer.reset();
                    break;
            }
        }
        catch (Exception e) {
            Log.e("PLAYER_MP#", e.getMessage());
        }
    }

    @Override
    public void pause() {
        mediaPlayer.pause();
        statusPlayer = StatusPlayer.PAUSADO;
    }

    @Override
    public void stop() {
        mediaPlayer.stop();
        statusPlayer = StatusPlayer.PARADO;
    }

    public void close() {
        stop();
        mediaPlayer.release();
        mediaPlayer = null;
    }

    @Override
    public boolean isPlaying() {
        boolean tocando = StatusPlayer.TOCANDO.equals(statusPlayer.getStatus());
        boolean pausado = StatusPlayer.PAUSADO.equals(statusPlayer.getStatus());
        return  tocando || pausado;
    }

    @Override
    public String pathFile() {
        return null;
    }

    @Override
    public void onCompletion(MediaPlayer mp) {
        Log.i("MP_COMPLETATION", mp.toString());
    }
}
