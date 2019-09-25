package com.goldze.mvvmhabit.entity.response;

public class RegisterResponseEntity {

    /**
     * token : eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJqdGkiOiJiOTc5M2JmNi04NTk4LTQyZDMtOTg3OS01ODRjNDgxNjY5MzAiLCJpYXQiOiIxNTY3NjEyNTA0IiwibmJmIjoiMTU2NzYxMjUwNCIsImV4cCI6IjE1NzAyMDQ1MDQiLCJpc3MiOiJUU1dBcGkiLCJ1c2VySW5mbyI6IntcIlN0YWZmSWRcIjpcImI5NzkzYmY2LTg1OTgtNDJkMy05ODc5LTU4NGM0ODE2NjkzMFwiLFwiU3RhZmZOYW1lXCI6XCLlhbvnjKrlsI_og73miYtcIixcIk1vYmlsZVwiOlwiMTc3NTUxMDk0MTZcIixcIklzQWRtaW5cIjpmYWxzZSxcIkxhc3RWaXNpdFRpbWVcIjpcIjIwMTkvMDkvMDQgMjM6NTU6MDRcIixcIkV4cGlyZVRpbWVcIjpcIjIwMTkvMTAvMDQgMjM6NTU6MDRcIn0iLCJhdWQiOiJTT1AifQ.9e58QwaM3b4aT3wxi0hlcTgxT7kuBtSAgEUX-v2iljc
     * phone : 17755109416
     * nickName : 养猪小能手
     */

    private String token;
    private String phone;
    private String nickName;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }
}
