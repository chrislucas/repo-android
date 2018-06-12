package com.project.studyadvancedthread;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.ProgressBar;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ProgressBar progressBar = (ProgressBar) findViewById(R.id.progress_bar_image);
        ImageView imageView = (ImageView) findViewById(R.id.img_downloaded);
        TaskDownloadImage taskDownloadImage = new TaskDownloadImage(this, progressBar, imageView);
        String urls [] = {
            "https://www.facebook.com/rsrc.php/v3/yD/r/2wuGfVYB2an.png"
            ,"https://www.google.com.br/images/branding/googlelogo/2x/googlelogo_color_272x92dp.png"
            ,"http://st.codeforces.com/s/18867/images/codeforces-logo-with-telegram.png"
        };
        taskDownloadImage.execute(urls);
    }
}
