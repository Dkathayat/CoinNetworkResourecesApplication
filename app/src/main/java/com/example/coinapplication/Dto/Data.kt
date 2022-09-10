package com.example.coinapplication.Dto

data class Data(
    val itemsPerPage: Int,
    val list: List<CoinDetails>,
    val startIndex: Int,
    val totalItems: Int
)