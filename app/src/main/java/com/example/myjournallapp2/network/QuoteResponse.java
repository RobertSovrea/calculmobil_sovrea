package com.example.myjournallapp2.network;

import com.google.gson.annotations.SerializedName;

public class QuoteResponse {
    @SerializedName("q")
    private String quote;

    @SerializedName("a")
    private String author;

    public String getQuote() {
        return quote;
    }

    public String getAuthor() {
        return author;
    }
}
