package com.example.eventeasy.data.model.auth

data class SignUpRequest(
    val nombre: String,
    val apellido: String,
    val correo: String,
    val contrasena: String
)