package com.itcse.loginformwdatabiding;

import android.content.res.Resources;
import android.util.Patterns;

import com.itcse.loginformwdatabiding.util.BindableBoolean;
import com.itcse.loginformwdatabiding.util.BindableString;

/**
 * Created by User on 12-10-2015.
 */
public class LoginInfo {

    public BindableString email = new BindableString();
    public BindableString password = new BindableString();
    public BindableString emailErr = new BindableString();
    public BindableString passErr = new BindableString();


    public boolean loginExecuted;

    // Function to reset the fields when user clicks on reset button
    public void reset() {
        email.set(null);
        password.set(null);
        emailErr.set(null);
        passErr.set(null);
        loginExecuted = false;
    }

    // Function to validate entered data and display relevant error response
    public boolean validate(final Resources resources) {
        if (!loginExecuted) {
            return true;
        }

        int emailErrRes = 0;
        int passErrRes = 0;

        if (email.isEmpty()) {
            emailErrRes = R.string.empty_email;
        } else if (!Patterns.EMAIL_ADDRESS.matcher(email.get()).matches()){
            emailErrRes = R.string.invalid_email;
        }

        if (password.isEmpty()) {
            passErrRes = R.string.empty_pass;
        }

        emailErr.set(emailErrRes != 0 ? resources.getString(emailErrRes) : null);
        passErr.set(passErrRes != 0 ? resources.getString(passErrRes) : null);
        return emailErrRes == 0 && passErrRes == 0;
    }
}
