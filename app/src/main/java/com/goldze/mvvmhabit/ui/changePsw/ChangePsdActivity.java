package com.goldze.mvvmhabit.ui.changePsw;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;

import com.goldze.mvvmhabit.BR;
import com.goldze.mvvmhabit.R;
import com.goldze.mvvmhabit.app.AppViewModelFactory;
import com.goldze.mvvmhabit.databinding.ActivityDemoBinding;

import me.goldze.mvvmhabit.base.BaseActivity;

public class ChangePsdActivity extends BaseActivity<ActivityDemoBinding, ChangePsdViewModel> {

    @Override
    public int initContentView(Bundle savedInstanceState) {
        return R.layout.activity_change_psw;
    }

    @Override
    public int initVariableId() {
        return BR.viewModel;
    }

    @Override
    public ChangePsdViewModel initViewModel() {
        //使用自定义的ViewModelFactory来创建ViewModel，如果不重写该方法，则默认会调用LoginViewModel(@NonNull Application application)构造方法
        AppViewModelFactory factory = AppViewModelFactory.getInstance(getApplication());
        return ViewModelProviders.of(this, factory).get(ChangePsdViewModel.class);
    }

    @Override
    public void initData() {
        super.initData();
        //初始化标题
        viewModel.initToolbar();
    }
}
