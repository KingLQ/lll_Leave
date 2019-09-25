package com.goldze.mvvmhabit.ui.tab_bar.fragment.my;

import android.app.Application;
import android.support.annotation.NonNull;

import com.goldze.mvvmhabit.data.DemoRepository;
import com.goldze.mvvmhabit.entity.request.SendSmsCodeRequestEntity;
import com.goldze.mvvmhabit.ui.changePsw.ChangePsdActivity;
import com.goldze.mvvmhabit.ui.leave_recording.LeaveRecordingListActivity;
import com.goldze.mvvmhabit.ui.login.LoginActivity;
import com.goldze.mvvmhabit.ui.selectsdepartment.SelectDepartmentActivity;
import com.goldze.mvvmhabit.utils.UserManager;

import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import me.goldze.mvvmhabit.base.BaseViewModel;
import me.goldze.mvvmhabit.binding.command.BindingAction;
import me.goldze.mvvmhabit.binding.command.BindingCommand;
import me.goldze.mvvmhabit.bus.event.SingleLiveEvent;
import me.goldze.mvvmhabit.http.BaseResponse;
import me.goldze.mvvmhabit.http.ResponseThrowable;
import me.goldze.mvvmhabit.utils.RxUtils;
import me.goldze.mvvmhabit.utils.ToastUtils;

public class MyFragmentViewModel extends BaseViewModel<DemoRepository> {

    public SingleLiveEvent<String> dialogTitle = new SingleLiveEvent<>();

    public MyFragmentViewModel(@NonNull Application application, DemoRepository repository) {
        super(application, repository);
    }

    public BindingCommand my_department = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            startActivity(SelectDepartmentActivity.class);
        }
    });

    public BindingCommand my_leave_recording = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            startActivity(LeaveRecordingListActivity.class);
        }
    });

    public BindingCommand my_changpsw = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            startActivity(ChangePsdActivity.class);
        }
    });

    public BindingCommand my_contact = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            getCompanyInfo();
        }
    });

    public BindingCommand my_exit = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            UserManager.getAppManager().loginOut();
            startActivity(LoginActivity.class);
            finish();
        }
    });

    public void getCompanyInfo() {
        model.getCompanyInfo()
                .compose(RxUtils.schedulersTransformer()) //线程调度
                .compose(RxUtils.exceptionTransformer()) // 网络错误的异常转换, 这里可以换成自己的ExceptionHandle
                .doOnSubscribe(this)//请求与ViewModel周期同步
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(Disposable disposable) throws Exception {
                        showDialog("正在请求...");
                    }
                })
                .subscribe(new Consumer<BaseResponse<String>>() {
                    @Override
                    public void accept(BaseResponse<String> response) throws Exception {
                        //请求成功
                        if (!response.isSuccess()) {
                            ToastUtils.showShort(response.getMsg());
                        } else {
                            if (response.getMsg() != null && !response.getMsg().isEmpty()) {
                                dialogTitle.setValue(response.getResponse().toString());
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
