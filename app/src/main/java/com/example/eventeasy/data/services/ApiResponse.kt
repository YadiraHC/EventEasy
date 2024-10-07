package com.example.eventeasy.data.services

data class ApiResponse(
    val status: String,
    val message: String,
    val data: Any? = null
)
