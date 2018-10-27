package com.example.yahor.smack.Screens

import android.support.test.espresso.ViewInteraction

abstract class BaseScreen {
    abstract val uniqueView: ViewInteraction
}
enum class PasswordType{
    Valid,
    Invalid
}