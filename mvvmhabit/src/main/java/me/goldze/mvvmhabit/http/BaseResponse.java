package me.goldze.mvvmhabit.http;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by goldze on 2017/5/10.
 * 该类仅供参考，实际业务返回的固定字段, 根据需求来定义，
 */
public class BaseResponse<T>  {

    /**
     * success : true
     * code : 200
     * msg : ok
     * response : {"token":"eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJqdGkiOiJiOTc5M2JmNi04NTk4LTQyZDMtOTg3OS01ODRjNDgxNjY5MzAiLCJpYXQiOiIxNTY3OTYwODQxIiwibmJmIjoiMTU2Nzk2MDg0MSIsImV4cCI6IjE1NzA1NTI4NDEiLCJpc3MiOiJUU1dBcGkiLCJ1c2VySW5mbyI6IntcIlN0YWZmSWRcIjpcImI5NzkzYmY2LTg1OTgtNDJkMy05ODc5LTU4NGM0ODE2NjkzMFwiLFwiU3RhZmZOYW1lXCI6XCLlhbvnjKrlsI_og73miYtcIixcIk1vYmlsZVwiOlwiMTc3NTUxMDk0MTZcIixcIklzQWRtaW5cIjpmYWxzZSxcIkxhc3RWaXNpdFRpbWVcIjpcIjIwMTkvMDkvMDkgMDA6NDA6NDFcIixcIkV4cGlyZVRpbWVcIjpcIjIwMTkvMTAvMDkgMDA6NDA6NDFcIn0iLCJhdWQiOiJTT1AifQ.zgb9acf8iCvLLQcED4B1Bsh-Hd1zYK22-ogNsdDeRMg","phone":"17755109416","nickName":"养猪小能手"}
     */

    private boolean success;
    private int code;
    private String msg;
    private T response;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getResponse() {
        return response;
    }
    public void setResponse(T response) {
        this.response = response;
    }

}
