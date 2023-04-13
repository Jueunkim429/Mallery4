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
    
    private val okHttpClient = OkHttpClient.Builder()
        .addInterceptor { chain ->
            val original2 = chain.request()
            val requestBuilder2 = original2.newBuilder()
                .addHeader("Authorization",AUTH)
                .method(original2.method(),original2.body())
            val request2 = requestBuilder2.build()
            chain.proceed(request2)
        }.build()
    

    // 최종 id, 회원가입, 로그인 인스턴스
    val instance: Api by lazy {
        val retrofit2 = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
            retrofit2.create(Api::class.java)
    }
    
    // 로그인 후 -> client 고유의 토큰값으로 client instance 만들기

}