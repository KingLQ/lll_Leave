package com.goldze.mvvmhabit.data.source.http;

import com.goldze.mvvmhabit.data.source.HttpDataSource;
import com.goldze.mvvmhabit.data.source.http.service.DemoApiService;
import com.goldze.mvvmhabit.entity.DemoEntity;
import com.goldze.mvvmhabit.entity.request.ChangePswRequestEntity;
import com.goldze.mvvmhabit.entity.request.GetLeaveListRequestEntity;
import com.goldze.mvvmhabit.entity.request.LoginRequestEntity;
import com.goldze.mvvmhabit.entity.request.RegisterRequestEntity;
import com.goldze.mvvmhabit.entity.request.SendSmsCodeRequestEntity;
import com.goldze.mvvmhabit.entity.response.GetLeaveListResponseEntity;
import com.goldze.mvvmhabit.entity.response.LoginResponseEntity;
import com.goldze.mvvmhabit.entity.response.SelectDepartmentResponseEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import me.goldze.mvvmhabit.http.BaseResponse;
import okhttp3.MediaType;
import okhttp3.RequestBody;

/**
 * Created by goldze on 2019/3/26.
 */
public class HttpDataSourceImpl implements HttpDataSource {
    private DemoApiService apiService;
    private volatile static HttpDataSourceImpl INSTANCE = null;

    public static HttpDataSourceImpl getInstance(DemoApiService apiService) {
        if (INSTANCE == null) {
            synchronized (HttpDataSourceImpl.class) {
                if (INSTANCE == null) {
                    INSTANCE = new HttpDataSourceImpl(apiService);
                }
            }
        }
        return INSTANCE;
    }

    public static void destroyInstance() {
        INSTANCE = null;
    }

    private HttpDataSourceImpl(DemoApiService apiService) {
        this.apiService = apiService;
    }

    @Override
    public Observable<Object> login() {
        return Observable.just(new Object()).delay(3, TimeUnit.SECONDS); //延迟3秒
    }

    @Override
    public Observable<DemoEntity> loadMore() {
        return Observable.create(new ObservableOnSubscribe<DemoEntity>() {
            @Override
            public void subscribe(ObservableEmitter<DemoEntity> observableEmitter) throws Exception {
                DemoEntity entity = new DemoEntity();
                List<DemoEntity.ItemsEntity> itemsEntities = new ArrayList<>();
                //模拟一部分假数据
                for (int i = 0; i < 10; i++) {
                    DemoEntity.ItemsEntity item = new DemoEntity.ItemsEntity();
                    item.setId(-1);
                    item.setName("模拟条目");
                    itemsEntities.add(item);
                }
                entity.setItems(itemsEntities);
                observableEmitter.onNext(entity);
            }
        }).delay(3, TimeUnit.SECONDS); //延迟3秒
    }

    @Override
    public Observable<BaseResponse<DemoEntity>> demoGet() {
        return apiService.demoGet();
    }

    @Override
    public Observable<BaseResponse<DemoEntity>> demoPost(String catalog) {
        return apiService.demoPost(catalog);
    }

    @Override
    public Observable<BaseResponse<LoginResponseEntity>> loginbypassword(String phone, String passWord) {
        LoginRequestEntity login = new LoginRequestEntity();
        login.setPhone(phone);
        login.setPassWord(passWord);
        return apiService.loginbypassword(login);
    }

    @Override
    public Observable<BaseResponse<String>> register(String phone, String code, String name,
                                                     String settingPassword) {
        RegisterRequestEntity register = new RegisterRequestEntity();
        register.setPhone(phone);
        register.setPassWord(settingPassword);
        register.setNickName(name);
        register.setCode(code);
        return apiService.register(register);
    }

    @Override
    public Observable<BaseResponse<Boolean>> requestChangePassword(String oldPassWord, String newPassWord) {
        ChangePswRequestEntity password = new ChangePswRequestEntity();
        password.setOldPassWord(oldPassWord);
        password.setNewPassWord(newPassWord);
        return apiService.modifyPwd(password);
    }

    @Override
    public Observable<BaseResponse<String>> sendSmsCode(String phone) {
        SendSmsCodeRequestEntity sendsmscode = new SendSmsCodeRequestEntity();
        sendsmscode.setPhone(phone);
        return apiService.sendSmsCode(sendsmscode);
    }

    @Override
    public Observable<BaseResponse<GetLeaveListResponseEntity>> getLeaveList(int currentPage, int pageSize) {
        GetLeaveListRequestEntity getleavelist = new GetLeaveListRequestEntity();
        getleavelist.setCurrentPage(currentPage);
        getleavelist.setPageSize(pageSize);
        return apiService.getLeaveList(getleavelist);
    }

    @Override
    public Observable<BaseResponse<List<SelectDepartmentResponseEntity>>> getDepartList() {
        return apiService.getDepartList();
    }

    @Override
    public Observable<BaseResponse<String>> getCompanyInfo( ) {
        return apiService.getCompanyInfo();
    }

    public static RequestBody toRequestBody(String value) {
        RequestBody requestBody = RequestBody.create(MediaType.parse("text/plain"), value);
        return requestBody;
    }


}
