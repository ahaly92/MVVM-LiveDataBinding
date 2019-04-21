package com.example.livedata

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import com.example.livedata.LoginErrorType.EmailEmpty
import com.example.livedata.LoginErrorType.PasswordEmpty
import com.example.livedata.LoginErrorType.PasswordShort

const val PASSWORD_LENGTH = 5

sealed class LoginErrorType {
    object EmailEmpty : LoginErrorType()
    object PasswordShort : LoginErrorType()
    object PasswordEmpty : LoginErrorType()
}

class LoginViewModel : BaseViewModel() {
    val password = MutableLiveData<String>()
    val username = MutableLiveData<String>()
    val emailError: LiveData<LoginErrorType> = MutableLiveData<LoginErrorType>()
    val passwordError: LiveData<LoginErrorType> = MutableLiveData<LoginErrorType>()

    private var user: User? = null

    fun onClick() {
        when {
            username.value.isNullOrEmpty() -> emailError.latestValue = EmailEmpty
            password.value.isNullOrEmpty() -> passwordError.latestValue = PasswordEmpty
            password.value.orEmpty().length < PASSWORD_LENGTH -> passwordError.latestValue = PasswordShort
            else -> user = User(username.value.orEmpty(), password.value.orEmpty())
        }
    }
}
