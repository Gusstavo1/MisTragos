package com.gcr.mistragos.domain

import com.gcr.mistragos.domain.data.model.Drink
import com.gcr.mistragos.domain.data.model.DrinkEntity
import com.gcr.mistragos.vo.Resourse

interface Repository {
    suspend fun getDrinkList(drinkName:String): Resourse<List<Drink>>
    suspend fun getFavoritesDrink():Resourse<List<DrinkEntity>>
    suspend fun insertDrink(drinkEntity: DrinkEntity)
    suspend fun deleteDrink(drink: DrinkEntity)
}