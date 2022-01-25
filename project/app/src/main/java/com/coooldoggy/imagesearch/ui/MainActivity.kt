package com.coooldoggy.imagesearch.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.coooldoggy.imagesearch.R
import com.coooldoggy.imagesearch.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    companion object{
        private val TAG = MainActivity::class.java.simpleName
    }

    private var binding: ActivityMainBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding?.root)
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }
}