package com.chipman.common.base

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

abstract class BaseViewModel : ViewModel() {

    companion object {

        const val DEFAULT_PAGE_SIZE = 20
    }
}

@HiltViewModel
class EmptyViewModel @Inject constructor() : BaseViewModel()