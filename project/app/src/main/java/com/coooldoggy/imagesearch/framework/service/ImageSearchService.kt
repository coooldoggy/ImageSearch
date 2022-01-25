package com.coooldoggy.imagesearch.framework.service

import android.util.Log
import com.coooldoggy.imagesearch.BuildConfig
import com.coooldoggy.imagesearch.framework.constant.KAKAO_BASE_URL
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

interface ImageSearchService {

    companion object{
        val TAG = ImageSearchService::class.java.simpleName

        fun <S> createService(serviceClass: Class<S>): S {
            return buildRetrofitApi().create(serviceClass)
        }

        private fun buildRetrofitApi(): Retrofit {
            return Retrofit.Builder()
                .baseUrl(KAKAO_BASE_URL)
                .client(provideOkHttpClient(provideLoggingInterceptor()))
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }

        private fun provideOkHttpClient(interceptor: HttpLoggingInterceptor): OkHttpClient {
            val builder = OkHttpClient.Builder()
            builder.addInterceptor(interceptor)
            return builder.build()
        }

        private fun provideLoggingInterceptor(): HttpLoggingInterceptor {
            val interceptor = HttpLoggingInterceptor {
                Log.d("GithubIssueService", it)
            }
            interceptor.level = if (BuildConfig.DEBUG) {
                HttpLoggingInterceptor.Level.BODY
            }else{
                HttpLoggingInterceptor.Level.NONE
            }
            return interceptor
        }
    }

}