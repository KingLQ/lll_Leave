package com.goldze.mvvmhabit.data.source;

import com.goldze.mvvmhabit.entity.DemoEntity;
import com.goldze.mvvmhabit.entity.response.GetLeaveListResponseEntity;
import com.goldze.mvvmhabit.entity.response.LoginResponseEntity;
import com.goldze.mvvmhabit.entity.response.SelectDepartmentResponseEntity;

import java.util.List;

import io.reactivex.Observable;
import me.goldze.mvvmhabit.http.BaseResponse;

/**
 * Created by goldze on 2019/3/26.
 */
public interface HttpDataSource {
    //模拟登录
    Observable<Object> login();

    //模拟上拉加载
    Observable<DemoEntity> loadMore();

    Observable<BaseResponse<DemoEntity>> demoGet();

    Observable<BaseResponse<DemoEntity>> demoPost(String catalog);

    Observable<BaseResponse<LoginResponseEntity>> loginbypassword(String phone, String passWord);

    Observable<BaseResponse<String>> register(String phone, String password, String name,
                                              String settingPassword);

    Observable<BaseResponse<String>> sendSmsCode(String phone);

    Observable<BaseResponse<GetLeaveListResponseEntity>> getLeaveList(int currentPage, int pageSize);

    Observable<BaseResponse<List<SelectDepartmentResponseEntity>>> getDepartList();

    Observable<BaseResponse<String>> getCompanyInfo();

    Observable<BaseResponse<Boolean>> requestChangePassword(String oldPassWord, String newPassWord);

}
