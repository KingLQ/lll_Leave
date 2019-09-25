package com.goldze.mvvmhabit.ui.tab_bar.fragment.my;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.flyco.animation.BounceEnter.BounceTopEnter;
import com.flyco.animation.SlideExit.SlideBottomExit;
import com.flyco.dialog.listener.OnBtnClickL;
import com.flyco.dialog.widget.NormalDialog;
import com.goldze.mvvmhabit.BR;
import com.goldze.mvvmhabit.R;
import com.goldze.mvvmhabit.app.AppViewModelFactory;
import com.goldze.mvvmhabit.databinding.FragmentMyBinding;

import me.goldze.mvvmhabit.base.BaseFragment;
import me.goldze.mvvmhabit.utils.MaterialDialogUtils;

/**
 * Created by goldze on 2018/7/18.
 */

public class MyFragment extends BaseFragment<FragmentMyBinding, MyFragmentViewModel> {
    @Override
    public int initContentView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return R.layout.fragment_my;
    }

    @Override
    public int initVariableId() {
        return BR.viewModel;
    }

    @Override
    public MyFragmentViewModel initViewModel() {
        AppViewModelFactory factory = AppViewModelFactory.getInstance(getActivity().getApplication());
        return ViewModelProviders.of(this, factory).get(MyFragmentViewModel.class);
    }

    @Override
    public void initViewObservable() {
        super.initViewObservable();
        viewModel.dialogTitle.observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String title) {
                showDialogPhone(title);
            }
        });
    }

    public void showDialogPhone(final String title) {
        final NormalDialog dialog = new NormalDialog(getActivity());
        dialog.content(title)
                .style(NormalDialog.STYLE_TWO)
                .title("提示")
                .contentTextSize(16)
                .btnTextSize(16)
                .cornerRadius(20)
                .titleTextColor(ContextCompat.getColor(getActivity(), R.color.color_333333))
                .titleTextSize(16)
                .showAnim(new BounceTopEnter())
                .dismissAnim(new SlideBottomExit())
                .show();
        dialog.setOnBtnClickL(
                new OnBtnClickL() {
                    @Override
                    public void onBtnClick() {
                        dialog.dismiss();
                    }
                },
                new OnBtnClickL() {
                    @Override
                    public void onBtnClick() {
                        dialog.dismiss();
                        Intent intent = new Intent(Intent.ACTION_DIAL);
                        Uri data = Uri.parse("tel:" + title);
                        intent.setData(data);
                        startActivity(intent);
                    }
                });
    }


}
