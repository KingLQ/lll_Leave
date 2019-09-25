package com.goldze.mvvmhabit.ui.chat;

import android.app.Application;
import android.support.annotation.NonNull;

import com.goldze.mvvmhabit.data.DemoRepository;

import me.goldze.mvvmhabit.base.BaseViewModel;

public class ChatContentListViewModel extends BaseViewModel<DemoRepository> {

    public class UIChangeObservable {
    }

    public ChatContentListViewModel(@NonNull Application application, DemoRepository repository) {
        super(application, repository);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
