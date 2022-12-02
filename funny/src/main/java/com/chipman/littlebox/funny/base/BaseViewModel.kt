package com.chipman.littlebox.funny.base

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

abstract class BaseViewModel : ViewModel()

@HiltViewModel
class EmptyViewModel @Inject constructor() : BaseViewModel()