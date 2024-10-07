package com.example.eventeasy.data.services.common

import okhttp3.Interceptor
import okhttp3.Response

// Interceptor para agregar el encabezado de idioma a cada solicitud
class LanguageInterceptor(private val languageCode: String) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request().newBuilder()
            .addHeader("Accept-Language", languageCode)
            .build()
        return chain.proceed(request)
    }
}
