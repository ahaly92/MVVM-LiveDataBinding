package com.example.livedata

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel

open class BaseViewModel : ViewModel() {
    protected var <T : Any> LiveData<T>.latestValue: T?
        get() = this.value
        set(value) {
            (this as MutableLiveData<T>).value = value
        }
}