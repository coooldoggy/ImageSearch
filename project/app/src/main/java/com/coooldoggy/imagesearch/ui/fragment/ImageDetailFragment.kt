package com.coooldoggy.imagesearch.ui.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import androidx.navigation.navGraphViewModels
import com.coooldoggy.imagesearch.R
import com.coooldoggy.imagesearch.databinding.FragmentImageDetailBinding
import com.coooldoggy.imagesearch.ui.viewmodel.ImageDetailViewModel

class ImageDetailFragment: Fragment() {
    companion object{
        private val TAG = ImageDetailFragment::class.java.simpleName
    }

    private var viewDataBinding: FragmentImageDetailBinding? = null
    private val viewModel by navGraphViewModels<ImageDetailViewModel>(R.id.nav_graph_main)
    private val requestArgs by navArgs<ImageDetailFragmentArgs>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "$requestArgs")
        viewModel.responseData.value = requestArgs.imageData
        viewModel.setData()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        viewDataBinding = DataBindingUtil.inflate<FragmentImageDetailBinding>(inflater, R.layout.fragment_image_detail, container,false).apply {
            model = viewModel
            lifecycleOwner = viewLifecycleOwner
        }
        return viewDataBinding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}