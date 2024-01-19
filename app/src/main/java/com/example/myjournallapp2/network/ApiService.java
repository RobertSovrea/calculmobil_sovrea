package com.example.myjournallapp2.network;

import com.example.myjournallapp2.network.QuoteResponse;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiService {
    @GET("random")
    Call<QuoteResponse> getMotivationalQuote();
}
