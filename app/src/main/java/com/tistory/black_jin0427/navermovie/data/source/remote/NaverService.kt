package com.tistory.black_jin0427.navermovie.data.source.remote

import com.tistory.black_jin0427.navermovie.data.source.remote.model.Book
import com.tistory.black_jin0427.navermovie.data.source.remote.model.Movie
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface NaverService {

    @GET("search/movie.json")
    fun getMovie(
        @Query("query") query: String,
        @Query("display") display: Int = 10,
        @Query("start") start: Int = 1
    ): Single<Movie>

    @GET("search/book.json")
    fun getBook(
        @Query("query") query: String,
        @Query("display") display: Int = 10,
        @Query("start") start: Int = 1
    ): Single<Book>
}