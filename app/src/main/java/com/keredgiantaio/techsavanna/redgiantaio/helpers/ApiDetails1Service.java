package com.keredgiantaio.techsavanna.redgiantaio.helpers;

import com.keredgiantaio.techsavanna.redgiantaio.methods.DetailsOneResponse;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface ApiDetails1Service {

    @FormUrlEncoded
    @POST("POST/detailsone.php")
    Call<DetailsOneResponse> sendRegister(@Field("nameofshop") String nameofshop,
                                          @Field("telephone") String telephone,
                                          @Field("sale") String sale,
                                          @Field("qauntity") String qauntity,
                                          @Field("route") String route,
                                          @Field("lat") String lat,
                                          @Field("lon") String lon);
}
