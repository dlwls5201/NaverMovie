package com.tistory.black_jin0427.navermovie

import org.junit.Test

class InlineTest {

    interface Callback {
        fun notEmptyString(param: String)
    }

    fun notEmptyString(param: String?, callback: Callback) {
        if (!param.isNullOrEmpty()) {
            callback.notEmptyString(param)
        }
    }

    fun String?.notNullMessage1(callback: Callback) {
        if(!this.isNullOrEmpty()) {
            callback.notEmptyString(this)
        }
    }

    fun String?.notNullMessage2(callback: String.() -> Unit) {
        if (!this.isNullOrEmpty()) {
            this.callback()
        }
    }

    inline fun String?.notNullMessage3(callback: String.() -> Unit) {
        if (!this.isNullOrEmpty()) {
            this.callback()
        }
    }

    // 사용하는 부분
    @Test
    fun test() {

        val message = "blackJin"

        notEmptyString(message, object : Callback {
            override fun notEmptyString(param: String) {
                println("notEmptyString $param")
            }

        })

        message.notNullMessage1(object : Callback {
            override fun notEmptyString(param: String) {
                println("notNullMessage1 $param")
            }
        })

        message.notNullMessage2 {
            println("notNullMessage2 $this")
        }

        message.notNullMessage3 {
            println("notNullMessage3 $this")
        }
    }
}