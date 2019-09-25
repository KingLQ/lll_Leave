package com.goldze.mvvmhabit.entity.response;

public class LoginResponseEntity {

    /**
     * token : eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJqdGkiOiJiOTc5M2JmNi04NTk4LTQyZDMtOTg3OS01ODRjNDgxNjY5MzAiLCJpYXQiOiIxNTY4Mzg5MTY0IiwibmJmIjoiMTU2ODM4OTE2NCIsImV4cCI6IjE1NzA5ODExNjQiLCJpc3MiOiJUU1dBcGkiLCJ1c2VySW5mbyI6IntcIlN0YWZmSWRcIjpcImI5NzkzYmY2LTg1OTgtNDJkMy05ODc5LTU4NGM0ODE2NjkzMFwiLFwiU3RhZmZOYW1lXCI6XCLlhbvnjKrlsI_og73miYtcIixcIk1vYmlsZVwiOlwiMTc3NTUxMDk0MTZcIixcIklzQWRtaW5cIjpmYWxzZSxcIkxhc3RWaXNpdFRpbWVcIjpcIjIwMTkvMDkvMTMgMjM6Mzk6MjRcIixcIkV4cGlyZVRpbWVcIjpcIjIwMTkvMTAvMTMgMjM6Mzk6MjRcIn0iLCJhdWQiOiJTT1AifQ._plEr042v6aXPrPVMLHfpkWx5Qw4kFfmb_RTa4nfFTs
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
