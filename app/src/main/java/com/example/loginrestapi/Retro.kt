package com.example.loginrestapi

import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class Retro {
    fun getRetrofitClientInstance(): Retrofit{
        val gson = GsonBuilder().setLenient().create()
        return Retrofit.Builder()
            .baseUrl("http://192.168.188.210:5000/")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
    }
}