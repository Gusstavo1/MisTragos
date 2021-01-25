package com.gcr.mistragos.domain

import com.gcr.mistragos.domain.data.DataSourceImp
import com.gcr.mistragos.domain.data.model.Drink
import com.gcr.mistragos.domain.data.model.DrinkEntity
import com.gcr.mistragos.vo.Resourse

class RepoImpl(private val dataSourceImp: DataSourceImp):Repository {

    override suspend fun getDrinkList(drinkName: String): Resourse<List<Drink>> {
        return dataSourceImp.getDrinkByName(drinkName)
    }

    override suspend fun getFavoritesDrink(): Resourse<List<DrinkEntity>> {
       return dataSourceImp.getFavoritesDrinks()
    }

    override suspend fun insertDrink(drinkEntity: DrinkEntity) {
        dataSourceImp.insertDrinkIntoRoom(drinkEntity)
    }

    override suspend fun deleteDrink(drink: DrinkEntity) {
        dataSourceImp.deleteDrink(drink)
    }
}