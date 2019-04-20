package com.example.livedata

import android.util.Patterns

const val PASSWORD_LENGTH = 5

data class User(val emailAddress: String, val password: String) {
    val isEmailValid: Boolean
        get() = Patterns.EMAIL_ADDRESS.matcher(emailAddress).matches()

    val isPasswordLengthGreaterThan5: Boolean
        get() = password.length > PASSWORD_LENGTH
}
