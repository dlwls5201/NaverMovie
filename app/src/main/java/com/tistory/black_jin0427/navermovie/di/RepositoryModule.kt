package com.tistory.black_jin0427.navermovie.di

import com.tistory.black_jin0427.navermovie.data.repository.BookRepository
import com.tistory.black_jin0427.navermovie.data.repository.BookRepositoryImpl
import com.tistory.black_jin0427.navermovie.data.repository.MovieRepository
import com.tistory.black_jin0427.navermovie.data.repository.MovieRepositoryImpl
import com.tistory.black_jin0427.navermovie.data.source.remote.NaverRemoteDataSource
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RepositoryModule {

    @Provides
    @Singleton
    fun provideBookRepository(remote: NaverRemoteDataSource): BookRepository {
        return BookRepositoryImpl(remote)
    }

    @Provides
    @Singleton
    fun provideMovieRepository(remote: NaverRemoteDataSource): MovieRepository {
        return MovieRepositoryImpl(remote)
    }
}