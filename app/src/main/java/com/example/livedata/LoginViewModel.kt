package com.example.livedata

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.view.View

class LoginViewModel : ViewModel() {
    var emailAddress = MutableLiveData<String>()
    var password = MutableLiveData<String>()

    private var userMutableLiveData: MutableLiveData<User> = MutableLiveData()

    val user: MutableLiveData<User>
        get() = userMutableLiveData

    fun onClick(view: View) {
        userMutableLiveData.value = User(emailAddress.value.orEmpty(), password.value.orEmpty())
    }
}
