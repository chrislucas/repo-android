package com.project.importdb;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.drawable.AnimationDrawable;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Environment;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.LinearLayout;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private Map<String, List<String>> fileTree;
    private AnimationDrawable animationDrawable;

    private LinearLayout container;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        container = (LinearLayout) findViewById(R.id.activity_main);
        animationDrawable = (AnimationDrawable) container.getBackground();
        animationDrawable.setEnterFadeDuration(3000);
        animationDrawable.setExitFadeDuration(1500);

        fileTree = new HashMap<>();
        new AsyncTask<Void, Void, Void>() {
            /**
             * Override this method to perform a computation on a background thread. The
             * specified parameters are the parameters passed to {@link #execute}
             * by the caller of this task.
             * <p>
             * This method can call {@link #publishProgress} to publish updates
             * on the UI thread.
             *
             * @param params The parameters of the task.
             * @return A result, defined by the subclass of this task.
             * @see #onPreExecute()
             * @see #onPostExecute
             * @see #publishProgress
             */
            @Override
            protected Void doInBackground(Void... params) {
                readAllFiles();
                return null;
            }
        }.execute();

    }

    @Override
    protected void onResume() {
        super.onResume();
        if(animationDrawable != null && ! animationDrawable.isRunning())
            animationDrawable.start();
    }

    @Override
    protected void onPause() {
        super.onPause();
        if(animationDrawable != null && animationDrawable.isRunning())
            animationDrawable.stop();
    }

    private void requestPermissions() {
        List<String> listPermissionsDenied = new ArrayList<>();
        String permissions [] = {
             Manifest.permission.READ_EXTERNAL_STORAGE
            ,Manifest.permission.WRITE_EXTERNAL_STORAGE
        };

        for(String permission : permissions) {
            boolean denied = ContextCompat.checkSelfPermission(this, permission) == PackageManager.PERMISSION_DENIED;
            if(denied) {
                listPermissionsDenied.add(permission);
            }
            boolean shouldIhaveRequestPermission = ActivityCompat
                    .shouldShowRequestPermissionRationale(this, permission);

        }
        if(listPermissionsDenied.size() == 0) {
            readAllFiles();
        }
        else {
            String buffer [] = new String[listPermissionsDenied.size()];
            listPermissionsDenied.toArray(buffer);
            ActivityCompat.requestPermissions(this, buffer, 0x03);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode == 0x03) {
            if(permissions != null && grantResults != null) {
                if( grantResults.length > 0 && (grantResults[0] == PackageManager.PERMISSION_GRANTED) ) {
                    readAllFiles();
                }
                //for(int i=0; i<grantResults.length; i++) {}
            }
        }
    }

    private void readAllFiles() {
        navigation(new File("/data/user/"));
        navigation(new File("/data/user/0"));
        navigation(new File("/data/data/com.example.r028367.appicomon"));
        navigation(new File("/sdcard/"));
        navigation(new File("/data/data/"));
        navigation(new File("/data/local/tmp/"));
        File sd         = Environment.getExternalStorageDirectory();
        File data       = Environment.getDataDirectory();
        Log.i("ENVIROMENT_DATA", String.format("SD %s\n DATA %s\n", data.getPath(), data.getPath()));
        Log.i("ENVIROMENT_SD", String.format("SD %s\n DATA %s\n", sd.getAbsoluteFile(), data.getAbsoluteFile()));
        navigation(data);
        navigation(sd);
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            String locals [] = {
                 Environment.DIRECTORY_DOCUMENTS
                ,Environment.DIRECTORY_ALARMS
                ,Environment.DIRECTORY_DOWNLOADS
                ,Environment.DIRECTORY_DCIM
            };
            for(String local : locals) {
                File sdPublic = Environment.getExternalStoragePublicDirectory(local);
                boolean isAvaialble = Environment.getExternalStorageState(sdPublic).equals(Environment.MEDIA_MOUNTED);
                if(isAvaialble) {
                    Log.i("ENVIROMENT_SD_PUBLIC", String.format("SD %s\n DATA %s\n", sdPublic.getAbsoluteFile(), sdPublic.getAbsoluteFile()));
                    navigation(sdPublic);
                }
            }
        }
    }

    private void navigation(File node)  {
        if(node != null && node.isDirectory()) {
            Log.i("DIRECTORY", node.getName());
            if(fileTree.containsKey(node.getName())) {
                fileTree.get(node.getName()).add(node.getPath());
            }
            else {
                ArrayList<String> list = new ArrayList<>();
                list.add(node.getPath());
                fileTree.put(node.getName(), list);
            }
            //String leafs [] = node.list();
            File[] leafs  = node.listFiles();
            if(leafs != null) {
                for(File leaf : leafs) {
                    if(leaf.getName().contains(".db")) {
                        Log.i("DB", String.format("%s %s", leaf.getName(), leaf.getAbsoluteFile()));
                        String content = getContentFile(leaf);
                        writeFileExternalStorage(leaf.getName(), content);
                    }
                    Log.i( leaf.isDirectory() ? "SUB_DIRECTORY" : "FILE", leaf.getName());
                    navigation(leaf);
                }
            }
        }
    }

    private String getContentFile(File file) {
        StringBuilder content = new StringBuilder();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String in = null;
            while( ( in = reader.readLine() ) != null ) {
                content.append(in);
            }
        } catch (Exception e) {
            Log.e("READ_FILE_EXCP", e.getMessage());
        }
        return content.toString();
    }

    private String copyContentFile(File origin, File destiny) {
        FileChannel channelOrigin = null, channelDestiny = null;
        try {
            channelOrigin = new FileInputStream(origin).getChannel();
            channelDestiny = new FileOutputStream(destiny).getChannel();
            channelDestiny.transferFrom(channelOrigin, 0, channelOrigin.size());
            return channelDestiny.toString();
        } catch (Exception e) {
            Log.e("EXCP_COPY_CONTENT_FILE", e.getMessage());
        }
        finally {
            if(channelOrigin != null) {
                try {
                    channelOrigin.close();
                } catch (IOException e) {
                }
            }
             if(channelDestiny != null) {
                 try {
                     channelDestiny.close();
                 } catch (IOException e) {
                 }
             }

        }
        return null;
    }

    private void writeFileInternalStorage(String fileName, String content) {
        try {
            FileOutputStream fileOutputStream = openFileOutput("/data/" + fileName, Context.MODE_APPEND);
            if(fileOutputStream != null) {
                fileOutputStream.write(content.getBytes());
                fileOutputStream.close();
            }
        } catch (Exception e) {
            Log.e("W_INTERNAL_FILE_EXCP", e.getMessage());
        }
    }

    private void writeFileExternalStorage(String filename, String content) {
        boolean isMounted = Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED);
        if(isMounted) {
            try {
                File file = null;
                if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                    file = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
                }
                else {
                    file = Environment.getExternalStorageDirectory();
                }
                String path     = file.getAbsolutePath().concat(File.separator).concat("backup");
                File folder     = new File(path);
                File fileBackup = new File(folder, filename);
                if( ! folder.exists() ) {
                    if(folder.mkdirs())
                        writer(fileBackup, content);
                }
                else {
                    writer(fileBackup, content);
                }

            } catch (Exception e) {
                Log.e("W_INTERNAL_FILE_EXCP", e.getMessage());
            }
        }
    }

    private void writer(File file, String content) throws IOException {
        FileOutputStream fileOutputStream = new FileOutputStream(file);
        if(fileOutputStream != null) {
            fileOutputStream.write(content.getBytes());
            fileOutputStream.close();
        }
    }

    private void searchFile(File node, String fileName) {
        if(node != null) {
            if( node.isDirectory() ) {
                Log.i("DIRECTORY", node.getName());
                if(fileTree.containsKey(node.getName())) {
                    fileTree.get(node.getName()).add(node.getPath());
                }
                else {
                    ArrayList<String> list = new ArrayList<>();
                    list.add(node.getPath());
                    fileTree.put(node.getName(), list);
                }
                //String leafs [] = node.list();
                File[] leafs  = node.listFiles();
                if(leafs != null) {
                    for(File leaf : leafs) {
                        if(leaf.getName().contains(".db")) {
                            Log.i("DB", leaf.getName());
                        }
                        Log.i( leaf.isDirectory() ? "SUB_DIRECTORY" : "FILE", leaf.getName());
                        searchFile(leaf, fileName);
                    }
                }
            }
            else {
                if(node.equals(fileName)) {
                    return;
                }
            }
        }
    }

    public boolean isExternalStorageWritable() {
        return Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState());
    }

    public boolean isExternalStorageReadable() {
        String state = Environment.getExternalStorageState();
        return Environment.MEDIA_MOUNTED.equals(state) || Environment.MEDIA_MOUNTED_READ_ONLY.equals(state);
    }

    /**
     * https://docs.oracle.com/javase/tutorial/essential/io/walk.html
     * */
}
