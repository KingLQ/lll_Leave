package com.goldze.mvvmhabit.entity.request;

public class ChangePswRequestEntity {


    /**
     * oldPassWord : string
     * newPassWord : string
     */

    private String oldPassWord;
    private String newPassWord;

    public String getOldPassWord() {
        return oldPassWord;
    }

    public void setOldPassWord(String oldPassWord) {
        this.oldPassWord = oldPassWord;
    }

    public String getNewPassWord() {
        return newPassWord;
    }

    public void setNewPassWord(String newPassWord) {
        this.newPassWord = newPassWord;
    }
}
