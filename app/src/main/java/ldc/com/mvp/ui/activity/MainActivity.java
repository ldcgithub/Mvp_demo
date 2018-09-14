package ldc.com.mvp.ui.activity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.KeyEvent;
import android.widget.Toast;

import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;
import com.youlu.modul.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import ldc.com.mvp.R;
import ldc.com.mvp.common.ActivityController;
import ldc.com.mvp.ui.fragment.FragmentAllArticle;
import ldc.com.mvp.ui.fragment.FragmentJuji;
import ldc.com.mvp.ui.fragment.FragmentMeiju;
import ldc.com.mvp.ui.fragment.FragmentOriginal;

public class MainActivity extends BaseActivity implements BottomNavigationBar.OnTabSelectedListener {

    private static boolean enableExit = false;
    @BindView(R.id.bottom_navigation_bar_container)
    BottomNavigationBar bottom_navigation_bar_container;
    private FragmentMeiju fragmentMeiju;
    private FragmentAllArticle fragmentAllArticle;
    private FragmentJuji fragmentJuji;
    private FragmentOriginal fragmentOriginal;
    private MyHandler mHandler = new MyHandler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        initBottomNavBar();
    }

    private void initBottomNavBar() {
        bottom_navigation_bar_container.setMode(BottomNavigationBar.MODE_DEFAULT);
        bottom_navigation_bar_container.setBackgroundStyle(BottomNavigationBar.BACKGROUND_STYLE_STATIC);

        bottom_navigation_bar_container.setBarBackgroundColor(R.color.white); //背景颜色
        bottom_navigation_bar_container.setInActiveColor(R.color.bottom_nav_normal); //未选中时的颜色
        bottom_navigation_bar_container.setActiveColor(R.color.bottom_nav_selected);//选中时的颜色

        BottomNavigationItem meijulItem = new BottomNavigationItem(R.mipmap.icon_linggan, "灵感");
        BottomNavigationItem allArticleItem = new BottomNavigationItem(R.mipmap.icon_jingdian, "经典");
        BottomNavigationItem jujiItem = new BottomNavigationItem(R.mipmap.icon_juji, "句集");
        BottomNavigationItem originalItem = new BottomNavigationItem(R.mipmap.icon_yuanchuang, "原创");

        bottom_navigation_bar_container.addItem(meijulItem).addItem(allArticleItem).addItem(jujiItem).addItem(originalItem);
        bottom_navigation_bar_container.setFirstSelectedPosition(0);
        bottom_navigation_bar_container.initialise();
        bottom_navigation_bar_container.setTabSelectedListener(this);

        setDefaultFrag();

    }

    private void setDefaultFrag() {
        if (fragmentMeiju == null) {
            fragmentMeiju = new FragmentMeiju();
        }
        addFrag(fragmentMeiju);
        getSupportFragmentManager().beginTransaction().show(fragmentMeiju).commit();
    }

    private void addFrag(Fragment fragment) {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();

        if (fragment != null && !fragment.isAdded()) {
            ft.add(R.id.bottom_nav_content, fragment);
        }
        ft.commit();
    }

    private void hideFrag(Fragment fragment) {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        if (fragment != null && fragment.isAdded()) {
            ft.hide(fragment);
        }
        ft.commit();
    }

    private void hideAllFrag() {
        hideFrag(fragmentMeiju);
        hideFrag(fragmentAllArticle);
        hideFrag(fragmentJuji);
        hideFrag(fragmentOriginal);
    }

    @Override
    public void onTabSelected(int position) {
        hideAllFrag();
        switch (position) {
            case 0:
                if (fragmentMeiju == null) {
                    fragmentMeiju = new FragmentMeiju();

                }
                addFrag(fragmentMeiju);
                getSupportFragmentManager().beginTransaction().show(fragmentMeiju).commit();
                break;
            case 1:
                if (fragmentAllArticle == null) {
                    fragmentAllArticle = new FragmentAllArticle();
                }
                addFrag(fragmentAllArticle);
                getSupportFragmentManager().beginTransaction().show(fragmentAllArticle).commit();

                break;
            case 2:
                if (fragmentJuji == null) {

                    fragmentJuji = new FragmentJuji();
                }

                addFrag(fragmentJuji);
                getSupportFragmentManager().beginTransaction().show(fragmentJuji).commit();

                break;
            case 3:
                if (fragmentOriginal == null) {
                    fragmentOriginal = new FragmentOriginal();

                }
                addFrag(fragmentOriginal);
                getSupportFragmentManager().beginTransaction().show(fragmentOriginal).commit();
                break;

        }


    }

    @Override
    public void onTabUnselected(int position) {

    }

    @Override
    public void onTabReselected(int position) {

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (!enableExit) {
                enableExit = true;
                Toast.makeText(MainActivity.this, "再按一次退出程序", Toast.LENGTH_SHORT).show();
                mHandler.sendEmptyMessageDelayed(0, 3000);
            } else {
                ActivityController.exitApp();
            }
            return true;
        }

        return super.onKeyDown(keyCode, event);
    }

    private static class MyHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 0:
                    enableExit = false;
                    break;
            }
        }
    }
}
