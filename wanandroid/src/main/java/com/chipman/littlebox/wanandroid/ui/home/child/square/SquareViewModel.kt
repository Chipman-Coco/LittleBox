package com.chipman.littlebox.wanandroid.ui.home.child.square

import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.chipman.common.base.BaseViewModel
import com.chipman.data.repository.wanandroid.HomeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SquareViewModel @Inject constructor(homeRepository: HomeRepository) : BaseViewModel() {

    val collectSquareFlow = homeRepository.getSquarePageList(20).cachedIn(viewModelScope)

}