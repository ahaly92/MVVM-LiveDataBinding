package com.example.livedata

import android.support.v7.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val fragment = LoginFragment()
        supportFragmentManager.beginTransaction().add(R.id.container, fragment).commit()
    }
}
