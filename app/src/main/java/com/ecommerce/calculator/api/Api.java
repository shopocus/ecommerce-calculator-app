package com.ecommerce.calculator.api;

import com.ecommerce.calculator.models.DefaultResponse;
import com.ecommerce.calculator.models.LoginResponse;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface Api {


    @FormUrlEncoded
    @POST("signup")
    Call<DefaultResponse> signup(
            @Field("name") String name,
            @Field("email") String email,
            @Field("mobile_no") String mobile_no,
            @Field("password") String password
    );

    @FormUrlEncoded
    @POST("login")
    Call<LoginResponse> login(
            @Field("email") String email,
            @Field("password") String password
    );

    /*@FormUrlEncoded
    @PUT("updateuser/{id}")
    Call<LoginResponse> updateUser(
            @Field("email") String email,
            @Field("name") String name,
            @Field("mobile") String mobile_no
    );

    @FormUrlEncoded
    @PUT("updatepassword")
    Call<DefaultResponse> updatePassword(
            @Field("currentpassword") String currentpassword,
            @Field("newpassword") String newpassword,
            @Field("email") String email
    );*/

}
