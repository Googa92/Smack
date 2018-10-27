package com.example.yahor.smack.Tests

import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import com.example.yahor.smack.Controller.MainActivity
import com.example.yahor.smack.Screens.MainScreen
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
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