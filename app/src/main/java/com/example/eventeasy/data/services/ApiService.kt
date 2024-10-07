package com.example.eventeasy.data.services

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

// Define las rutas de la API
interface ApiService {

    // Ruta para el registro de usuarios
    @POST("/auth/signup")
    fun signUp(@Body signUpRequest: SignUpRequest): Call<ApiResponse>

    // Otros endpoints 
}


data class SignUpRequest(
    val nombre: String,
    val apellido: String,
    val correo: String,
    val contrasena: String
)


