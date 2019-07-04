package com.keredgiantaio.techsavanna.redgiantaio.helpers;

import com.keredgiantaio.techsavanna.redgiantaio.methods.LoginResponse;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface ApiLoginService {
    @FormUrlEncoded
    @POST("POST/loginuser.php")
    Call<LoginResponse> sendLogin(@Field("telephone") String telephone,
                                     @Field("password") String password);
}
