package com.pedago2.Service;

import com.pedago2.Moel.Login.LoginResponse;
import com.pedago2.Moel.Register.RegisterResponse;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by firma on 26-Oct-17.
 */

public interface ApiInterface {

    @FormUrlEncoded
    @POST("user/register")
    @Headers({"tipe_output : 4"})
    Call<RegisterResponse> registerResponse(
            @Field("email")String email,
            @Field("password")String password,
            @Field("s_groupuser_id")String s_groupuser_id,
            @Field("isStudent")String isStudent
    );

    @FormUrlEncoded
    @POST("user/login")
    @Headers({"tipe_output : 4"})
    Call<LoginResponse> loginResponse(
            @Field("email")String email,
            @Field("password")String password,
            @Query("tipe_output")int tipe_output
    );

}
