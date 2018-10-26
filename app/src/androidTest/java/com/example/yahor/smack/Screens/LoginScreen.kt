package com.example.yahor.smack.Screens

import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.ViewInteraction
import android.support.test.espresso.action.ViewActions.click
import android.support.test.espresso.action.ViewActions.closeSoftKeyboard
import android.support.test.espresso.action.ViewActions.typeText
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.matcher.ViewMatchers.isDisplayed
import android.support.test.espresso.matcher.ViewMatchers.withId
import com.example.yahor.smack.R

class LoginScreen: BaseScreen(){

    private val emailField: ViewInteraction
        get() = onView(withId(R.id.loginEmailTxt))

    private val passwordField: ViewInteraction
        get() = onView(withId(R.id.loginPasswordTxt))

    private val loginBtn: ViewInteraction
        get() = onView(withId(R.id.loginLoginBtn))

    private val signUpHereBtn: ViewInteraction
        get() = onView(withId(R.id.loginCreateUserBtn))

    override val uniqueView: ViewInteraction
        get() = loginBtn

    init {
        uniqueView.check(matches(isDisplayed()))
    }

    fun enterAndLogin (email: String, password: String): UserNavigationScreen {
        emailField.perform(typeText(email))
        passwordField.perform(typeText(password), closeSoftKeyboard())
        loginBtn.perform(click())
        return UserNavigationScreen()
    }

    fun tapOnSignUoHereBtn() : CreateUserScreen {
        signUpHereBtn.perform(click())
        return CreateUserScreen()
    }
}