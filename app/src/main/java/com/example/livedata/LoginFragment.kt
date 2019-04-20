package com.example.livedata

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import com.example.livedata.databinding.FragmentLoginBinding

class LoginFragment : Fragment() {
    private lateinit var loginViewModel: LoginViewModel
    private lateinit var binding: FragmentLoginBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreate(savedInstanceState)
        loginViewModel = ViewModelProviders.of(this).get(LoginViewModel::class.java)
        binding = FragmentLoginBinding.inflate(inflater)
        binding.let {
            it.lifecycleOwner = viewLifecycleOwner
            it.loginViewModel = loginViewModel
            return it.root
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bindViewModel()
    }

    private fun bindViewModel() {
        loginViewModel.user.observe(this, Observer { user ->
            binding.run {
                user?.let {
                    when {
                        it.emailAddress.isEmpty() -> setErrorOnField(emailAddress, R.string.empty_email_error)
                        !it.isEmailValid -> setErrorOnField(emailAddress, R.string.invalid_email_error)
                        it.password.isEmpty() -> setErrorOnField(password, R.string.empty_password_error)
                        !it.isPasswordLengthGreaterThan5 -> setErrorOnField(password, R.string.password_too_short_error)
                        else -> {
                            resultEmailAddress.text = it.emailAddress
                            resultPassword.text = it.password
                        }
                    }
                }
            }
        })
    }

    private fun setErrorOnField(editText: EditText, errorResource: Int) {
        editText.error = getString(errorResource)
        editText.requestFocus()
    }
}
