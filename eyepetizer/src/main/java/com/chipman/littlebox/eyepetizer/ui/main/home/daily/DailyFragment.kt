package com.chipman.littlebox.eyepetizer.ui.main.home.daily

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.chipman.littlebox.eyepetizer.R
import com.chipman.littlebox.eyepetizer.databinding.FragmentRefreshLayoutBinding

/**
 * 首页-日报
 */
class DailyFragment : Fragment() {

    private var _binding: FragmentRefreshLayoutBinding? = null

    private val binding: FragmentRefreshLayoutBinding
        get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_daily, container, false)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}