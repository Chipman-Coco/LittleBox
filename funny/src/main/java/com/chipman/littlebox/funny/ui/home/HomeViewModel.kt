package com.chipman.littlebox.funny.ui.home

import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.chipman.littlebox.funny.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val homeRepository: HomeRepository
) : BaseViewModel() {

    val recommendList = homeRepository.getRecommendList(20).cachedIn(viewModelScope)
}