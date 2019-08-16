package com.tistory.black_jin0427.navermovie.di

import com.tistory.black_jin0427.navermovie.constant.AppSchedulerProvider
import com.tistory.black_jin0427.navermovie.constant.SchedulersProvider
import com.tistory.black_jin0427.navermovie.di.scope.ActivityScope
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
abstract class RxModule {

    /*@Provides
    @Singleton
    fun provideRxSchedulers(): SchedulersProvider {
        return AppSchedulerProvider
    }*/

    /**
     * 사용법
     *
     * @Binds의 경우 하나의 파라메터만 받는 추상 메소드를 정의해야하고 리턴값은 반드시 그 하나의 파라메터의 상위 인터페이스가 되어야 합니다.
     * 그리고 구현부에서는 생성자에 @Inject 어노테이션을 추가해 줍니다.
     *
     * 장점
     *
     * @Binds를 사용하는 경우 조금더 효율적인 코드가 generate 됩니다.
     * 또한 개발자가 직접 객체를 생성할 필요가 없어서 코드량이 줄어들게 됩니다.
     *
     * 설명 링크 : https://dagger.dev/faq#why-is-binds-different-from-provides
     */
    @Binds
    @Singleton
    abstract fun bindRxSchedulers(appSchedulerProvider: AppSchedulerProvider): SchedulersProvider
}