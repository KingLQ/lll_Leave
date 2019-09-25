package com.goldze.mvvmhabit.ui.selectsdepartment;

import android.app.Application;
import android.databinding.ObservableArrayList;
import android.databinding.ObservableList;
import android.support.annotation.NonNull;
import android.view.View;

import com.goldze.mvvmhabit.R;
import com.goldze.mvvmhabit.data.DemoRepository;
import com.goldze.mvvmhabit.entity.response.GetLeaveListResponseEntity;
import com.goldze.mvvmhabit.entity.response.SelectDepartmentResponseEntity;
import com.goldze.mvvmhabit.ui.base.viewmodel.ToolbarViewModel;

import java.util.List;

import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import me.goldze.mvvmhabit.http.BaseResponse;
import me.goldze.mvvmhabit.http.ResponseThrowable;
import me.goldze.mvvmhabit.utils.RxUtils;
import me.goldze.mvvmhabit.utils.ToastUtils;
import me.tatarka.bindingcollectionadapter2.ItemBinding;

public class SelectDepartmentViewModel extends ToolbarViewModel<DemoRepository> {

    //给RecyclerView添加ObservableList
    public ObservableList<SelectDepartmentItemViewModel> observableList = new ObservableArrayList<>();

    public class UIChangeObservable {
    }

    public SelectDepartmentViewModel(@NonNull Application application, DemoRepository repository) {
        super(application, repository);
    }

    //给RecyclerView添加ItemBinding
    public ItemBinding<SelectDepartmentItemViewModel> itemBinding = ItemBinding.of(com.goldze.mvvmhabit.BR.viewModel, R.layout.item_department);

    public void requestNetWork() {
        //可以调用addSubscribe()添加Disposable，请求与View周期同步
        model.getDepartList()
                .compose(RxUtils.schedulersTransformer()) //线程调度
                .compose(RxUtils.exceptionTransformer()) // 网络错误的异常转换, 这里可以换成自己的ExceptionHandle
                .doOnSubscribe(this)//请求与ViewModel周期同步
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(Disposable disposable) throws Exception {
                        showDialog("正在请求...");
                    }
                })
                .subscribe(new Consumer<BaseResponse<List<SelectDepartmentResponseEntity>>>() {
                    @Override
                    public void accept(BaseResponse<List<SelectDepartmentResponseEntity>> response) throws Exception {
                        //请求成功
                        if (response.getCode() == 200 && response.isSuccess()) {
                            for (SelectDepartmentResponseEntity entity : response.getResponse()) {
                                SelectDepartmentItemViewModel itemViewModel = new SelectDepartmentItemViewModel(SelectDepartmentViewModel.this, entity);
                                //双向绑定动态添加Item
                                observableList.add(itemViewModel);
                            }
                        } else {
                            //code错误时也可以定义Observable回调到View层去处理
                            ToastUtils.showShort("数据错误");
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

    /**
     * 初始化Toolbar
     */
    public void initToolbar() {
        //初始化标题栏
        setRightTextVisible(View.GONE);
        setRightIconVisible(View.GONE);
        setTitleText("选择部门");
    }

    public int getItemPosition(SelectDepartmentItemViewModel mSelectDepartmentItemViewModel) {
        return observableList.indexOf(mSelectDepartmentItemViewModel);
    }

}
