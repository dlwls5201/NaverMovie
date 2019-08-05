package com.tistory.black_jin0427.navermovie.data.repository

import com.tistory.black_jin0427.navermovie.domain.model.MovieEntity
import io.reactivex.Single

interface MovieRepository {

    fun get(query: String): Single<MovieEntity>
}