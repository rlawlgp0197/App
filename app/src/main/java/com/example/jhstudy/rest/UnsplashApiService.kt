package com.example.jhstudy.rest


import com.example.jhstudy.model.UnsplashResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface UnsplashApiService {

    @GET("photos/random/?client_id=kdLGpMaKXrDJYsil5E9rwK9kpje6gt8nSBiMsrUr5Us/&count=30")
    suspend fun getRandomPhotos(@Query("query") query: String?): Response<UnsplashResponse>
}