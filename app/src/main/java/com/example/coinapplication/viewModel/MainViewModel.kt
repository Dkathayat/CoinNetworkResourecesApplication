package com.example.coinapplication.viewModel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.coinapplication.Dto.CoinDetails
import com.example.coinapplication.Dto.CoinResponse
import com.example.coinapplication.network.NetworkResource
import com.example.coinapplication.repository.MainRepository
import kotlinx.coroutines.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainViewModel(private val mainRepository: MainRepository) : ViewModel() {

    val coinResponse: MutableLiveData<NetworkResource<CoinResponse>> = MutableLiveData()
    val coinsResp: CoinResponse? = null
    val errorMessage = MutableLiveData<String>()
    val coinList = MutableLiveData<CoinResponse>()
    var job: Job? = null
    val loading = MutableLiveData<Boolean>()

    init {
        getCoins()
    }


    fun getCoins() = viewModelScope.launch {
        coinResponse.postValue(NetworkResource.Loading())
        val response = mainRepository.getAllCoins()
        coinResponse.postValue(handleCoinResponse(response))
    }

    private fun handleCoinResponse(response: Response<CoinResponse>): NetworkResource<CoinResponse> {
        if (response.isSuccessful) {
            response.body()?.let {
                return NetworkResource.Success(it)
            }
        }
        return NetworkResource.Error(response.message())
    }

}
//
//fun getAllCoins() {
//    job = CoroutineScope(Dispatchers.IO).launch {
//        val response = mainRepository.getAllCoins()
//        withContext(Dispatchers.Main) {
//            if (response.isSuccessful){
//                coinList.postValue(response.body())
//                loading.value = false
//            } else {
//                Log.e("ViewModel", response.message())
//            }
//        }
//    }
//}