package com.project.services.mp3project;

/**
 * Created by r028367 on 01/03/2017.
 */

public interface MultimidiaInterface {
    void play(String fileMp3);
    void pause();
    void stop();
    boolean isPlaying();
    String pathFile();
}
