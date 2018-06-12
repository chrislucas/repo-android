package com.project.services.mp3project;

import android.media.MediaPlayer;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import android.util.Log;

import java.io.FileDescriptor;
import java.io.IOException;
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
                    initializeMedia(fileMp3);
                    mediaPlayer.start();
                    this.statusPlayer = StatusPlayer.TOCANDO;
                    break;

                case TOCANDO:
                    mediaPlayer.stop();
                    break;

                case PAUSADO:
                    mediaPlayer.start();
                    this.statusPlayer = StatusPlayer.TOCANDO;
                    break;

                case PARADO:
                    mediaPlayer.reset();
                    initializeMedia(fileMp3);
                    mediaPlayer.start();
                    this.statusPlayer = StatusPlayer.TOCANDO;
                    break;
            }
        }
        catch (Exception e) {
            Log.e("PLAYER_MP#", e.getMessage());
        }

        mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                if(mp.isPlaying()) {

                }
            }
        });
    }


    private void initializeMedia(String file) {
        try {
            mediaPlayer.setDataSource(file);
            mediaPlayer.prepare();
        } catch (IOException e) {
            Log.e("PLAYER_MP", e.getMessage());
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

    @Override
    public void close() {
        stop();
        mediaPlayer.release();
        mediaPlayer = null;
    }

    @Override
    public boolean isPlaying() {
        // status do player eh tocando ?
        boolean tocando = StatusPlayer.TOCANDO.status == statusPlayer.getStatus();
        // se o status for pausado, quer dizer que nao foi
        boolean pausado = StatusPlayer.PAUSADO.status == statusPlayer.getStatus();
        return  tocando || pausado;
    }

    @Override
    public String pathFile() {
        return null;
    }

    @Override
    public void onCompletion(MediaPlayer mp) {
        Log.i("MP_COMPLETATION", String.valueOf(mp.isPlaying()));
    }

    @Override
    public String getInterfaceDescriptor() throws RemoteException {
        return null;
    }

    @Override
    public boolean pingBinder() {
        return false;
    }

    @Override
    public boolean isBinderAlive() {
        return false;
    }

    @Override
    public IInterface queryLocalInterface(String descriptor) {
        return null;
    }

    @Override
    public void dump(FileDescriptor fd, String[] args) throws RemoteException {

    }

    @Override
    public void dumpAsync(FileDescriptor fd, String[] args) throws RemoteException {

    }

    @Override
    public boolean transact(int code, Parcel data, Parcel reply, int flags) throws RemoteException {
        return false;
    }

    @Override
    public void linkToDeath(DeathRecipient recipient, int flags) throws RemoteException {

    }

    @Override
    public boolean unlinkToDeath(DeathRecipient recipient, int flags) {
        return false;
    }
}
