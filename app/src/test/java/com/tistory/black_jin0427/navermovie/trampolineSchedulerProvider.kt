package com.tistory.black_jin0427.navermovie

import com.tistory.black_jin0427.navermovie.constant.SchedulersProvider
import io.reactivex.Scheduler
import io.reactivex.schedulers.Schedulers

/**
 * https://medium.com/@jungil.han/junit-rxjava-%EA%B7%B8%EB%A6%AC%EA%B3%A0-%EC%BB%B4%ED%8C%A8%EB%8B%88%EC%96%B8-%EC%98%A4%EB%B8%8C%EC%A0%9D%ED%8A%B8-e8d17b674bdd
 */
class trampolineSchedulerProvider : SchedulersProvider {
    override fun io(): Scheduler {
        return Schedulers.trampoline()
    }

    override fun ui(): Scheduler {
        return Schedulers.trampoline()
    }
}