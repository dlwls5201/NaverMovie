package com.tistory.black_jin0427.navermovie.data.source.remote

import com.tistory.black_jin0427.navermovie.domain.model.Book
import com.tistory.black_jin0427.navermovie.domain.model.Movie
import io.reactivex.Single

class NaverRemoteDataSourceImpl(
    private val api: NaverService
) : NaverRemoteDataSource {

    override fun getMovie(query: String): Single<Book> =
        api.getMovie(query)
    /*.map {
        it.itemEntities.map { item ->
            MovieItem(
                item.title,
                item.image,
                item.director.replace("|", ", "),
                item.actor.replace("|", ", "),
                item.userRating.toString()
            )
        }
    }*/


    override fun getBook(query: String): Single<Movie> =
        api.getBook(query)
    /*.map {
        it.itemEntities.map { item ->
            BookItem(
                item.title, item.image
            )
        }
    }*/
}