package com.tistory.black_jin0427.navermovie.data.source.remote

import com.tistory.black_jin0427.navermovie.BuildConfig
import okhttp3.Interceptor

// https://github.com/square/okhttp/wiki/Interceptors
class AddHeaderInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain) = chain.run {
        proceed(
            request()
                .newBuilder()
                .addHeader("X-Naver-Client-Id", BuildConfig.MOVIE_CLIENT_ID)
                .addHeader("X-Naver-Client-Secret", BuildConfig.MOVIE_CLIENT_SECRET)
                .build()
        )
    }
}