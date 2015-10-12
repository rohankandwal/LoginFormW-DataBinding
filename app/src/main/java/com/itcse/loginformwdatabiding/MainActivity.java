package com.itcse.loginformwdatabiding;

import android.databinding.DataBindingUtil;
import android.databinding.layouts.DataBindingInfo;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;

import com.itcse.loginformwdatabiding.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Binding Adapter created automatically based on the name of the XML file used. Using BindingAdapter to setContentView
        final ActivityMainBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        final LoginInfo loginInfo = new LoginInfo();

        binding.setLoginInfo(loginInfo);

        // Creating TextWatcher object which will detect text changes in EditText
        TextWatcher watcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                // Validating text after each key stroke
                loginInfo.validate(getResources());
            }
        };

        // Adding TextWatcher to both EditTexts
        binding.email.addTextChangedListener(watcher);
        binding.password.addTextChangedListener(watcher);

        // Validating inputs again when user clicks on Login button
        binding.login.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                loginInfo.loginExecuted = true;
                if (loginInfo.validate(getResources())) {
                    // Just showing the entered email id and password
                    Snackbar.make(binding.getRoot(), loginInfo.email.get() + " - " + loginInfo.password.get(), Snackbar.LENGTH_LONG).show();
                }
            }
        });
    }
}
