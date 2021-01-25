package com.gcr.mistragos.ui.viewmodel

import androidx.lifecycle.*
import com.gcr.mistragos.domain.Repository
import com.gcr.mistragos.domain.data.model.Drink
import com.gcr.mistragos.domain.data.model.DrinkEntity
import com.gcr.mistragos.vo.Resourse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.lang.Exception

class MainViewModel(private val repo:Repository):ViewModel() {

    private val drinkData = MutableLiveData<String>()

    fun setDrinkName(name:String){
        drinkData.value = name
    }

    init {
        setDrinkName("Margarita")
    }

    val fetchDrinkList = drinkData.distinctUntilChanged().switchMap {drinkName->
        liveData(Dispatchers.IO){
            emit(Resourse.Loading())
            try {
                emit(repo.getDrinkList(drinkName))
            }catch (e:Exception){
                emit(Resourse.Failure(e))
            }
        }
    }

    fun saveDrink(drinkEntity: DrinkEntity){
        viewModelScope.launch {
            repo.insertDrink(drinkEntity)
        }
    }

    fun getFavoritesDrinks() = liveData(Dispatchers.IO) {
        emit(Resourse.Loading())
        try {
            emit(repo.getFavoritesDrink())
        }catch (e:Exception){
            emit(Resourse.Failure(e))
        }
    }

    fun deleteFavotiteDrink(drink: DrinkEntity){
        viewModelScope.launch {
            repo.deleteDrink(drink)
        }
    }


    /*val fetchDrinkList = liveData(Dispatchers.IO){
        emit(Resourse.Loading())
        try {
            emit(repo.getDrinkList("Margarita"))
        }catch (e:Exception){
            emit(Resourse.Failure(e))
        }
    }*/
}