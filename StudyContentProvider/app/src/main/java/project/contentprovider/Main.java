package project.contentprovider;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;

import com.project.studycontentprovider.R;

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
    }
}
