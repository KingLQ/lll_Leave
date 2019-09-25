package com.goldze.mvvmhabit.ui.changePsw;

import android.app.Application;
import android.databinding.ObservableField;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.view.View;

import com.goldze.mvvmhabit.data.DemoRepository;
import com.goldze.mvvmhabit.entity.response.ChangePswResponseEntity;
import com.goldze.mvvmhabit.ui.base.viewmodel.ToolbarViewModel;

import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import me.goldze.mvvmhabit.binding.command.BindingAction;
import me.goldze.mvvmhabit.binding.command.BindingCommand;
import me.goldze.mvvmhabit.http.BaseResponse;
import me.goldze.mvvmhabit.http.ResponseThrowable;
import me.goldze.mvvmhabit.utils.RxUtils;
import me.goldze.mvvmhabit.utils.ToastUtils;

public class ChangePsdViewModel extends ToolbarViewModel<DemoRepository> {

    public ObservableField<String> oldPassword = new ObservableField<>("");
    public ObservableField<String> newPassword = new ObservableField<>("");
    public ObservableField<String> surePassword = new ObservableField<>("");

    public class UIChangeObservable {
    }

    public ChangePsdViewModel(@NonNull Application application, DemoRepository repository) {
        super(application, repository);
    }

    public BindingCommand changePassword = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            quickLogin();
        }
    });

    /**
     * 初始化Toolbar
     */
    public void initToolbar() {
        //初始化标题栏
        setRightTextVisible(View.GONE);
        setRightIconVisible(View.GONE);
        setTitleText("修改密码");
    }

    private void quickLogin() {
        if (TextUtils.isEmpty(oldPassword.get())) {
            ToastUtils.showShort("请输入旧密码！");
            return;
        }
        if (TextUtils.isEmpty(newPassword.get())) {
            ToastUtils.showShort("请输入新密码！");
            return;
        }
        if (TextUtils.isEmpty(surePassword.get())) {
            ToastUtils.showShort("请输入确认新密码！");
            return;
        }
        if (!newPassword.get().equals(surePassword.get())) {
            ToastUtils.showShort("两次输入密码不一致！");
            return;
        }
        requestChangePassword(oldPassword.get(), newPassword.get());

    }

    public void requestChangePassword(String oldPassword, String newPassword) {
        model.requestChangePassword(oldPassword, newPassword)
                .compose(RxUtils.schedulersTransformer()) //线程调度
                .compose(RxUtils.exceptionTransformer()) // 网络错误的异常转换, 这里可以换成自己的ExceptionHandle
                .doOnSubscribe(this)//请求与ViewModel周期同步
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(Disposable disposable) throws Exception {
                        showDialog("正在请求...");
                    }
                })
                .subscribe(new Consumer<BaseResponse<Boolean>>() {
                    @Override
                    public void accept(BaseResponse<Boolean> response) throws Exception {
                        //请求成功
                        if (response.isSuccess()) {
                            //关闭页面
                            finish();
                        } else {
                            if (response.getMsg() != null && !response.getMsg().isEmpty()) {
                                ToastUtils.showShort(response.getMsg());
                            } else {
                                ToastUtils.showShort("注册失败，请重新登录！");
                            }
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        //关闭对话框
                        dismissDialog();
                        if (throwable instanceof ResponseThrowable) {
                            ToastUtils.showShort(((ResponseThrowable) throwable).message);
                        }
                    }
                }, new Action() {
                    @Override
                    public void run() throws Exception {
                        //关闭对话框
                        dismissDialog();
                    }
                });
    }
}
