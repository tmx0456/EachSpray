package com.eachspray.tan.tanbase.utils

import java.util.*

/**
 * @author Tan
 * @date 2018/10/15 16:01
 */
 
open class RandUtils{

    open  fun randInt(min:Int, max:Int) : Int{
        val rand = Random()

        val randNum = rand.nextInt((max - min) +1) +min

        return randNum
    }
}