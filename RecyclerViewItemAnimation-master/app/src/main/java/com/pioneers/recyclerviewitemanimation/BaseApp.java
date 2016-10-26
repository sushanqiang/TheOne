package com.pioneers.recyclerviewitemanimation;

import android.app.Application;
import android.widget.Toast;
import com.yolanda.nohttp.Logger;
import com.yolanda.nohttp.NoHttp;

public class BaseApp extends Application {
    private static BaseApp app;


    public static BaseApp getInstance() {
        return app;
    }
    public static Toast toast;
    @Override
    public void onCreate() {
        super.onCreate();
        app = this;
        NoHttp.initialize(this);
        Logger.setDebug(true);
    }
    //全局土司提示
    public void showToast(String message) {
        if (null == toast) {
            if (message.equalsIgnoreCase("系统异常，请稍后再试")) {
                message = "网络不好，请稍后再试";
            }
            toast = Toast.makeText(BaseApp.this, message, Toast.LENGTH_SHORT);
        } else {
            toast.setText(message);
        }
        toast.show();
    }

    public void showLongToast(int message) {
        if (null == toast) {
            toast = Toast.makeText(BaseApp.this, message, Toast.LENGTH_SHORT);
        } else {
            toast.setText(message);
        }
        toast.show();
    }
}
