package com.keredgiantaio.techsavanna.redgiantaio.helpers;

import com.keredgiantaio.techsavanna.redgiantaio.methods.RegisterTwoResponse;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface ApiRegisterTwoService {
    @FormUrlEncoded
    @POST("POST/registertwo.php")
    Call<RegisterTwoResponse> sendRegisterTwo(@Field("nameofperson") String nameofperson,
                                              @Field("telephone") String telephone,
                                              @Field("idnumber") String idnumber,
                                              @Field("gender") String gender,
                                              @Field("age") String age,
                                              @Field("height") String height,
                                              @Field("education") String education);
}
