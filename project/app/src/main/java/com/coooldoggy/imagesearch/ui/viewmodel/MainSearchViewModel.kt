package com.coooldoggy.imagesearch.ui.viewmodel

import android.app.Application
import android.os.CountDownTimer
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.coooldoggy.imagesearch.framework.api.ApiManager
import com.coooldoggy.imagesearch.framework.service.ImageSearchService
import com.coooldoggy.imagesearch.ui.adapter.ImageViewAdapter
import com.coooldoggy.imagesearch.ui.adapter.paging.DocumentComparator
import com.coooldoggy.imagesearch.ui.adapter.paging.ImagePagingSource
import kotlinx.coroutines.launch

class MainSearchViewModel(application: Application): AndroidViewModel(application) {
    companion object{
        private val TAG = MainSearchViewModel::class.java.simpleName
    }

    var countDownTimer: CountDownTimer? = null
    val queryString = MutableLiveData<String>()
    val adapter = ImageViewAdapter(DocumentComparator)
    val flow = Pager(
        PagingConfig(pageSize = 30)
    ){
        ImagePagingSource(ImageSearchService.createService(ImageSearchService::class.java), query = queryString.value ?: "")
    }.flow.cachedIn(viewModelScope)

    fun startTimer(){
        countDownTimer = object : CountDownTimer((1 * 1000).toLong(), 1000){
            override fun onTick(p0: Long) {
            }

            override fun onFinish() {
                queryImage()
            }
        }
        countDownTimer?.start()
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