package com.example.eventeasy.data.services.auth

import com.example.eventeasy.data.model.auth.SignUpRequest
import com.example.eventeasy.data.model.common.ApiResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST


interface ApiService {

    // Ruta para el registro de usuarios
    @POST("/auth/signup")
    fun signUp(@Body signUpRequest: SignUpRequest): Call<ApiResponse>
}
