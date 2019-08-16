package com.tistory.black_jin0427.navermovie.di

import com.tistory.black_jin0427.navermovie.data.source.remote.NaverRemoteDataSource
import com.tistory.black_jin0427.navermovie.data.source.remote.NaverRemoteDataSourceImpl
import com.tistory.black_jin0427.navermovie.data.source.remote.NaverService
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.CallAdapter
import retrofit2.Converter
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
class RemoteDataSourceModule {

    private val baseUrl = "https://openapi.naver.com/v1/"

    @Provides
    @Singleton
    fun provideRemoteDataSource(api: NaverService): NaverRemoteDataSource {
        return NaverRemoteDataSourceImpl(api)
    }

    @Provides
    @Singleton
    fun provideGithubApi(
        okHttpClient: OkHttpClient,
        callAdapter: CallAdapter.Factory,
        factory: Converter.Factory
    ): NaverService {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(okHttpClient)
            .addCallAdapterFactory(callAdapter)
            .addConverterFactory(factory)
            .build()
            .create(NaverService::class.java)
    }
}