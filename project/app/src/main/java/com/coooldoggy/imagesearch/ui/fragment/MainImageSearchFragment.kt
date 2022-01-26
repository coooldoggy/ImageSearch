package com.coooldoggy.imagesearch.ui.fragment

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import androidx.core.widget.doAfterTextChanged
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.navGraphViewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.coooldoggy.imagesearch.R
import com.coooldoggy.imagesearch.databinding.FragmentMainImageSearchBinding
import com.coooldoggy.imagesearch.ui.viewmodel.MainSearchViewModel
import kotlinx.coroutines.flow.collectLatest

class MainImageSearchFragment: Fragment() {
    companion object{
        private val TAG = MainImageSearchFragment::class.java.simpleName
    }

    private var viewDataBinding: FragmentMainImageSearchBinding? = null
    private val viewModel by navGraphViewModels<MainSearchViewModel>(R.id.nav_graph_main)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewDataBinding = DataBindingUtil.inflate<FragmentMainImageSearchBinding>(inflater, R.layout.fragment_main_image_search, container, false).apply {
            model = viewModel
            lifecycleOwner = this@MainImageSearchFragment
        }
        return viewDataBinding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(viewDataBinding){
            if (this == null) return@with
            etSearch.apply {
                doAfterTextChanged {
                    Log.d(TAG, "$it")
                    viewModel.queryString.value = it.toString()
                    viewModel.startTimer()
                }

                setOnEditorActionListener { v, actionId, _ ->
                    if (EditorInfo.IME_ACTION_DONE == actionId) {
                        hideSoftInputMethod(v)
                        viewModel.queryImage()
                        true
                    }else{
                        false
                    }
                }
            }
            clEditText.setOnClickListener {
                showSoftInputMethod(it)
            }

            rvSearchList.apply {
                adapter = viewModel.adapter
                layoutManager = GridLayoutManager(requireContext(), 3)
            }

        }

        lifecycleScope.launchWhenCreated {
            viewModel.flow.collectLatest { pagingData ->
                viewModel.adapter.submitData(pagingData)
            }
        }
    }

    private fun hideSoftInputMethod(view: View?) {
        Log.d(TAG, "hideSoftInputMethod")

        kotlin.runCatching {
            val imm = context?.getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager
            imm?.let {
                val targetView = view ?: activity?.currentFocus
                if(targetView == null) {
                    Log.d(TAG, "view is null")
                    return@let
                }

                it.hideSoftInputFromWindow(targetView.windowToken, 0)
            }
        }
    }

    private fun showSoftInputMethod(view: View?) {
        Log.d(TAG, "showSoftInputMethod")

        kotlin.runCatching {
            val imm = context?.getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager
            imm?.let {
                val targetView = view ?: activity?.currentFocus
                if(targetView == null) {
                    Log.d(TAG, "view is null")
                    return@let
                }

                it.showSoftInput(targetView, InputMethodManager.SHOW_IMPLICIT)
            }
        }
    }
}