package com.chipman.littlebox.eyepetizer.ui.main.personal

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.chipman.littlebox.eyepetizer.databinding.FragmentPersonalBinding

/**
 * 我的
 */
class PersonalFragment : Fragment() {

    private var _binding: FragmentPersonalBinding? = null

    private val binding: FragmentPersonalBinding
        get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPersonalBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}