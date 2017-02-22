package com.nguyen.bzc;

import android.app.Activity;
import android.content.Context;

public class ApplicationContext {
    private static ApplicationContext instance = new ApplicationContext();

    private ApplicationContext() {

    }

    public static ApplicationContext getInstance() {
        return instance;
    }

    private Context _applicationContext;

    // Static variable for saving current main activity
    private Activity currentActivity_;


    public void setMainActivity(Activity mainActivity) {
        currentActivity_ = mainActivity;
    }


    public Activity getMainActivity() {
        return currentActivity_;
    }


    public void setApplicationContext(Context applicationContext) {
        _applicationContext = applicationContext;
    }

    public Context getApplicationContext() {
        return _applicationContext;
    }


}
