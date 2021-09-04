package com.example.githublite


import com.google.common.truth.Truth.assertThat
import org.junit.Test

class checkUserTest {
    @Test
    fun emptyUsername(){
        val result = checkUser.validateUser(
            ""
        )
        assertThat(result).isFalse()
    }
//    @Test
//    fun corectUsername(){
//        val result = checkUser.validateUser(
//            "anuj-kumar-sharma"
//        )
//        assertThat(result).isTrue()
//    }
}