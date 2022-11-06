package pmt.daweiguide.apk;

import android.annotation.SuppressLint;
import android.app.Application;


public class MyApp extends Application {
    @Override
    public void onCreate() {
TypefaceUtils.overrideFont(getApplicationContext(),"SERIF","zawgyi.ttf");
        super.onCreate();
    }
}
