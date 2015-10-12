package com.itcse.loginformwdatabiding.util;

import android.databinding.BindingAdapter;
import android.databinding.BindingConversion;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;

import com.itcse.loginformwdatabiding.R;

/**
 * Class used for managing Binder related conversions.
 */
public class Converters {

    // Need this function for "app:error" attribute
    @BindingConversion
    public static String convertBindableToString(BindableString bindableString) {
        return bindableString.get();
    }

    // Function used by "Reset" button
    @BindingAdapter({"app:onClick"})
    public static void bindOnClick(View view, final Runnable runnable) {
        view.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                runnable.run();
            }
        });
    }

    // Function used by "Email" and "Password" EditText field
    @BindingAdapter({"app:binding"})
    public static void bindEditText(final EditText view, final BindableString bindableString) {
        // Using tag to ensure that TextWatcher isn't assigned to the EditTexts multiple times
        if (view.getTag(R.id.dataBinding) == null) {
            view.setTag(R.id.dataBinding, true);
            view.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    // 2 way communication, from layout to object
                    bindableString.set(s.toString());
                }

                @Override
                public void afterTextChanged(Editable s) {

                }
            });

            // If the values have changed, updating view
            final String newValue = bindableString.get();
            if (!view.getText().toString().equals(newValue)) {
                view.setText(newValue);
            }
        }
    }
}
