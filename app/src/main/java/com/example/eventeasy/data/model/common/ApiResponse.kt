package com.example.eventeasy.data.model.common

data class ApiResponse(
    val status: String,
    val message: String,
    val data: Any? = null
)
