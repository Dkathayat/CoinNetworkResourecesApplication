package com.example.coinapplication.network

import com.example.coinapplication.Dto.CoinDetails
import com.example.coinapplication.Dto.CoinResponse
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET

interface RetrofitApi {

    @GET("coinlist")
    suspend fun getAllCoins(): Response<CoinResponse>

    @GET("coinlist")
    suspend fun getCoinDetails(): Response<List<CoinDetails>>

}