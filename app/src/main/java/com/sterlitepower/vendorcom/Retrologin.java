package com.sterlitepower.vendorcom;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface retrologin {
    @Headers("Content-Type: application/json")
    @POST("login/")
    Call<Object> sign(@Body String login_in);
}