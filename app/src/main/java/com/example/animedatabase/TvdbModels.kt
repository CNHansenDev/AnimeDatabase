package com.example.animedatabase

data class TvdbSearchResponse(
    val data: List<TvdbAnime>?
)

data class TvdbAnime(
    val id: Int,
    val seriesName: String?,
    val overview: String?,
    val banner: String?
)