package com.example.animedatabase

import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

const val TVDB_BASE_URL = "https://api.thetvdb.com/"

interface TvdbApiService {
    @GET("search/series")
    suspend fun searchAnime(
        @Header("Authorization") token: String,
        @Query("name") animeName: String
    ): TvdbSearchResponse
}

object TvdbApi {
    val retrofitService: TvdbApiService by lazy {
        Retrofit.Builder()
            .baseUrl(TVDB_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(TvdbApiService::class.java)
    }
}