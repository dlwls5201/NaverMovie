package com.tistory.black_jin0427.navermovie.presentation.main.module

import com.tistory.black_jin0427.navermovie.di.scope.ActivityScope
import com.tistory.black_jin0427.navermovie.domain.usecase.GetContentsUsecase
import com.tistory.black_jin0427.navermovie.presentation.main.*
import com.tistory.black_jin0427.navermovie.presentation.main.adapter.BookAdapter
import com.tistory.black_jin0427.navermovie.presentation.main.adapter.MovieAdapter
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
class MainModule {

    @Provides
    @ActivityScope
    fun provideMainPresenter(
        view: MainActivity,
        getContentsUsecase: GetContentsUsecase
    ): MainContract.Presenter {
        return MainPresenter(view, getContentsUsecase)
    }

    /**
     * Constructor injection 으로 변경
     */
    /*@Provides
    @ActivityScope
    fun provideBookAdapter() = BookAdapter()

    @Provides
    @ActivityScope
    fun provideMovieAdapter() = MovieAdapter()*/
}