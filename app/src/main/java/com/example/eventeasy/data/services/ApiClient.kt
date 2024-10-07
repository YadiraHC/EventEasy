package com.example.eventeasy.data.services
import com.example.eventeasy.BuildConfig

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiClient {
    private var retrofit: Retrofit? = null

    fun getClient(languageCode: String): Retrofit {
        if (retrofit == null) {
            val client = OkHttpClient.Builder()
                .addInterceptor(LanguageInterceptor(languageCode))
                .build()

            retrofit = Retrofit.Builder()
                .baseUrl(BuildConfig.API_BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }
        return retrofit!!
    }
}
