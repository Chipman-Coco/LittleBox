package com.chipman.common.exception

data class ApiException(
    val code: Int,
    override val message: String?
) : Exception(message)