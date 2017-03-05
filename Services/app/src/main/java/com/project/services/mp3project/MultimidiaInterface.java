package com.project.services.mp3project;

import android.os.IBinder;

/**
 * Created by r028367 on 01/03/2017.
 */

public interface MultimidiaInterface extends IBinder{
    void play(String fileMp3);
    void pause();
    void stop();
    void close();
    boolean isPlaying();
    String pathFile();
}
