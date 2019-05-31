package com.tistory.black_jin0427.navermovie

import org.junit.Test

class GenericKotlinTest {

    interface Output<T> {
        fun isArgument(argument: T): Boolean
    }

    val items = ArrayList<Output<String>>()

    @Test
    fun sample() {

        items.add(object : Output<String> {
            override fun isArgument(argument: String) = false
        })
        items.add(object : Output<String> {
            override fun isArgument(argument: String) = true
        })
    }

    /**
     *  out은 읽기 전용
     */
    private fun printAll(items: ArrayList<out Output<String>>) {

         /*items.add(object : Output<String> {
            override fun isArgument(argument: String) = false
         })*/

        items.indices
            .filter { items[it].isArgument("") }
            .forEach { println("item : " + items[it]) }

        //items.add(null) - Error
    }

    /**
     *  in은 쓰기 적용
     */
    private fun getItem(items: ArrayList<in Output<String>>) {

        items.add(object : Output<String> {
           override fun isArgument(argument: String) = false
        })

        /*items.indices
            .filter { items[it].isArgument("") }
            .forEach { println("item : " + items[it]) }*/

        //items.add(null) - Error
    }

    @Test
    fun sample2() {

        val temp1 = createArrayList<String>()
        val temp2 = createNumberArrayList<Int>()

        printArrayList(mutableListOf("AA", "BB", "CC"))
        printArrayList(mutableListOf(1, 2, 3))

        val ints: Array<Int> = arrayOf(1, 2, 3)
        val any = Array<Any>(3) { 0 }

        copy(ints, any) // Error: expects (Array<Any>, Array<Any>)

        copy2(ints, any)
    }

    private fun <T> createArrayList(): List<T> {
        return ArrayList()
    }

    private fun <T: Number> createNumberArrayList(): List<T> {
        return ArrayList()
    }

    private fun printArrayList(list: List<*>) {
        list.forEach {
            println("it $it")
        }
    }

    fun copy(from: Array<out Any>, to: Array<in Any>) {
        assert(from.size == to.size)
        for (i in from.indices)
            to[i] = from[i]
    }

    fun <TYPE> copy2(from: Array<out TYPE>, to: Array<in TYPE>) {
        assert(from.size == to.size)
        for (i in from.indices)
            to[i] = from[i]
    }
}