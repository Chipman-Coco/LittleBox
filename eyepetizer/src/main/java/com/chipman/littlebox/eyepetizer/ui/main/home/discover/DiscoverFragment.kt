package com.chipman.littlebox.eyepetizer.ui.main.home.discover

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.chipman.littlebox.eyepetizer.databinding.FragmentRefreshLayoutBinding
import com.chipman.littlebox.eyepetizer.ui.main.home.HomeViewModel
import timber.log.Timber

/**
 * 首页-发现
 */
class DiscoverFragment : Fragment() {

    private var _binding: FragmentRefreshLayoutBinding? = null

    private val binding: FragmentRefreshLayoutBinding
        get() = _binding!!

    private val homeViewModel by viewModels<HomeViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRefreshLayoutBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        initData()
    }

    private fun initView() {
        binding.apply {
            swipeRefresh.setOnRefreshListener {
                Timber.d("refresh")
            }
            with(recyclerView) {

            }
        }
    }

    private fun initData() {
        homeViewModel.discovery.observe(viewLifecycleOwner) {

        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}