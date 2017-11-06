package com.example.administrator.myhome.bean;


public class User  {

    private String loginId;
    private String password;

    public User() {
        this.loginId = loginId;
        this.password = password;
    }



    public String getLoginId() {
        return loginId;
    }

    public void setLoginId(String phone_number) {
        this.loginId = phone_number;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "User{" +
                "phone_number='" + loginId + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
