package com.project.services.mp3project.Services;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import android.support.v4.app.BundleCompat;
import android.util.Log;

import com.project.services.mp3project.MultimidiaInterface;
import com.project.services.mp3project.PlayerActivity;
import com.project.services.mp3project.PlayerMultimidiaInterface;

import java.io.FileDescriptor;

public class MultimidiaService extends Service implements MultimidiaInterface {
    public MultimidiaService() {
    }

    private MultimidiaInterface playerMp3;
    private String fileMp3;

    public class Mp3ServiceBinder extends Binder {
        public Mp3ServiceBinder() {}

        public Mp3ServiceBinder(MultimidiaInterface multimidiaInterface) {
            MultimidiaService.this.playerMp3 = multimidiaInterface;
        }

        public MultimidiaInterface getInterfaceMp3() {
            return MultimidiaService.this;
        }
    }

    @Override
    public IBinder onBind(Intent intent) {

        if(intent != null) {
            Bundle bundle = intent.getExtras();
            if(bundle != null) {
                IBinder binder = BundleCompat.getBinder(bundle, PlayerActivity.BIND_PLAYER);
            }
        }
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
            Log.e("EXCEPTION_PLAY", e.getMessage());
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
    public void close() {
        stop();
    }

    @Override
    public boolean isPlaying() {
        return playerMp3.isPlaying();
    }

    @Override
    public String pathFile() {
        return fileMp3;
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
