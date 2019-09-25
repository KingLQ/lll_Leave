package com.goldze.mvvmhabit.ui.vp_frg;

import android.support.v4.app.Fragment;

import com.goldze.mvvmhabit.R;
import com.goldze.mvvmhabit.ui.base.fragment.BasePagerFragment;
import com.goldze.mvvmhabit.ui.network.NetWorkFragment;
import com.goldze.mvvmhabit.ui.tab_bar.fragment.ChatListFragment;
import com.goldze.mvvmhabit.ui.tab_bar.fragment.LeaveFragment;
import com.goldze.mvvmhabit.ui.tab_bar.fragment.my.MyFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Create Author：goldze
 * Create Date：2019/01/25
 * Description：ViewPager+Fragment的实现
 */

public class ViewPagerGroupFragment extends BasePagerFragment {
    @Override
    protected List<Fragment> pagerFragment() {
        List<Fragment> list = new ArrayList<>();
        list.add(new LeaveFragment());
        list.add(new ChatListFragment());
        list.add(new MyFragment());
        return list;
    }

    @Override
    protected List<String> pagerTitleString() {
        List<String> list = new ArrayList<>();
        list.add("请假");
        list.add("聊天");
        list.add("我的");
        return list;
    }

    @Override
    protected List<Integer> pagerIcon() {
        List<Integer> list = new ArrayList<>();
        list.add(R.mipmap.leave_icon_bg);
        list.add(R.mipmap.my_icon_bg);
        list.add(R.mipmap.message_icon_g);
        return list;
    }
}
