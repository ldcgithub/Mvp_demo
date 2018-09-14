package ldc.com.mvp.common;

import android.app.Activity;

import java.util.ArrayList;
import java.util.List;

/**
 *Activity管理控制类
 */
public class ActivityController {

    public static final List<Activity> ACTIVITIES = new ArrayList<Activity>();

    public static void addActivity(Activity activity){
        ACTIVITIES.add(activity);
    }

    public static void removeActivity(Activity activity){
        ACTIVITIES.remove(activity);
    }

    public static void exitApp(){
        finishAll();
        android.os.Process.killProcess(android.os.Process.myPid());
        System.exit(0);
    }

    private static void finishAll() {
        synchronized (ACTIVITIES) {
            for (Activity act : ACTIVITIES) {
                if (act != null && !act.isFinishing()) {
                    act.finish();
                }
            }
        }
    }


}
