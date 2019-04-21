package com.example.livedata

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import com.example.livedata.LoginErrorType.EmailEmpty
import com.example.livedata.LoginErrorType.PasswordEmpty
import com.example.livedata.LoginErrorType.PasswordShort
import com.example.livedata.databinding.FragmentLoginBinding
import kotlinx.android.synthetic.main.fragment_login.password
import kotlinx.android.synthetic.main.fragment_login.username

class LoginFragment : Fragment() {
    private lateinit var viewModel: LoginViewModel
    private lateinit var binding: FragmentLoginBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(LoginViewModel::class.java)
        binding = FragmentLoginBinding.inflate(inflater)
        return binding.apply {
            lifecycleOwner = viewLifecycleOwner
            loginViewModel = viewModel

        }.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bindViewModel()
    }

    private fun bindViewModel() {
        viewModel.emailError.observe(this, Observer {
            it?.let { loginErrorType -> username.setErrorOnField(loginErrorType) }
        })

        viewModel.passwordError.observe(this, Observer {
            it?.let { loginErrorType -> password.setErrorOnField(loginErrorType) }
        })
    }

    private fun EditText.setErrorOnField(errorType: LoginErrorType) {
        error =
            when (errorType) {
                is EmailEmpty -> getString(R.string.empty_email_error)
                is PasswordEmpty -> getString(R.string.empty_password_error)
                is PasswordShort -> getString(R.string.password_too_short_error)
            }
        requestFocus()
    }
}
