package com.chipman.littlebox.funny.util.ktx

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

inline fun ViewModel.launch(crossinline block: suspend CoroutineScope.() -> Unit) {
    viewModelScope.launch {
        block()
    }
}