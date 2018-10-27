package com.example.yahor.smack.Tests

import android.support.test.rule.ActivityTestRule
import com.example.yahor.smack.Controller.MainActivity
import com.example.yahor.smack.Screens.MainScreen
import com.example.yahor.smack.Screens.PasswordType
import com.example.yahor.smack.Screens.UserNavigationScreen
import org.junit.Rule
import org.junit.Test
import java.util.Random

class CreateUserTest:BaseTest() {

    private val newEmail = "test${random}@test.com"
    private val newUserName = "test${random}"

    @Rule
    @JvmField
    var mActivityTestRule = ActivityTestRule(MainActivity::class.java)

    @Test
    fun createUserTest(){

        val mainScreen = MainScreen()
        val userNavigationScreen = mainScreen.clickOnNavigationDrawer()
        val loginScreen = userNavigationScreen.clickOnLoginLogoutBtn()
        val createUserScreen = loginScreen.clickOnSignUpHereBtn()
        createUserScreen.generateUserAvatar()
        createUserScreen.enterNewUser(newUserName,newEmail,validPassword,PasswordType.Valid)
        as UserNavigationScreen
        userNavigationScreen.userInfoIsDisplayed
    }
}