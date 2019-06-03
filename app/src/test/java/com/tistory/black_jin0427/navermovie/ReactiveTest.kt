package com.tistory.black_jin0427.navermovie

import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.functions.BiFunction
import io.reactivex.functions.Function3
import org.junit.Test
import java.util.*
import java.util.concurrent.TimeUnit

class ReactiveTest {

    /**
     * Zip 사용시 규칙
     * 1. Observable 끼리 Single 끼리 사용
     * 2. 두개의 Zip 사용시 BiFunction, 그 이상 Function(3 ~ 9)
     * 3. BiFunction, Function 사용시 타입을 명시 해야 합니다.
     */
    @Test
    fun test_zip() {

        val shapes = arrayOf("BALL", "PENTAGON", "STAR")
        val coloredTriangles = arrayOf("2-T", "6-T", "4-T")

        val test1 = Single.just(true).delay(3000, TimeUnit.MILLISECONDS)
        val test2 = Single.just(shapes).delay(3000, TimeUnit.MILLISECONDS)
        val test3 = Single.just(coloredTriangles).delay(1000, TimeUnit.MILLISECONDS)

        val temp1 = Single
            .zip(
                test1,
                test2,
                test3,
                Function3 { resutl: Boolean, planList: Array<String>, banner:  Array<String> ->
                    if (resutl) {
                        planList
                    } else {
                        planList
                    }
                }
            )

        val temp2 = Single
            .zip(
                test1,
                test2,
                BiFunction<Boolean, Array<String>, Boolean> { t1, t2 ->
                    true
                }
            )

        val temp3 = Observable.zip(
            Observable.fromArray(shapes),
            Observable.fromArray(coloredTriangles),
            BiFunction { t1: Array<String>, t2: Array<String> ->
                t1 + t2
            }
        )
    }

    /**
     * zip에 있는 것 중 딜레이가 가장 깃 것이 끝나면 동작됩니다.
     */
    @Test
    fun test_zip_time_delay() {

        val shapes = arrayOf("BALL", "PENTAGON", "STAR")
        val coloredTriangles = arrayOf("2-T", "6-T", "4-T")

        val test1 = Single.just(true).delay(3000, TimeUnit.MILLISECONDS)
        val test2 = Single.just(shapes).delay(3000, TimeUnit.MILLISECONDS)
        val test3 = Single.just(coloredTriangles).delay(1000, TimeUnit.MILLISECONDS)

        val temp = Single.zip(
            test1,
            test2,
            test3,
            Function3 { t1: Boolean, t2: Array<String>, t3: Array<String> ->
                //순차적이 아닌 모든 Observable 이 동작되면 실
                println("t1 $t1")
                println("t2 ${Arrays.toString(t2)}")
                println("t3 ${Arrays.toString(t3)}")
            }
        )

        temp.subscribe({
            println(it)
        },{
            println(it.message)
        })

        Thread.sleep(10000)
    }


    /**
     *  test_CompositeDisposable에는 옵저버블이 들어간 순서가 아닌 지연되는 순서로 실행됩니다.
     */
    @Test
    fun test_CompositeDisposable() {

        val shapes = arrayOf("BALL", "PENTAGON", "STAR")
        val coloredTriangles = arrayOf("2-T", "6-T", "4-T")

        val test1 = Single.just(true).delay(3000, TimeUnit.MILLISECONDS)
        val test2 = Single.just(shapes).delay(4000, TimeUnit.MILLISECONDS)
        val test3 = Single.just(coloredTriangles).delay(1000, TimeUnit.MILLISECONDS)

        CompositeDisposable().addAll(
            test1.subscribe({ println("1 $it")},{}),
            test2.subscribe({ println("2 ${Arrays.toString(it)}")},{}),
            test3.subscribe({ println("3 ${Arrays.toString(it)}")},{})
        )

        //Thread.sleep(5000)

    }
}