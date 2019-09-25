package com.goldze.mvvmhabit.ui.main;

import android.app.Application;
import android.support.annotation.NonNull;

import com.goldze.mvvmhabit.data.DemoRepository;
import com.goldze.mvvmhabit.ui.vp_frg.ViewPagerGroupFragment;

import me.goldze.mvvmhabit.base.BaseViewModel;

public class MainViewModel extends BaseViewModel<DemoRepository> {


    public class UIChangeObservable {
    }

    public MainViewModel(@NonNull Application application, DemoRepository repository) {
        super(application, repository);
    }
}
