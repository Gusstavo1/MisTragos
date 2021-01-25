package com.gcr.mistragos.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.gcr.mistragos.AppDataBase
import com.gcr.mistragos.R
import com.gcr.mistragos.domain.RepoImpl
import com.gcr.mistragos.domain.data.DataSourceImp
import com.gcr.mistragos.domain.data.model.Drink
import com.gcr.mistragos.ui.viewmodel.MainViewModel
import com.gcr.mistragos.ui.viewmodel.VmFactoty
import com.gcr.mistragos.vo.Resourse
import kotlinx.android.synthetic.main.fragment_favorites_drink.*


class FavoritesDrinkFragment : Fragment(),RvAdapter.drinkOnclickListener {

    private val viewModel by activityViewModels<MainViewModel>{
        VmFactoty(
        RepoImpl(DataSourceImp(AppDataBase.getDataBase(requireActivity().applicationContext)))
    ) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_favorites_drink, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpRecyclerView()

        viewModel.getFavoritesDrinks().observe(viewLifecycleOwner, Observer {favorites->
            when(favorites){
                is Resourse.Loading->{

                }
                is Resourse.Success->{
                    Log.d("Fav", "Lista recibida:${favorites.data} ")
                   val lista = favorites.data.map {
                       Drink(it.idDrink,it.image,it.name,it.description,it.hasAlcohol) }
                    rvFavoritesDrinks.adapter = RvAdapter(this,requireContext(),lista)
                }

                is Resourse.Failure->{

                }
            }
        })
    }

    fun setUpRecyclerView() {
        rvFavoritesDrinks.layoutManager = LinearLayoutManager(requireContext())
        rvFavoritesDrinks.addItemDecoration(
            DividerItemDecoration(
                requireContext(),
                DividerItemDecoration.VERTICAL
            )
        )
    }

    override fun clickListener(drink: Drink,position:Int) {
        viewModel.getFavoritesDrinks().observe(viewLifecycleOwner, Observer {

        })
        Toast.makeText(requireContext(),"Borrando bebida",Toast.LENGTH_SHORT).show()
        //viewModel.deleteFavotiteDrink(drink)
        //rvFavoritesDrinks.adapter?.notifyItemRemoved(position)
        //rvFavoritesDrinks.adapter?.notifyItemRangeRemoved(position,rvFavoritesDrinks.adapter?.itemCount!!)
    }
}