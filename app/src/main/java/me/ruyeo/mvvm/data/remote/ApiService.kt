package me.ruyeo.mvvm.data.remote

import me.ruyeo.mvvm.data.local.entity.News
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {


    @POST("news")
    suspend fun addNews(@Body news: News): News
}