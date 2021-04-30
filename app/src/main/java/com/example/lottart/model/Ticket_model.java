package com.example.lottart.model;

public class Ticket_model {
    String email,name,coin;

    public Ticket_model() {
    }

    public Ticket_model(String email, String name, String coin) {
        this.email = email;
        this.name = name;
        this.coin = coin;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCoin() {
        return coin;
    }

    public void setCoin(String coin) {
        this.coin = coin;
    }
}
