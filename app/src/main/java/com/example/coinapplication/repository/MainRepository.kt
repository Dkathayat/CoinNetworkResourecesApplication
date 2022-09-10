package com.example.coinapplication.repository

import com.example.coinapplication.network.RetrofitClient

class MainRepository() {

    suspend fun getAllCoins() = RetrofitClient.api.getAllCoins()
    suspend fun getAllCoinsDetails() = RetrofitClient.api.getCoinDetails()
}