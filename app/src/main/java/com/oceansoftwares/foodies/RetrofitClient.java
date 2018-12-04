package com.oceansoftwares.foodies;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {

    private static final String BASE_URL = "http://store.kagee.in/";
    private static RetrofitClient mData;
    private Retrofit retrofit;

    private RetrofitClient(){

        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public static synchronized RetrofitClient getData(){
        if (mData == null){
            mData = new RetrofitClient();
        }
        return mData;
    }

    public Api getApi(){
        return retrofit.create(Api.class);
    }
}
