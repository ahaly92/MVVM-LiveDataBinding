package com.example.livedata

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.databinding.Bindable

class LoginViewModel : ViewModel() {
    @Bindable
    val email = MutableLiveData<String>()

    @Bindable
    val password = MutableLiveData<String>()

    private val _user = MutableLiveData<User>()
    val user: LiveData<User> = _user

    fun onClick() {
        _user.value = User(email.value.orEmpty(), password.value.orEmpty())
    }
}
