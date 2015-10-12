package com.itcse.loginformwdatabiding.util;

import android.databinding.BaseObservable;

/**
 * Created by User on 12-10-2015.
 */
public class BindableBoolean extends BaseObservable {

    boolean value;

    public Boolean get() {
        return value;
    }

    public void set( final boolean value) {
        if (this.value != value) {
            this.value = value;
            notifyChange();
        }
    }

}
