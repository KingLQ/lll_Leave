package com.goldze.mvvmhabit.data.source.http.service;

import com.goldze.mvvmhabit.entity.DemoEntity;
import com.goldze.mvvmhabit.entity.request.ChangePswRequestEntity;
import com.goldze.mvvmhabit.entity.request.GetLeaveListRequestEntity;
import com.goldze.mvvmhabit.entity.request.LoginRequestEntity;
import com.goldze.mvvmhabit.entity.request.RegisterRequestEntity;
import com.goldze.mvvmhabit.entity.request.SendSmsCodeRequestEntity;
import com.goldze.mvvmhabit.entity.response.GetLeaveListResponseEntity;
import com.goldze.mvvmhabit.entity.response.LoginResponseEntity;
import com.goldze.mvvmhabit.entity.response.SelectDepartmentResponseEntity;

import java.util.List;

import io.reactivex.Observable;
import me.goldze.mvvmhabit.http.BaseResponse;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;

/**
 * Created by goldze on 2017/6/15.
 */

public interface DemoApiService {

    @GET("action/apiv2/banner?catalog=1")
    Observable<BaseResponse<DemoEntity>> demoGet();

    @FormUrlEncoded
    @POST("action/apiv2/banner")
    Observable<BaseResponse<DemoEntity>> demoPost(@Field("catalog") String catalog);

    @Headers({"Content-Type: application/json"})
    @POST("api/User/LoginByPassWord")
    Observable<BaseResponse<LoginResponseEntity>> loginbypassword(@Body LoginRequestEntity params);

    @Headers({"Content-Type: application/json-patch+json", "accept: application/json"})
    @POST("api/User/Register")
    Observable<BaseResponse<String>> register(@Body RegisterRequestEntity params);

    @Headers({"Content-Type: application/json-patch+json", "accept: application/json"})
    @POST("api/User/SendSmsCode")
    Observable<BaseResponse<String>> sendSmsCode(@Body SendSmsCodeRequestEntity params);

    @Headers({"Content-Type: application/json-patch+json", "accept: application/json"})
    @POST("api/Leave/GetLeaveList")
    Observable<BaseResponse<GetLeaveListResponseEntity>> getLeaveList(@Body GetLeaveListRequestEntity params);

    @Headers({"Content-Type: application/json-patch+json", "accept: application/json"})
    @POST("api/User/GetDepartList")
    Observable<BaseResponse<List<SelectDepartmentResponseEntity>>> getDepartList();

    @Headers({"accept: application/json"})
    @POST("api/User/GetCompanyInfo")
    Observable<BaseResponse<String>> getCompanyInfo();

    @POST("api/User/ModifyPwd")
    Observable<BaseResponse<Boolean>> modifyPwd(@Body ChangePswRequestEntity params);
}
