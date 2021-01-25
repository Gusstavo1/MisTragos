package com.gcr.mistragos.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import com.bumptech.glide.Glide
import com.gcr.mistragos.AppDataBase
import com.gcr.mistragos.R
import com.gcr.mistragos.domain.RepoImpl
import com.gcr.mistragos.domain.data.DataSourceImp
import com.gcr.mistragos.domain.data.model.Drink
import com.gcr.mistragos.domain.data.model.DrinkEntity
import com.gcr.mistragos.ui.viewmodel.MainViewModel
import com.gcr.mistragos.ui.viewmodel.VmFactoty
import kotlinx.android.synthetic.main.fragment_drink_details.*

class DrinkDetailsFragment : Fragment() {

    private val viewModel by activityViewModels<MainViewModel>{VmFactoty(
        RepoImpl(DataSourceImp(AppDataBase.getDataBase(requireActivity().applicationContext)))
    )}
    private lateinit var drink: Drink

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        requireArguments().let {
            drink = it.getParcelable("drink")!!
            Log.d("Drink", "objeto recibido: ${drink.description}")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_drink_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Glide.with(requireContext()).load(drink.image).into(imgDrinkDetail)
        tvDrinkTitle.text = drink.name
        tvDescription.text = drink.description

        btnSaveDrink.setOnClickListener {
            viewModel.saveDrink(DrinkEntity(
                drink.idDrink,
                drink.image,
                drink.name,
                drink.description,
                drink.hasAlcohol
            ))
            Toast.makeText(requireContext(),"Se guardo en favoritos",Toast.LENGTH_SHORT).show()
        }
    }
}