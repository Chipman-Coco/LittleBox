package com.chipman.littlebox.funny.util.ktx

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.*

/**
 * 开启一个线程调度模式为[Dispatchers.IO]的协程 有默认的异常处理器
 *
 * @param exceptionHandler 异常处理器
 * @param block 协程体
 * @return Job
 */
fun ViewModel.launchIO(
    exceptionHandler: CoroutineExceptionHandler = CoroutineExceptionHandler { coroutineContext, throwable ->
        throwable.printStackTrace()
    },
    block: suspend CoroutineScope.() -> Unit
): Job = viewModelScope.launch(Dispatchers.IO + exceptionHandler, block = block)

/**
 * 开启一个线程调度模式为[Dispatchers.Default]的协程 有默认的异常处理器
 */
fun ViewModel.launchDefault(
    exceptionHandler: CoroutineExceptionHandler = CoroutineExceptionHandler { coroutineContext, throwable ->
        throwable.printStackTrace()
    },
    block: suspend CoroutineScope.() -> Unit
): Job = viewModelScope.launch(Dispatchers.Default + exceptionHandler, block = block)

/**
 * 开启一个线程调度模式为[Dispatchers.Main]的协程 有默认的异常处理器
 */
fun ViewModel.launchMain(
    exceptionHandler: CoroutineExceptionHandler = CoroutineExceptionHandler { coroutineContext, throwable ->
        throwable.printStackTrace()
    },
    block: suspend CoroutineScope.() -> Unit
): Job = viewModelScope.launch(Dispatchers.Main + exceptionHandler, block = block)