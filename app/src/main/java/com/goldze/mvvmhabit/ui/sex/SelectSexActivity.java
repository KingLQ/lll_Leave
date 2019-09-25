package com.goldze.mvvmhabit.ui.sex;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;

import com.goldze.mvvmhabit.BR;
import com.goldze.mvvmhabit.R;
import com.goldze.mvvmhabit.app.AppViewModelFactory;
import com.goldze.mvvmhabit.databinding.ActivityDemoBinding;

import me.goldze.mvvmhabit.base.BaseActivity;

/**
 * 一个MVVM模式的登陆界面
 */
public class SelectSexActivity extends BaseActivity<ActivityDemoBinding, SelectSexViewModel> {

    //ActivityLoginBinding类是databinding框架自定生成的,对应activity_login.xml
    @Override
    public int initContentView(Bundle savedInstanceState) {
        return R.layout.select_sexw;
    }

    @Override
    public int initVariableId() {
        return BR.viewModel;
    }

    @Override
    public SelectSexViewModel initViewModel() {
        //使用自定义的ViewModelFactory来创建ViewModel，如果不重写该方法，则默认会调用LoginViewModel(@NonNull Application application)构造方法
        AppViewModelFactory factory = AppViewModelFactory.getInstance(getApplication());
        return ViewModelProviders.of(this, factory).get(SelectSexViewModel.class);
    }

    @Override
    public void initViewObservable() {
    }
}