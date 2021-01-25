package com.gcr.mistragos.domain

import com.gcr.mistragos.domain.data.model.Drink
import com.gcr.mistragos.domain.data.model.DrinkEntity
import com.gcr.mistragos.vo.Resourse

interface DataSource {
    suspend fun getDrinkByName(drinkName:String): Resourse<List<Drink>>
    suspend fun insertDrinkIntoRoom(drinkEntity: DrinkEntity)
    suspend fun getFavoritesDrinks(): Resourse<List<DrinkEntity>>
    suspend fun deleteDrink(drink: DrinkEntity)
}