package com.capirow.tradingview.scraper

import okhttp3.OkHttpClient
import org.springframework.stereotype.Component
import retrofit2.Retrofit
import retrofit2.converter.jackson.JacksonConverterFactory
import java.util.concurrent.TimeUnit

@Component
class TradingViewClient {

    private val client = OkHttpClient.Builder()
        .connectTimeout(10, TimeUnit.SECONDS)
        .readTimeout(10, TimeUnit.SECONDS)
        .build()
    private val retrofit = Retrofit.Builder()
        .baseUrl("https://www.tradingview.com/") // base URL
        .client(client)
        .addConverterFactory(JacksonConverterFactory.create())
        .build()

    val api: TradingViewApi = retrofit.create(TradingViewApi::class.java)
}