package com.tistory.black_jin0427.navermovie.data.repository

import com.tistory.black_jin0427.navermovie.data.source.remote.model.Movie
import io.reactivex.Single

/**
 * 1. 데이터를 불러오는 록직을 분리시켜 관리
 * 2. 하나의 Repository는 하나의 Domain을 담당
 * 3. 추상화를 통해 테스트에 용이
 */
interface MovieRepository {

    fun get(query: String): Single<Movie>
}