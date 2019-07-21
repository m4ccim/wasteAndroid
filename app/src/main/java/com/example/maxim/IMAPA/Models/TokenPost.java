package com.example.maxim.IMAPA.Models;

public class TokenPost {
    private String access_token = "";
    private String email;
    private boolean isAdmin;
    private int cardId;
    private String cardOwnerName;

    public String getToken() {
        return access_token;
    }

    public String getUsername() {
        return email;
    }

    public boolean getIsAdmin() {
        return isAdmin;
    }

    public int getCardId() {
        return cardId;
    }

    public String getCardOwnerName() {
        return cardOwnerName;
    }
}
