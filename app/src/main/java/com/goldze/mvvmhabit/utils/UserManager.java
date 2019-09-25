package com.goldze.mvvmhabit.utils;

import android.app.Application;

import com.goldze.mvvmhabit.app.AppViewModelFactory;
import com.goldze.mvvmhabit.entity.response.LoginResponseEntity;
import com.goldze.mvvmhabit.entity.response.SelectDepartmentResponseEntity;

/**
 * 用户信息管理
 */
public class UserManager {

    private static UserManager instance;
    private LoginResponseEntity mLogineEntity = null;
    private SelectDepartmentResponseEntity mSelectDepartment = null;

    private UserManager() {
    }

    public static UserManager getAppManager() {
        if (instance == null) {
            instance = new UserManager();
        }
        return instance;
    }

    public LoginResponseEntity getmLogineEntity() {
        return mLogineEntity;
    }

    public String getToken() {
        if (mLogineEntity != null && mLogineEntity.getToken() != null && !mLogineEntity.getToken().isEmpty()) {
            return mLogineEntity.getToken();
        }
        return "";
    }

    public void setmLogineEntity(Application application, LoginResponseEntity response) {
        this.mLogineEntity = response;
        RetrofitClient.resetRetrofitClient(UserManager.getAppManager().getToken());
        AppViewModelFactory.destroyInstance();
    }

    public SelectDepartmentResponseEntity getmSelectDepartment() {
        return mSelectDepartment;
    }

    public void setmSelectDepartment(SelectDepartmentResponseEntity mSelectDepartment) {
        this.mSelectDepartment = mSelectDepartment;
    }

    public boolean isLogin() {
        if (mLogineEntity == null) {
            return true;
        }
        return false;
    }

    public void loginOut() {
        mLogineEntity = null;
    }

}