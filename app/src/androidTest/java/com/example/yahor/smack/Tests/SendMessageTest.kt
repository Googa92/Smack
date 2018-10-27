package com.example.yahor.smack.Tests

import android.support.test.rule.ActivityTestRule
import com.example.yahor.smack.Controller.MainActivity
import com.example.yahor.smack.Screens.MainScreen
import org.junit.Rule
import org.junit.Test

class SendMessageTest:BaseTest(){

    @Rule
    @JvmField
    var mActivityTestRule = ActivityTestRule(MainActivity::class.java)

    @Test
    fun sendMessageTest(){

        val login = LoginTests()
        login.loginTest()
        pressBack()
        val mainScreen = MainScreen()
        mainScreen.enterMessage()
        mainScreen.messageIsSent
    }
}