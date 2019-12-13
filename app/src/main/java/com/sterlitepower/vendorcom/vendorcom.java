package com.sterlitepower.vendorcom;

import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface vendorcom {
    @Headers("Content-Type: application/json")
    @POST("answer/")
    Call<Object> post_data(@Header ("auth") String token,
                           @Body String data);
}
