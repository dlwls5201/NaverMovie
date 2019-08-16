package com.tistory.black_jin0427.navermovie.di

import com.tistory.black_jin0427.navermovie.constant.SchedulersProvider
import com.tistory.black_jin0427.navermovie.data.repository.BookRepository
import com.tistory.black_jin0427.navermovie.data.repository.MovieRepository
import com.tistory.black_jin0427.navermovie.domain.usecase.GetContentsUsecase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class UsecaseModule {

    @Provides
    @Singleton
    fun provideContentsUsecase(
        bookRepository: BookRepository,
        movieRepository: MovieRepository,
        schedulersProvider: SchedulersProvider
    ): GetContentsUsecase {
        return GetContentsUsecase(bookRepository, movieRepository, schedulersProvider)
    }
}