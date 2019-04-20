package com.example.livedata

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.view.View

class LoginViewModel : ViewModel() {
    private val mutableEmailAddress = MutableLiveData<String>()
    private val mutablePassword = MutableLiveData<String>()
    val emailAddress: LiveData<String> = mutableEmailAddress
    val password: LiveData<String> = mutablePassword

    private var userMutableLiveData: MutableLiveData<User> = MutableLiveData()

    val user: MutableLiveData<User>
        get() = userMutableLiveData

    fun onClick(view: View) {
        userMutableLiveData.value = User(emailAddress.value.orEmpty(), password.value.orEmpty())
    }
}
