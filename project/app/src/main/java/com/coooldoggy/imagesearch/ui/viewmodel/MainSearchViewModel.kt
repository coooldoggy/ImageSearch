package com.coooldoggy.imagesearch.ui.viewmodel

import android.app.Application
import android.os.CountDownTimer
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.coooldoggy.imagesearch.framework.api.ApiManager
import kotlinx.coroutines.launch

class MainSearchViewModel(application: Application): AndroidViewModel(application) {
    companion object{
        private val TAG = MainSearchViewModel::class.java.simpleName
    }

    var countDownTimer: CountDownTimer? = null
    val queryString = MutableLiveData<String>()

    fun startTimer(){
        countDownTimer = object : CountDownTimer((1 * 60 * 1000).toLong(), 1000){
            override fun onTick(p0: Long) {
                Log.d(TAG, "onTick $p0")
            }

            override fun onFinish() {
                queryImage()
            }
        }
    }

    fun queryImage(){
        viewModelScope.launch {
            if (queryString.value.isNullOrEmpty()) return@launch
            countDownTimer?.cancel()
            val result = ApiManager.queryImage(queryString.value ?: "")
            if (result.isSuccessful){
                Log.d(TAG, "queryImage result ${result.body()}")
            }
        }
    }
}