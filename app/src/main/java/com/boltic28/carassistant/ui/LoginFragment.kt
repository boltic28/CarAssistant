package com.boltic28.carassistant.ui

import android.os.Bundle
import android.view.View
import androidx.annotation.DrawableRes
import androidx.lifecycle.lifecycleScope
import com.boltic28.carassistant.R
import com.boltic28.carassistant.app.launchWhenStarted
import com.boltic28.carassistant.databinding.FragmentLoginBinding
import com.boltic28.carassistant.domain.sign.SignState
import com.boltic28.carassistant.ui.base.BaseFragment
import kotlinx.coroutines.flow.onEach
import org.koin.androidx.viewmodel.ext.android.viewModel

class LoginFragment : BaseFragment<FragmentLoginBinding, LoginViewModel>(R.layout.fragment_login) {

    override val viewModel: LoginViewModel by viewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setClickListeners()
        observeSignIn()
    }

    private fun setClickListeners() {
        with(binding) {
            val onFocusChangeListener = View.OnFocusChangeListener { _, _ -> showDefaultInputFrame() }
            clickListener = object : LoginClickListener {
                override fun onForgotPasswordClick() {
                    viewModel.showMessage(R.string.not_provided_functionality)
                }

                override fun onSignUpClick() {
                    showDefaultInputFrame()
                    lfButtonsGroup.visibility = View.GONE
                    lfRegistrationGroup.visibility = View.VISIBLE
                }

                override fun onSignInClick() {
                    showProgressBar()
                    tryToSignIn(
                        lfEmailField.text.toString(),
                        lfPasswordField.text.toString()
                    )
                }

                override fun onRegistrationClick() {
                    showProgressBar()
                    tryToSignUp(
                        lfEmailField.text.toString(),
                        lfPasswordField.text.toString(),
                        lfRepeatPasswordField.text.toString()
                    )
                }

                override fun onBackToSignInClick() {
                    showDefaultInputFrame()
                    lfButtonsGroup.visibility = View.VISIBLE
                    lfRegistrationGroup.visibility = View.GONE
                }
            }
            lfEmailField.onFocusChangeListener = onFocusChangeListener
            lfPasswordField.onFocusChangeListener = onFocusChangeListener
            lfRepeatPasswordField.onFocusChangeListener = onFocusChangeListener
        }
    }

    private fun tryToSignIn(email: String, password: String) {
        val isInputDataValid = viewModel.isSignInDataValid(email, password)
        if (isInputDataValid){
            askServerForSignIn(email, password, false)
        }else{
            hideProgressBar()
            showWrongInputFrame()
        }
    }

    private fun tryToSignUp(email: String, password: String, passwordConfirmation: String) {
        val isInputValid = viewModel.isRegistrationDataValid(email, password, passwordConfirmation)
        if (isInputValid) {
            askServerForSignIn(email, password, true)
        } else {
            hideProgressBar()
            showWrongInputFrame()
        }
    }

    private fun changeInputFieldFrame(@DrawableRes res: Int) {
        with(binding) {
            lfEmailField.setBackgroundResource(res)
            lfPasswordField.setBackgroundResource(res)
            lfRepeatPasswordField.setBackgroundResource(res)
        }
    }

    private fun observeSignIn() {
        viewModel.observeSignInState().onEach {
            if (it == SignState.FAILED) {
                showWrongInputFrame()
                viewModel.showMessage(R.string.wrong_data_check_or_register)
            }
            hideProgressBar()
        }.launchWhenStarted(lifecycleScope)

        activateSignInController(
            isSigned = {
                hideProgressBar()
                toMainFragment()
            },
            isNotSigned = { }
        )
    }

    private fun showWrongInputFrame() = changeInputFieldFrame(R.drawable.input_field_bg_wrong)

    private fun showDefaultInputFrame() = changeInputFieldFrame(R.drawable.input_field_bg_default)
}