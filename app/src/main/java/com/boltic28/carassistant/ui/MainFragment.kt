package com.boltic28.carassistant.ui

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.boltic28.carassistant.R
import com.boltic28.carassistant.data.dto.Car
import com.boltic28.carassistant.databinding.FragmentMainBinding
import com.boltic28.carassistant.ui.adapters.car.CarAdapter
import com.boltic28.carassistant.ui.base.BaseFragment
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainFragment :
    BaseFragment<FragmentMainBinding, MainFragmentViewModel>(R.layout.fragment_main) {

    override val viewModel: MainFragmentViewModel by viewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        println("->> mainFragment: userMail = ${viewModel.getCurrentUser().email}")

        activateSignInController(
            isSigned = { },
            isNotSigned = { toLoginFragment() }
        )
        initViews()
        //signOut()
    }


    fun initViews() {
        with(binding.mfRecycler) {
            layoutManager =
                LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            adapter = CarAdapter(getCars())
        }
    }


    fun getCars(): List<Car> =
        listOf(
            Car(1, "AUDI", "A4", "1234 AA-5", "ZZZWAU45671266476", 2015, 123000),
            Car(1, "AUDI", "A6", "1232 AA-5", "ZZZWAU45234126676", 2011, 153000),
            Car(1, "AUDI", "A1", "1634 AA-5", "ZZZWAU45345445676", 2013, 23000),
        )
}