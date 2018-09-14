package ldc.com.mvp.model;

import android.content.Context;

import ldc.com.mvp.model.bean.User;
import ldc.com.mvp.presenter.callback.OnLoginListener;

/**
 * 用户登录接口
 */
public interface IUserModel {
    void login(Context context, User user, OnLoginListener listener);
}
