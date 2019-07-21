package com.example.maxim.IMAPA;

import com.example.maxim.IMAPA.Models.TokenPost;

public  class Global {
    public static TokenPost token = new TokenPost();
    public static String GetBearer(){
        return "bearer " + token.getToken();
    }
}
