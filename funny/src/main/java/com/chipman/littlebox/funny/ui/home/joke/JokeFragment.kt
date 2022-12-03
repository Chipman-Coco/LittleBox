package com.chipman.littlebox.funny.ui.home.joke

import android.os.Bundle
import androidx.fragment.app.viewModels
import com.chipman.littlebox.funny.base.BaseFragment
import com.chipman.littlebox.funny.base.EmptyViewModel
import com.chipman.littlebox.funny.databinding.FragmentJokeBinding
import com.chipman.littlebox.funny.ui.home.HomeFragment
import com.chipman.littlebox.funny.ui.home.HomeTabBean

class JokeFragment : BaseFragment<FragmentJokeBinding, EmptyViewModel>() {

    override val mViewModel: EmptyViewModel by viewModels()

    override fun FragmentJokeBinding.initView(savedInstanceState: Bundle?) {

    }

    companion object {
        @JvmStatic
        fun newInstance(tabBean: HomeTabBean) =
            JokeFragment().apply {
                arguments = Bundle().apply {
                    putParcelable(HomeFragment.KEY_CHILD_HOME_TAB_PARCELABLE, tabBean)
                }
            }
    }
}