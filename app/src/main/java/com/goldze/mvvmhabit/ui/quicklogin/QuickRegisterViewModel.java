package com.goldze.mvvmhabit.ui.quicklogin;

import android.app.Application;
import android.databinding.ObservableField;
import android.support.annotation.NonNull;
import android.text.TextUtils;

import com.goldze.mvvmhabit.data.DemoRepository;
import com.goldze.mvvmhabit.entity.request.RegisterRequestEntity;
import com.goldze.mvvmhabit.entity.request.SendSmsCodeRequestEntity;

import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import me.goldze.mvvmhabit.base.BaseViewModel;
import me.goldze.mvvmhabit.binding.command.BindingAction;
import me.goldze.mvvmhabit.binding.command.BindingCommand;
import me.goldze.mvvmhabit.http.BaseResponse;
import me.goldze.mvvmhabit.http.ResponseThrowable;
import me.goldze.mvvmhabit.utils.RxUtils;
import me.goldze.mvvmhabit.utils.ToastUtils;

public class QuickRegisterViewModel extends BaseViewModel<DemoRepository> {

    public ObservableField<String> quickLoginPhone = new ObservableField<>("");
    public ObservableField<String> phoneCode = new ObservableField<>("");
    public ObservableField<String> name = new ObservableField<>("");
    public ObservableField<String> settingPassword = new ObservableField<>("");
    public ObservableField<String> surePassword = new ObservableField<>("");

    public class UIChangeObservable {
    }

    public QuickRegisterViewModel(@NonNull Application application, DemoRepository repository) {
        super(application, repository);
    }

    public BindingCommand quickRegiste = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            quickLogin();
        }
    });

    private void quickLogin() {
        if (TextUtils.isEmpty(quickLoginPhone.get())) {
            ToastUtils.showShort("请输入手机号！");
            return;
        }
        if (TextUtils.isEmpty(phoneCode.get())) {
            ToastUtils.showShort("请输入验证码！");
            return;
        }
        if (TextUtils.isEmpty(name.get())) {
            ToastUtils.showShort("请输入昵称！");
            return;
        }
        if (TextUtils.isEmpty(settingPassword.get())) {
            ToastUtils.showShort("请输入密码！");
            return;
        }
        if (TextUtils.isEmpty(surePassword.get())) {
            ToastUtils.showShort("请输入确认密码！");
            return;
        }
        if (!settingPassword.get().equals(surePassword.get())) {
            ToastUtils.showShort("两次输入密码不一致！");
            return;
        }
        requestLoginByVerifyCode(quickLoginPhone.get(), phoneCode.get(), name.get(), settingPassword.get());
    }

    public void requestLoginByVerifyCode(String phone, String code, String name,
                                         String settingPassword) {
        model.register(phone, code, name, settingPassword)
                .compose(RxUtils.schedulersTransformer()) //线程调度
                .compose(RxUtils.exceptionTransformer()) // 网络错误的异常转换, 这里可以换成自己的ExceptionHandle
                .doOnSubscribe(this)//请求与ViewModel周期同步
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(Disposable disposable) throws Exception {
                        showDialog("正在请求...");
                    }
                })
                .subscribe(new Consumer<BaseResponse<RegisterRequestEntity>>() {
                    @Override
                    public void accept(BaseResponse<RegisterRequestEntity> response) throws Exception {
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


    //获取短信验证码
    public BindingCommand getQuickVerifyCode = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            if (TextUtils.isEmpty(quickLoginPhone.get())) {
                ToastUtils.showShort("请输入手机号！");
                return;
            }
            requestSendSmsCode(quickLoginPhone.get());
        }
    });

    public void requestSendSmsCode(String phone) {
        model.sendSmsCode(phone)
                .compose(RxUtils.schedulersTransformer()) //线程调度
                .compose(RxUtils.exceptionTransformer()) // 网络错误的异常转换, 这里可以换成自己的ExceptionHandle
                .doOnSubscribe(this)//请求与ViewModel周期同步
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(Disposable disposable) throws Exception {
                        showDialog("正在请求...");
                    }
                })
                .subscribe(new Consumer<BaseResponse<SendSmsCodeRequestEntity>>() {
                    @Override
                    public void accept(BaseResponse<SendSmsCodeRequestEntity> response) throws Exception {
                        //请求成功
                        if (!response.isSuccess()) {
                            ToastUtils.showShort(response.getMsg());
                        } else {
                            if (response.getMsg() != null && !response.getMsg().isEmpty()) {
                                ToastUtils.showShort(response.getMsg());
                            } else {
                                ToastUtils.showShort("获取验证码失败，请重新登录！");
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
