package com.example.myjournallapp2.activities;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myjournallapp2.R;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;

public class SecondActivity extends AppCompatActivity {

    private static final String QUOTE_API_URL = "https://quotes-inspirational-quotes-motivational-quotes.p.rapidapi.com/quote?token=ipworld.info";
    private static final String PREFS_NAME = "MyPrefsFile";
    private TextView textViewQuote;
    private OkHttpClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        textViewQuote = findViewById(R.id.textViewQuote);

        // Create a logging interceptor
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BASIC);

        // Build the OkHttpClient with the logging interceptor
        client = new OkHttpClient.Builder()
                .addInterceptor(loggingInterceptor)
                .build();

        fetchQuote();
    }

    private void fetchQuote() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Request request = new Request.Builder()
                            .url(QUOTE_API_URL)
                            .get()
                            .addHeader("X-RapidAPI-Key", "9219fe7cecmsh8e66b06e013c5c0p15e2cajsn9630df7d7241")
                            .addHeader("X-RapidAPI-Host", "quotes-inspirational-quotes-motivational-quotes.p.rapidapi.com")
                            .build();

                    try (Response response = client.newCall(request).execute()) {
                        if (response.isSuccessful() && response.body() != null) {
                            final String quoteText = response.body().string();
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    textViewQuote.setText(quoteText);
                                    saveQuote(quoteText);
                                }
                            });
                        }
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    private void saveQuote(String quote) {
        SharedPreferences sharedPref = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString("saved_quote", quote);
        editor.apply();
    }
}
