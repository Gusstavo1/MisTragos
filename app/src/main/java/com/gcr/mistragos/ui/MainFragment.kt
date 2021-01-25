package com.gcr.mistragos.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import android.widget.SearchView
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
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
import kotlinx.android.synthetic.main.fragment_main.*

class MainFragment : Fragment() ,RvAdapter.drinkOnclickListener{

    private val viewModel by activityViewModels<MainViewModel>{VmFactoty(
        RepoImpl(DataSourceImp(AppDataBase.getDataBase(requireActivity().applicationContext)))
    )}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpRecyclerView()
        setUpSearchView()
        setUpObservers()

        btnFavorites.setOnClickListener {
            findNavController().navigate(R.id.action_mainFragment_to_favoritesDrinkFragment)
        }
    }

    fun setUpObservers(){
        viewModel.fetchDrinkList.observe(viewLifecycleOwner, Observer {result->
            when(result){
                is Resourse.Loading->{
                    progress.visibility = VISIBLE
                }
                is Resourse.Success->{
                    progress.visibility = GONE
                    rvDrinks.adapter = RvAdapter(this,requireContext(),result.data)
                }
                is Resourse.Failure->{
                    progress.visibility = GONE
                    Toast.makeText(requireContext(),"Ocurrio un pronblema: ${result.exception}",Toast.LENGTH_SHORT).show()
                }
            }
        })
    }

    fun setUpRecyclerView() {
        rvDrinks.layoutManager = LinearLayoutManager(requireContext())
        rvDrinks.addItemDecoration(
            DividerItemDecoration(
                requireContext(),
                DividerItemDecoration.VERTICAL
            )
        )
    }

    fun setUpSearchView(){
        searchDrink.setOnQueryTextListener(object :SearchView.OnQueryTextListener,
            androidx.appcompat.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                viewModel.setDrinkName(query!!)
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
               return false
            }
        })
    }

    override fun clickListener(drink: Drink,position:Int) {
        val bundle = Bundle()
        bundle.putParcelable("drink",drink)
        findNavController().navigate(R.id.action_mainFragment_to_drinkDetailsFragment,bundle)
    }

}