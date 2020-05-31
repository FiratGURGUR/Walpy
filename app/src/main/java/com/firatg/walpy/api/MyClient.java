package com.firatg.walpy.api;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MyClient {

    private static final String BASE_URL="https://api.pexels.com/v1/";
    public static final String API_KEY="563492ad6f91700001000001806116a43227476ab32efc96fc3545ed";
    private static MyClient myClient;
    private static Retrofit retrofit;

    private MyClient(){
        retrofit = new Retrofit
                .Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public static synchronized MyClient getInstance(){
        if (myClient == null){
            myClient = new MyClient();
        }
        return myClient;
    }

    public MyApi getMyApi(){
        return retrofit.create(MyApi.class);
    }


}
