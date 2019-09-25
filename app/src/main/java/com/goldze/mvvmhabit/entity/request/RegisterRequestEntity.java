package com.goldze.mvvmhabit.entity.request;

public class RegisterRequestEntity {

    /**
     * phone : string
     * passWord : string
     * nickName : string
     * code : string
     */

    private String phone;
    private String passWord;
    private String nickName;
    private String code;

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
