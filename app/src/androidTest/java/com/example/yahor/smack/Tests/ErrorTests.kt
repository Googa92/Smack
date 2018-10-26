package com.example.yahor.smack.Tests

import android.support.test.espresso.Espresso
import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.matcher.ViewMatchers
import android.support.test.espresso.matcher.ViewMatchers.isDisplayed
import android.support.test.espresso.matcher.ViewMatchers.withContentDescription
import android.support.test.rule.ActivityTestRule
import com.example.yahor.smack.Controller.MainActivity
import com.example.yahor.smack.Screens.MainScreen
import org.hamcrest.Matchers
import org.hamcrest.Matchers.allOf
import org.junit.Rule
import org.junit.Test

class ErrorTests: BaseTest() {

    val emptyEmail = ""
    val emptyPassword = ""
    val invalidPassword = randomString

    @Rule
    @JvmField
    var mActivityTestRule = ActivityTestRule(MainActivity::class.java)


    @Test
    fun invalidEmailTest(){

        val mainScreen = MainScreen()
        val userNavigationScreen = mainScreen.clickOnNavigationDrawer()
        val loginScreen = userNavigationScreen.clickOnLoginLogoutBtn()
        loginScreen.enterAndLogin(validEmail, invalidPassword)
        onView(
            allOf(
                withContentDescription("Something went wrong, please try again"),
                isDisplayed()))
    }
}