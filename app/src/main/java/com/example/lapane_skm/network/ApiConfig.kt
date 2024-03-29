package com.example.lapane_skm.network

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


// class ini fungsinya sama seperti class servicegenerator, base ketika menggunakan library
// retrofit2
class ApiConfig {


   // val base_url = "http://192.168.43.110:8080/e-skm/api/"
    val base_url = "http://b5c12c63.ngrok.io/website/e-skm/api/"


    private fun retrofit() : Retrofit {


        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY

        val httpClient = OkHttpClient.Builder()
            .connectTimeout(60, TimeUnit.SECONDS)
            .readTimeout(60L, TimeUnit.SECONDS)
            .writeTimeout(60L, TimeUnit.SECONDS)
            .addInterceptor(interceptor)
            .build()

        return Retrofit.Builder()
            .baseUrl(base_url)
            .client(httpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    fun instance() : ApiInterface {
        return retrofit().create(ApiInterface::class.java)
    }
}