package com.coooldoggy.imagesearch

import android.app.Application
import android.content.Context
import android.util.Log
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ProcessLifecycleOwner

class ImageSearchApplication : Application(){
    companion object{
        val TAG = ImageSearchApplication::class.java.simpleName

        private var globalApplication: Application? = null

        fun getContext(): Context? {
            Log.d(TAG, "getContext $globalApplication")
            return globalApplication
        }
    }

    override fun onCreate() {
        super.onCreate()
        ProcessLifecycleOwner.get().lifecycle.addObserver(lifecycleObserver)
    }

    private val lifecycleObserver = object : DefaultLifecycleObserver{
        override fun onCreate(owner: LifecycleOwner) {
            super.onCreate(owner)
            globalApplication = this@ImageSearchApplication
        }
    }
}