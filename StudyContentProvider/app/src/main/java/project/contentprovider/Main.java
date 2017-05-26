package project.contentprovider;

import android.Manifest;
import android.content.pm.PackageManager;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;

import com.project.studycontentprovider.R;

import java.util.ArrayList;
import java.util.Calendar;

import project.contentprovider.contentprovider.ContactsContentProvider;
import project.contentprovider.contentprovider.TelephonyContentProvider;
import project.contentprovider.utils.PermissionsUtils;

public class Main extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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
        verifyPermissions();
        //ContactsContentProvider.exploreContactsContractClass(this);
        //ContactsContentProvider.exploreContactsTable(this);
        //ContactsContentProvider.exploreFilterContactTable(this);
        TelephonyContentProvider.explore(this);
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
            if(permissions != null && grantResults != null && permissions.length > 0 && grantResults.length > 0) {
                if(permissions.length == grantResults.length ) {
                    for(int i=0; i<permissions.length; i++) {

                    }
                }

                if(grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                }
            }
        }

    }
}
