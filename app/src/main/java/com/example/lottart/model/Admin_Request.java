package com.example.lottart.model;

public class Admin_Request {
    String email,phonenumber1,total_ammount;

    public Admin_Request() {
    }

    public Admin_Request(String email, String phonenumber1, String total_ammount) {
        this.email = email;
        this.phonenumber1 = phonenumber1;
        this.total_ammount = total_ammount;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhonenumber1() {
        return phonenumber1;
    }

    public void setPhonenumber1(String phonenumber1) {
        this.phonenumber1 = phonenumber1;
    }

    public String getTotal_ammount() {
        return total_ammount;
    }

    public void setTotal_ammount(String total_ammount) {
        this.total_ammount = total_ammount;
    }
}
