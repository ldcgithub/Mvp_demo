package ldc.com.mvp.model.impl;

import android.content.Context;

import ldc.com.mvp.model.IUserModel;
import ldc.com.mvp.model.bean.User;
import ldc.com.mvp.presenter.callback.OnLoginListener;

public class UserModelImp implements IUserModel {

    private User mUser;

    private OnLoginListener mListener;

    private Context mContext;

    @Override
    public void login(Context context, User user, OnLoginListener listener) {
        this.mContext = context;
        this.mListener = listener;
        this.mUser = user;
    }
}
