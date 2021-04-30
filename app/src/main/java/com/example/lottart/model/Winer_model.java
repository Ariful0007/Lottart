package com.example.lottart.model;

public class Winer_model {
    String name,email,coin,uuid;

    public Winer_model() {
    }

    public Winer_model(String name, String email, String coin, String uuid) {
        this.name = name;
        this.email = email;
        this.coin = coin;
        this.uuid = uuid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCoin() {
        return coin;
    }

    public void setCoin(String coin) {
        this.coin = coin;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }
}
