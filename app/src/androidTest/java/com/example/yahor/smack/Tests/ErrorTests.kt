package com.example.yahor.smack.Tests

import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.matcher.ViewMatchers.isDisplayed
import android.support.test.espresso.matcher.ViewMatchers.withContentDescription
import android.support.test.rule.ActivityTestRule
import com.example.yahor.smack.Controller.MainActivity
import com.example.yahor.smack.Screens.CreateUserScreen
import com.example.yahor.smack.Screens.LoginScreen
import com.example.yahor.smack.Screens.MainScreen
import com.example.yahor.smack.Screens.PasswordType
import org.hamcrest.Matchers.allOf
import org.junit.Rule
import org.junit.Test

class ErrorTests: BaseTest() {

    private val emptyEmail = ""
    private val emptyPassword = ""
    private val invalidPassword = randomString

    @Rule
    @JvmField
    var mActivityTestRule = ActivityTestRule(MainActivity::class.java)

    @Test
    fun invalidEmailTest() {

        val mainScreen = MainScreen()
        val userNavigationScreen = mainScreen.clickOnNavigationDrawer()
        val loginScreen = userNavigationScreen.clickOnLoginLogoutBtn()
        loginScreen.enterAndLogin(validEmail, invalidPassword, PasswordType.Invalid)
            as LoginScreen
        onView(
            allOf(
                withContentDescription("Something went wrong, please try again"),
                isDisplayed()
            )
        )
    }

    @Test
    fun emptyEmailAndPasswordTest() {

        val mainScreen = MainScreen()
        val userNavigationScreen = mainScreen.clickOnNavigationDrawer()
        val loginScreen = userNavigationScreen.clickOnLoginLogoutBtn()
        loginScreen.enterAndLogin(emptyEmail, emptyPassword, PasswordType.Invalid)
            as LoginScreen
        onView(
            allOf(
                withContentDescription("Please fill in both email and password"),
                isDisplayed()
            )
        )
    }

    @Test
    fun createUserWithSameEmailTest(){

        val mainScreen = MainScreen()
        val userNavigationScreen = mainScreen.clickOnNavigationDrawer()
        val loginScreen = userNavigationScreen.clickOnLoginLogoutBtn()
        val createUserScreen = loginScreen.clickOnSignUpHereBtn()
        createUserScreen.generateUserAvatar()
        createUserScreen.enterNewUser(randomString,validEmail,validPassword,PasswordType.Invalid)
            as CreateUserScreen
        onView(
            allOf(
                withContentDescription("Something went wrong, please try again"),
                isDisplayed()
            )
        )
    }

    @Test
    fun createUserWithEmptyPasswordTest(){

        val mainScreen = MainScreen()
        val userNavigationScreen = mainScreen.clickOnNavigationDrawer()
        val loginScreen = userNavigationScreen.clickOnLoginLogoutBtn()
        val createUserScreen = loginScreen.clickOnSignUpHereBtn()
        createUserScreen.generateUserAvatar()
        createUserScreen.enterNewUser(randomString,validEmail,"",PasswordType.Invalid)
            as CreateUserScreen
        onView(
            allOf(
                withContentDescription("Make sure user name, email and password are filled in."),
                isDisplayed()
            )
        )
    }
}