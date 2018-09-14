package ldc.com.mvp.app;

import android.app.Application;
import android.content.Context;

import com.apkfuns.logutils.LogUtils;

/**
 *  全局管理器
 */
public class MyApp extends Application {

    private static Context sContext;

    public static Context getContext() {
        return sContext;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        sContext = this;
        initUtil();
    }

    private void initUtil() {
        LogUtils.configAllowLog = true;
        LogUtils.configTagPrefix = "mvp-";
    }
}
