package com.boltic28.carassistant.ui

import android.os.Bundle
import android.view.View
import com.boltic28.carassistant.BuildConfig
import com.boltic28.carassistant.R
import com.boltic28.carassistant.databinding.FragmentSplashBinding
import com.boltic28.carassistant.ui.base.BaseFragment
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class SplashFragment : BaseFragment<FragmentSplashBinding, SplashViewModel>(R.layout.fragment_splash) {

    override val viewModel: SplashViewModel by viewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //viewModel.signOut()
        binding.sfVersionName.text = resources.getString(R.string.version_name, BuildConfig.VERSION_NAME)


        CoroutineScope(Dispatchers.Main).launch{

            binding.sfLoadingData.text = "loading cars..."
            load()
            binding.sfLoadingData.text = "loading parts..."
            load()
            binding.sfLoadingData.text = "finding users..."
            load()

            delay(400)
            binding.sfLoadingData.text = "finished!"
            delay(200)
            activateSignInController(
                isSigned = { toMainFragment() },
                isNotSigned = { toLoginFragment() }
            )
        }
    }

    private suspend fun load(){
        for (i in 0..5) {
            delay(300)
            print(".")
        }
        println()
    }
}