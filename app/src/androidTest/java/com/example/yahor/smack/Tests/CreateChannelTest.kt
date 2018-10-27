package com.example.yahor.smack.Tests

import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.matcher.ViewMatchers.withId
import android.support.test.espresso.matcher.ViewMatchers.withText
import android.support.test.rule.ActivityTestRule
import com.example.yahor.smack.Controller.MainActivity
import com.example.yahor.smack.R
import com.example.yahor.smack.Screens.UserNavigationScreen
import org.hamcrest.Matchers.allOf
import org.junit.Rule
import org.junit.Test

class CreateChannelTest:BaseTest() {

    private val testChannelName = "TestCh${random}"
    private val testChannelDescription = "Test"

    @Rule
    @JvmField
    var mActivityTestRule = ActivityTestRule(MainActivity::class.java)

    @Test
    fun createNewChannelTest(){

        val login = LoginTests()
        login.loginTest()
        val userNavigationScreen = UserNavigationScreen()
        val addChannelAlert = userNavigationScreen.clickOnAddChannelBtn()
        addChannelAlert.addNewChannel(testChannelName,testChannelDescription)
        onView(
            allOf(
                withId(R.id.channel_list),
                withText(testChannelName)
            )
        )
    }
}