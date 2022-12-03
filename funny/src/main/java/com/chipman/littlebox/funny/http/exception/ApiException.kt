package com.chipman.littlebox.funny.http.exception

data class ApiException(
    val code: Int,
    override val message: String?
) : Exception(message)