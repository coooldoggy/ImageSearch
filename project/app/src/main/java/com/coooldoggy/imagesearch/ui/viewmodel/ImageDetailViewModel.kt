package com.coooldoggy.imagesearch.ui.viewmodel

import android.app.Application
import android.view.View
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.coooldoggy.imagesearch.framework.model.Documents


class ImageDetailViewModel(application: Application): AndroidViewModel(application) {
    companion object{
        private val TAG = ImageDetailViewModel::class.java.simpleName
    }

    val responseData = MutableLiveData<Documents?>()

    fun setData(){
        imageUrl.value = responseData.value?.imageUrl ?: responseData.value?.thumbNailUrl
        siteNameExist.value = !responseData.value?.displaySiteName.isNullOrEmpty()
        dateTimeExist.value = !responseData.value?.dateTime.isNullOrEmpty()
        siteNameVisibility.value = if (responseData.value?.displaySiteName.isNullOrEmpty()){
            View.GONE
        }else{
            View.VISIBLE
        }
        dateTimeVisibility.value =  if (responseData.value?.dateTime.isNullOrEmpty()){
            View.GONE
        }else{
            View.VISIBLE
        }
        dateTime.value = responseData.value?.dateTime
        siteName.value = responseData.value?.displaySiteName
    }

    val imageUrl = MutableLiveData("")
    val siteNameExist = MutableLiveData<Boolean>()
    val dateTimeExist = MutableLiveData<Boolean>()
    val siteNameVisibility = MutableLiveData<Int>()
    val dateTimeVisibility = MutableLiveData<Int>()
    val dateTime = MutableLiveData("")
    val siteName = MutableLiveData("")
}