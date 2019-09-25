package com.goldze.mvvmhabit.ui.login;

import android.app.Application;
import android.databinding.ObservableField;
import android.support.annotation.NonNull;

import com.goldze.mvvmhabit.app.AppViewModelFactory;
import com.goldze.mvvmhabit.data.DemoRepository;
import com.goldze.mvvmhabit.entity.response.LoginResponseEntity;
import com.goldze.mvvmhabit.ui.changePsw.ChangePsdActivity;
import com.goldze.mvvmhabit.ui.quicklogin.QuickRegisterActivity;
import com.goldze.mvvmhabit.ui.vp_frg.ViewPagerGroupFragment;
import com.goldze.mvvmhabit.utils.UserManager;

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

public class LoginViewModel extends BaseViewModel<DemoRepository> {
    //用户名的绑定
    public ObservableField<String> userName = new ObservableField<>("");
    //密码的绑定
    public ObservableField<String> password = new ObservableField<>("");

    public LoginViewModel(@NonNull Application application, DemoRepository repository) {
        super(application, repository);
        //从本地取得数据绑定到View层
        userName.set(model.getUserName());
        password.set(model.getPassword());
    }

    //登录按钮的点击事件
    public BindingCommand loginOnClickCommand = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            login();
        }
    });

    //忘记密码
    public BindingCommand forgetPassword = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            startActivity(ChangePsdActivity.class);
        }
    });

    //快捷登录
    public BindingCommand quickJumpLogin = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            startActivity(QuickRegisterActivity.class);
        }
    });

    /**
     * 网络模拟一个登陆操作
     **/
    private void login() {
//        if (TextUtils.isEmpty(userName.get())) {
//            ToastUtils.showShort("请输入账号！");
//            return;
//        }
//        if (TextUtils.isEmpty(password.get())) {
//            ToastUtils.showShort("请输入密码！");
//            return;
//        }
        requestLoginByPassWord("17755109416", "111111");
    }

    public void requestLoginByPassWord(String phone, String password) {
        model.loginbypassword(phone, password)
                .compose(RxUtils.schedulersTransformer()) //线程调度
                .compose(RxUtils.exceptionTransformer()) // 网络错误的异常转换, 这里可以换成自己的ExceptionHandle
                .doOnSubscribe(this)//请求与ViewModel周期同步
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(Disposable disposable) throws Exception {
                        showDialog("正在请求...");
                    }
                })
                .subscribe(new Consumer<BaseResponse<LoginResponseEntity>>() {
                    @Override
                    public void accept(BaseResponse<LoginResponseEntity> response) throws Exception {
                        //请求成功
                        if (response.isSuccess()) {
                            UserManager.getAppManager().setmLogineEntity(getApplication(), response.getResponse());
                            startContainerActivity(ViewPagerGroupFragment.class.getCanonicalName());
                            //关闭页面
                            finish();
                        } else {
                            if (response.getMsg() != null && !response.getMsg().isEmpty()) {
                                ToastUtils.showShort(response.getMsg());
                            } else {
                                ToastUtils.showShort("登录失败，请重新登录！");
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


//        model.loginbypassword(phone, password)
//                .compose(RxUtils.schedulersTransformer()) //线程调度
//                .doOnSubscribe(this) //请求与ViewModel周期同步
//                .doOnSubscribe(new Consumer<Disposable>() {
//                    @Override
//                    public void accept(Disposable disposable) throws Exception {
//                        showDialog("正在请求...");
//                    }
//                })
//                .subscribe(new Consumer<LoginResponseEntity>() {
//                    @Override
//                    public void accept(LoginResponseEntity entity) throws Exception {
//                    }
//                });
    }

}
