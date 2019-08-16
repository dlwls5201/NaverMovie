package com.tistory.black_jin0427.navermovie.di

import com.tistory.black_jin0427.navermovie.di.scope.ActivityScope
import com.tistory.black_jin0427.navermovie.presentation.main.MainActivity
import com.tistory.black_jin0427.navermovie.presentation.main.MainContract
import com.tistory.black_jin0427.navermovie.presentation.main.module.MainModule
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBindingModule {

    /**
     *  ContributesAndroidInjector
     *  1. 반환 타입을 통해 해당 Activity의 SubComponent를 생성합니다.
     *  2. modules에 SubComponent와 연결할 Module을 정의합니다.
     *
     *  원래는 SubComponent는 어노테이션을 활용하여 직접 만들었어야 했지만
     *  ContributesAndroidInjector를 활용하여 Module에서 자동으로 생성할 수 있습니다.
     */
    @ActivityScope
    @ContributesAndroidInjector(modules = [MainModule::class])
    abstract fun mainActivity(): MainActivity
}