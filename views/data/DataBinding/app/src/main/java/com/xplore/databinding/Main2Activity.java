package com.xplore.databinding;


import android.databinding.DataBindingUtil;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.xplore.databinding.databinding.ActivityMain2DatabindingBinding;
import com.xplore.databinding.models.BaseObservableUser;
import com.xplore.databinding.models.ObservableUser;

public class Main2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final ObservableUser user = new ObservableUser("Chris", "Lucas", 27);
        ActivityMain2DatabindingBinding binding
                //= ActivityMain2DatabindingBinding.inflate(getLayoutInflater());
                = DataBindingUtil.setContentView(this, R.layout.activity_main2_databinding);
        binding.setUser(user);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                user.setFirstName("Christoffer");
                user.setAge(73);
            }
        }, 4000);

    }
}
