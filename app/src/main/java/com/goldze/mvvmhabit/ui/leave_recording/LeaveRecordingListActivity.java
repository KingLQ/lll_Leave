package com.goldze.mvvmhabit.ui.leave_recording;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.goldze.mvvmhabit.BR;
import com.goldze.mvvmhabit.R;
import com.goldze.mvvmhabit.app.AppViewModelFactory;
import com.goldze.mvvmhabit.databinding.LeaveRecordingBinding;

import me.goldze.mvvmhabit.base.BaseActivity;

public class LeaveRecordingListActivity extends BaseActivity<LeaveRecordingBinding, LeaveRecordingListViewModel> {

    @Override
    public void initParam() {
        super.initParam();
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
    }

    @Override
    public int initContentView(Bundle savedInstanceState) {
        return R.layout.leave_recording;
    }

    @Override
    public int initVariableId() {
        return BR.viewModel;
    }

    @Override
    public LeaveRecordingListViewModel initViewModel() {
        //使用自定义的ViewModelFactory来创建ViewModel，如果不重写该方法，则默认会调用LoginViewModel(@NonNull Application application)构造方法
        AppViewModelFactory factory = AppViewModelFactory.getInstance(getApplication());
        return ViewModelProviders.of(this, factory).get(LeaveRecordingListViewModel.class);
    }

    @Override
    public void initViewObservable() {
        //监听下拉刷新完成
        viewModel.uc.finishRefreshing.observe(this, new Observer() {
            @Override
            public void onChanged(@Nullable Object o) {
                //结束刷新
                binding.leaveRefreshLayout.finishRefreshing();
            }
        });
    }

    @Override
    public void initData() {
        super.initData();
        //请求网络数据
        viewModel.requestNetWork(1, 10);
        //初始化标题
        viewModel.initToolbar();
    }


}
