package com.boltic28.carassistant.ui.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.boltic28.carassistant.R
import com.boltic28.carassistant.app.launchWhenStarted
import com.boltic28.carassistant.ui.MainActivity
import kotlinx.coroutines.flow.onEach

abstract class BaseFragment<B : ViewDataBinding, M : BaseViewModel>(private val layout: Int) :
    Fragment(layout) {

    protected lateinit var binding: B
        private set

    abstract val viewModel: M

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, layout, container, false)
        binding.lifecycleOwner = this
        return binding.root
    }

    //Authentication
    fun askServerForSignIn(email: String, password: String, isNewAccount: Boolean) {
        if (isNewAccount) {
            viewModel.signUp(email, password)
        } else {
            viewModel.signIn(email, password)
        }.addOnCompleteListener(requireActivity() as MainActivity) {
            hideProgressBar()
            viewModel.checkAuthTaskAndSetUser(it, isNewAccount)
        }
    }

    fun activateSignInController(isSigned: () -> Unit, isNotSigned: () -> Unit) {
        viewModel.observeUser().onEach {
            if (it.id.isEmpty()) isNotSigned.invoke() else isSigned.invoke()
        }.launchWhenStarted(lifecycleScope)
    }

    //Navigation
    fun toMainFragment() = findNavController().navigate(R.id.mainFragment)

    fun toLoginFragment() = findNavController().navigate(R.id.loginFragment)

    //Additional
    fun showProgressBar() = (requireActivity() as MainActivity).showProgressBar()

    fun hideProgressBar() = (requireActivity() as MainActivity).hideProgressBar()
}