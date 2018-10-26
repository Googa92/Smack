package com.example.yahor.smack.Tests

import android.support.test.rule.ActivityTestRule
import com.example.yahor.smack.Controller.MainActivity
import com.example.yahor.smack.Screens.MainScreen
import com.example.yahor.smack.Screens.UserNavigationScreen
import org.junit.Rule
import org.junit.Test

class LoginTests:BaseTest(){

    @Rule
    @JvmField
    var mActivityTestRule = ActivityTestRule(MainActivity::class.java)

    @Test
    fun loginTest(){

        val mainScreen = MainScreen()
        val userNavigationScreen = mainScreen.clickOnNavigationDrawer()
        val loginScreen = userNavigationScreen.clickOnLoginLogoutBtn()
        loginScreen.enterAndLogin(validEmail,validPassword)
            .userInfoIsDisplayed
    }

    @Test
    fun logoutTest(){
        val login = LoginTests()
        login.loginTest()
        val userNavigationScreen = UserNavigationScreen()
        userNavigationScreen.clickOnLoginLogoutBtn()
        pressBack()
        userNavigationScreen.userIsLogout
    }
}