package com.chipman.littlebox.funny.ui.home.attention

import android.os.Bundle
import androidx.fragment.app.viewModels
import com.chipman.littlebox.funny.base.BaseFragment
import com.chipman.littlebox.funny.base.EmptyViewModel
import com.chipman.littlebox.funny.databinding.FragmentAttentionBinding
import com.chipman.littlebox.funny.ui.home.HomeFragment
import com.chipman.littlebox.funny.ui.home.HomeTabBean


class AttentionFragment : BaseFragment<FragmentAttentionBinding, EmptyViewModel>() {

    override val mViewModel: EmptyViewModel by viewModels()

    override fun FragmentAttentionBinding.initView(savedInstanceState: Bundle?) {

    }

    companion object {
        @JvmStatic
        fun newInstance(tabBean: HomeTabBean) =
            AttentionFragment().apply {
                arguments = Bundle().apply {
                    putParcelable(HomeFragment.KEY_CHILD_HOME_TAB_PARCELABLE, tabBean)
                }
            }
    }
}