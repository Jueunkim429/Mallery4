package com.example.mallery4.retrofit

import android.util.Base64
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

object RetrofitClient {

    private val AUTH = "Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhZG1pbiB1c2VyIiwiYXV0aCI6IlJPTEVfVVNFUiIsImV4cCI6MTY4MDI3NzYzNn0.8a1TJ_DYAJN8_vkeRstVfjXW4lS9Wf3mItvqasefTFc"
    // server start 주소
    private const val BASE_URL = "http://ec2-3-39-19-70.ap-northeast-2.compute.amazonaws.com:8080"
    //private const val BASE_URL = "http://localhost:8080/"

//    private val okHttpClient: OkHttpClient by lazy {
//        OkHttpClient.Builder()
//            .addInterceptor(HttpLoggingInterceptor().apply {
//                level = HttpLoggingInterceptor.Level.BODY
//            })
//            .build()
//    }

    private val okHttpClient = OkHttpClient.Builder()
        .addInterceptor { chain ->
            val original = chain.request()
            val requestBuilder = original.newBuilder()
                .addHeader("Authorization",AUTH)
                .method(original.method(),original.body())
            val request = requestBuilder.build()
            chain.proceed(request)
        }.build()


    val instance: Api by lazy {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()

            retrofit.create(Api::class.java)
    }
}