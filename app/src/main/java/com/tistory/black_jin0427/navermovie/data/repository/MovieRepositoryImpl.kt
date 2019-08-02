package com.tistory.black_jin0427.navermovie.data.repository

import com.tistory.black_jin0427.navermovie.data.source.remote.NaverService
import com.tistory.black_jin0427.navermovie.data.source.remote.model.Movie
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * Repository
 * 로컬/서버 중 어떤 데이터를 불러올지 정의하고, 메모리 캐시를 포함한다.
 */
class MovieRepositoryImpl(
    private val api: NaverService
) : MovieRepository {

    override fun get(query: String): Single<Movie> {
        return api.getMovie(query)

    }
}