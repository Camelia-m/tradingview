package com.capirow.tradingview.scraper

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface TradingViewApi {
    @GET("symbols/{symbol}/ideas/")
    fun getIdeas(
        @Path("symbol") symbol: String,
        @Query("component-data-only") componentDataOnly: Int = 1,
        @Query("sort") sort: String = "recent"
    ): Call<Map<String, Any>>
}