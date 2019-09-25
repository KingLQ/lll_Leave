package com.goldze.mvvmhabit.ui.sex;

import android.app.Application;
import android.support.annotation.NonNull;

import com.goldze.mvvmhabit.data.DemoRepository;

import me.goldze.mvvmhabit.base.BaseViewModel;

public class SelectSexViewModel extends BaseViewModel<DemoRepository> {

    public class UIChangeObservable {
    }

    public SelectSexViewModel(@NonNull Application application, DemoRepository repository) {
        super(application, repository);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
