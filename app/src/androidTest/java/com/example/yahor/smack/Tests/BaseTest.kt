package com.example.yahor.smack.Tests

import android.support.test.espresso.Espresso
import java.util.Random

open class BaseTest {

    val validEmail = "test@test"
    val validPassword = "123456"
    val randomString = "${Random().nextInt(10000-1)}"


    fun pressBack(){
        Espresso.pressBack()
    }

}