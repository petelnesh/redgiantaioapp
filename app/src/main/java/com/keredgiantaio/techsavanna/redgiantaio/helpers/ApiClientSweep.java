package com.keredgiantaio.techsavanna.redgiantaio.helpers;

import com.keredgiantaio.techsavanna.redgiantaio.app.AppConfig;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClientSweep {
    public static Retrofit retrofit;

    public static Retrofit getApiSweep(){
        if (retrofit==null){

            retrofit = new Retrofit.Builder()
                    .baseUrl(AppConfig.SERVER_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}
