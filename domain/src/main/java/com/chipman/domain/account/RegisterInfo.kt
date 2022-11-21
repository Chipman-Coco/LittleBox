package com.chipman.domain.account

data class RegisterInfo(
    val username: String,
    val password: String,
    val confirmPassword: String
)
