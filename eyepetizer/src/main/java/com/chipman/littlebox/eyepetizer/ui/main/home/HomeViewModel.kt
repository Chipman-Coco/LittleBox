package com.chipman.littlebox.eyepetizer.ui.main.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.chipman.common.base.BaseViewModel
import com.chipman.data.repository.eyepetizer.HomeRepository
import com.chipman.model.eyepetizer.Discovery
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@HiltViewModel
class HomeViewModel constructor(
    private val homeRepository: HomeRepository
) : BaseViewModel() {

    private var _discover: MutableLiveData<List<Discovery.Item>> = MutableLiveData()

    val discovery: LiveData<List<Discovery.Item>>
        get() = _discover

    fun getDiscoverList() {
        viewModelScope.launch {
            homeRepository.getDiscover().collectLatest {
                _discover.value = it
            }
        }
    }

}