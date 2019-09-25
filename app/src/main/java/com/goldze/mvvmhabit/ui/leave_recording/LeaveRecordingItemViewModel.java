package com.goldze.mvvmhabit.ui.leave_recording;

import android.databinding.ObservableField;
import android.support.annotation.NonNull;

import com.goldze.mvvmhabit.entity.response.GetLeaveListResponseEntity;

import me.goldze.mvvmhabit.base.ItemViewModel;
import me.goldze.mvvmhabit.binding.command.BindingAction;
import me.goldze.mvvmhabit.binding.command.BindingCommand;
import me.goldze.mvvmhabit.utils.ToastUtils;


public class LeaveRecordingItemViewModel extends ItemViewModel<LeaveRecordingListViewModel> {

    public ObservableField<GetLeaveListResponseEntity.DataBean> entity = new ObservableField<>();

    public LeaveRecordingItemViewModel(@NonNull LeaveRecordingListViewModel viewModel, GetLeaveListResponseEntity.DataBean entity) {
        super(viewModel);
        this.entity.set(entity);
    }

    /**
     * 获取position的方式有很多种,indexOf是其中一种，常见的还有在Adapter中、ItemBinding.of回调里
     *
     * @return
     */
    public int getPosition() {
        return viewModel.getItemPosition(this);
    }

    //条目的点击事件
    public BindingCommand itemClick = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
        }
    });
}
