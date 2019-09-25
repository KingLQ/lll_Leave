package com.goldze.mvvmhabit.ui.tab_bar.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import com.goldze.mvvmhabit.BR;
import com.goldze.mvvmhabit.R;
import com.goldze.mvvmhabit.databinding.ActivityTabBarBinding;
import com.goldze.mvvmhabit.ui.tab_bar.fragment.ChatListFragment;
import com.goldze.mvvmhabit.ui.tab_bar.fragment.LeaveFragment;
import com.goldze.mvvmhabit.ui.tab_bar.fragment.my.MyFragment;
import me.goldze.mvvmhabit.base.BaseActivity;
import me.goldze.mvvmhabit.base.BaseViewModel;
import me.majiajie.pagerbottomtabstrip.NavigationController;
import me.majiajie.pagerbottomtabstrip.listener.OnTabItemSelectedListener;

import java.util.ArrayList;
import java.util.List;

/**
 * 底部tab按钮的例子
 * 所有例子仅做参考,理解如何使用才最重要。
 * Created by goldze on 2018/7/18.
 */

public class TabBarActivity extends BaseActivity<ActivityTabBarBinding, BaseViewModel> {
    private List<Fragment> mFragments;

    @Override
    public int initContentView(Bundle savedInstanceState) {
        return R.layout.activity_tab_bar;
    }

    @Override
    public int initVariableId() {
        return BR.viewModel;
    }

    @Override
    public void initData() {
        //初始化Fragment
        initFragment();
        //初始化底部Button
        initBottomTab();
    }

    private void initFragment() {
        mFragments = new ArrayList<>();
        mFragments.add(new LeaveFragment());
        mFragments.add(new ChatListFragment());
        mFragments.add(new MyFragment());
        //默认选中第一个
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.add(R.id.frameLayout, mFragments.get(0));
        transaction.commitAllowingStateLoss();
    }

    private void initBottomTab() {
        NavigationController navigationController = binding.pagerBottomTab.material()
                .addItem(R.mipmap.yingyong, "应用")
                .addItem(R.mipmap.xiaoxi_select, "消息")
                .addItem(R.mipmap.wode_select, "我的")
                .setDefaultColor(ContextCompat.getColor(this, R.color.textColorVice))
                .build();
        //底部按钮的点击事件监听
        navigationController.addTabItemSelectedListener(new OnTabItemSelectedListener() {
            @Override
            public void onSelected(int index, int old) {
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.frameLayout, mFragments.get(index));
                transaction.commitAllowingStateLoss();
            }

            @Override
            public void onRepeat(int index) {
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mFragments != null) {
            mFragments.clear();
            mFragments = null;
        }
    }
}
