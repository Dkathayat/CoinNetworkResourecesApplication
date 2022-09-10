package com.example.coinapplication.utils

import com.example.coinapplication.Dto.CoinDetails

object CoinDataShort {

    fun sortData(listCoins: List<CoinDetails>): List<CoinDetails> {
        val name = arrayListOf<String>()
        val mappedDetails = arrayListOf<CoinDetails>()

        listCoins.forEachIndexed { index, coinDetails ->
            if (name.contains(coinDetails.name.substringBefore(" ")).not())
                coinDetails.name?.substringBefore(" ").let {
                    if (it != null) {
                        name.add(it)
                    }
                }
        }
        return mappedDetails
    }
}