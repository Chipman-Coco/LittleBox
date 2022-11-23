package com.chipman.littlebox.ui.home.child.explore

import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.chipman.common.base.BaseViewModel
import com.chipman.data.repository.wanandroid.HomeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ExploreViewModel @Inject constructor(repository: HomeRepository) : BaseViewModel() {

    /**
     * 首页列表数据流
     */
    val articlesFlow = repository.getArticlePageList(20).cachedIn(viewModelScope)

}