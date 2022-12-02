package com.chipman.littlebox.funny.api.exception

data class ApiException(
    val code: Int,
    override val message: String?
) : Exception(message)