package com.xplore.learningdatabinding;


import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.xplore.learningdatabinding.databinding.ActivityMain2DatabindingBinding;
import com.xplore.learningdatabinding.databinding.DatabindUser;

public class Main2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DatabindUser user = new DatabindUser("Chris"
                , "Lucas", 27);
        ActivityMain2DatabindingBinding binding
                //= ActivityMain2DatabindingBinding.inflate(getLayoutInflater());
                = DataBindingUtil.setContentView(this, R.layout.activity_main2_databinding);
        binding.setUser(user);
    }
}
