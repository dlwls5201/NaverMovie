package com.tistory.black_jin0427.navermovie

import org.junit.Test

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {

    private fun showResult(body: (Int, Int) -> Int) {
        //println(body(1, 2))
    }

    @Test
    fun test() {

        //메소드 참조로 부르려는 함수의 파라미터가 서로 같아야 합니다.
        //showResult { a,b -> sum(a,b)}
        showResult(::sum)
        showResult(::minus)
        showResult(::multiply)
        showResult(::division)

        //showResult { a, b -> a + b }
        showResult(sum)
        showResult(minus)
        showResult(multiply)
        showResult(division)

    }

    //고차 함수 : 람다를 인자로 받거나 반환하는 함수
    //인자로 받은 함수
    fun sum(a: Int, b: Int) = a + b

    fun minus(a: Int, b: Int) = a - b
    fun multiply(a: Int, b: Int) = a * b
    fun division(a: Int, b: Int) = a / b

    //함수 : 람다는 { 함수 파라미터 타입 -> 함수의 반환 타임 } 구조로 되어 있다.
    //함수 타입
    val sum = { a: Int, b: Int -> a + b }
    val minus = { a: Int, b: Int -> a - b }
    val multiply = { a: Int, b: Int -> a * b }
    val division = { a: Int, b: Int -> a / b }

}




