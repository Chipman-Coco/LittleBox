package com.chipman.littlebox.funny.base

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

abstract class BaseViewModel : ViewModel() {

    companion object {

        const val DEFAULT_PAGE_SIZE = 20    // 默认pageSize
    }
}

@HiltViewModel
class EmptyViewModel @Inject constructor() : BaseViewModel()