package com.tistory.black_jin0427.navermovie.api

import com.tistory.black_jin0427.navermovie.api.model.Movie
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface NaverApi {

    //Headers -> AddHeaderInterceptor
    /*@Headers(
        "X-Naver-Client-Id:${BuildConfig.MOVIE_CLIENT_ID}",
        "X-Naver-Client-Secret:${BuildConfig.MOVIE_CLIENT_SECRET}"
    )*/
    @GET("search/movie.json")
    fun movie(
        @Query("query") query: String,
        @Query("display") display: Int = 10,
        @Query("start") start: Int = 1
    ): Single<Movie>
}