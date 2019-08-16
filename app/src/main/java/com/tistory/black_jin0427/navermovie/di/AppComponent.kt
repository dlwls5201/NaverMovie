package com.tistory.black_jin0427.navermovie.di

import android.app.Application
import com.tistory.black_jin0427.navermovie.BaseApplication
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AppModule::class, NetworkModule::class, RepositoryModule::class, RemoteDataSourceModule::class, RxModule::class, UsecaseModule::class,
        ActivityBindingModule::class,

        AndroidSupportInjectionModule::class
    ]
)
interface AppComponent : AndroidInjector<BaseApplication> {

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(app: Application): Builder

        fun build(): AppComponent
    }
}