package com.example.parcial2

import retrofit2.Response
import retrofit2.http.GET

interface ApiService {
    @GET("drivers.json")
    suspend fun getDrivers(): Response<F1Response>
}
