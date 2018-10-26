package com.example.yahor.smack.Tests

import android.support.test.rule.ActivityTestRule
import com.example.yahor.smack.Controller.MainActivity
import com.example.yahor.smack.Screens.MainScreen
import org.junit.Rule
import org.junit.Test

class LoginTests {

    @Rule
    var mActivityTestRule = ActivityTestRule(MainActivity::class.java)

    val validEmail = "test@test"
    val validPassword = "123456"

    @Test

    fun loginTest(){

        val mainScreen = MainScreen()
        val userNavigationScreen = mainScreen.clickOnNavigationDrawer()
        val loginScreen = userNavigationScreen.clickOnLoginLogoutBtn()
        loginScreen.enterAndLogin(validEmail,validPassword)
            .userInfoIsDisplayed
    }



}