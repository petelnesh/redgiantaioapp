package com.keredgiantaio.techsavanna.redgiantaio.helpers;

import com.keredgiantaio.techsavanna.redgiantaio.methods.RegisterResponse;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface ApiRegisterService {
    @FormUrlEncoded
    @POST("POST/registerone.php")
    Call<RegisterResponse> sendRegister(@Field("nameofperson") String nameofperson,
                                       @Field("telephone") String telephone,
                                       @Field("email") String email,
                                       @Field("password") String password,
                                       @Field("confirmpassword") String confirmpassword,
                                       @Field("weight") String weight);
}
