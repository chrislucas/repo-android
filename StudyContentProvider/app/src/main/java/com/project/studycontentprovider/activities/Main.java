package com.project.studycontentprovider.activities;

import android.Manifest;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;

import java.util.ArrayList;

import com.project.studycontentprovider.R;
import com.project.studycontentprovider.contentprovider.ContactsContentProvider;
import com.project.studycontentprovider.contentprovider.TelephonyContentProvider;
import com.project.studycontentprovider.utils.PermissionsUtils;

public class Main extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        verifyPermissions();
    }


    private void M() {
        TelephonyContentProvider.explore(this);
    }

    private void T() {
        ContactsContentProvider.exploreContactsContractClass(this);
    }

    private void R() {
        ContactsContentProvider.exploreContactsTable(this);
    }


    private void S() {
        ContactsContentProvider.exploreFilterContactTable(this);
    }

    private void U() {
        // Testando o metodo setTransformationMethod http://stackoverflow.com/questions/3685790/how-to-switch-between-hide-and-view-password
        /**
         * TransformationMethod
         * https://developer.android.com/reference/android/text/method/TransformationMethod.html
         * */
        final EditText editText = (EditText) findViewById(R.id.input_password_2);
        CheckBox checkBox = (CheckBox) findViewById(R.id.check_toggle_pwd);
        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                int start = editText.getSelectionStart(), end = editText.getSelectionEnd();
                Log.i("OnCheckedChangeListener", String.format("(%d, %d)", start, end));
                if(isChecked) {
                    editText.setTransformationMethod(null);
                }
                else {
                    editText.setTransformationMethod(new PasswordTransformationMethod());
                }
                editText.setSelection(start, end);
            }
        });
    }

    /**
     * As mensagens de erro estao cada vez mais humanas, ora ora
     * java.lang.IllegalArgumentException: Can only use lower 16 bits for requestCode
     * */
    private static final int REQUEST_CODE_PERMISSIONS = 0x0f;
    public void verifyPermissions() {
        ArrayList<String> permissions = new ArrayList<>();
        permissions.add(Manifest.permission.READ_CONTACTS);
        permissions.add(Manifest.permission.WRITE_CONTACTS);
        permissions.add(Manifest.permission.WRITE_CALENDAR);
        permissions.add(Manifest.permission.READ_CALENDAR);
        PermissionsUtils.validatePermission(permissions, this, REQUEST_CODE_PERMISSIONS);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode == REQUEST_CODE_PERMISSIONS) {
            if(permissions.length > 0 && grantResults.length > 0) {
                if(permissions.length == grantResults.length ) {
                    //for (String permission : permissions) {}
                }
                if(grantResults[0] == PackageManager.PERMISSION_GRANTED) {}
            }
        }
    }
}
