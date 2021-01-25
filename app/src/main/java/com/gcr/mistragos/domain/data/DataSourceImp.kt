package com.gcr.mistragos.domain.data

import com.gcr.mistragos.AppDataBase
import com.gcr.mistragos.domain.DataSource
import com.gcr.mistragos.domain.data.model.Drink
import com.gcr.mistragos.domain.data.model.DrinkEntity
import com.gcr.mistragos.vo.Resourse
import com.gcr.mistragos.vo.RetrofitClient

class DataSourceImp(private val appDataBase: AppDataBase):DataSource {

    override suspend fun getDrinkByName(drinkName:String):Resourse<List<Drink>>{
       return Resourse.Success(RetrofitClient.webService.getDrinkbyName(drinkName).drinkList)
   }

    override suspend fun insertDrinkIntoRoom(drinkEntity: DrinkEntity){
       return appDataBase.drinkDao().insertDrink(drinkEntity)
   }

    override suspend fun getFavoritesDrinks(): Resourse<List<DrinkEntity>>{
       return Resourse.Success(appDataBase.drinkDao().getAllFavoriteDrink())
   }

    override suspend fun deleteDrink(drink: DrinkEntity){
       appDataBase.drinkDao().deleteDrink(drink)
   }

    /*suspend fun getDrinkByName(drinkName:String):Resourse<List<Drink>>{
        return Resourse.Success(RetrofitClient.webService.getDrinkbyName(drinkName).drinkList)
    }

    suspend fun insertDrinkIntoRoom(drinkEntity: DrinkEntity){
        return appDataBase.drinkDao().insertDrink(drinkEntity)
    }

    suspend fun getFavoritesDrinks(): Resourse<List<DrinkEntity>>{
        return Resourse.Success(appDataBase.drinkDao().getAllFavoriteDrink())
    }

    suspend fun deleteDrink(drink: DrinkEntity){
        appDataBase.drinkDao().deleteDrink(drink)
    }*/


    // Datos locales
     /*val generateDrink = Resourse.Success (listOf(
        Drink("https://cdn5.recetasdeescandalo.com/wp-content/uploads/2018/09/Coctel-margarita-como-prepararlo.-Receta-e-ingredientes.jpg","Margarita","Azucar , vodka y nuez"),
        Drink("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRi3aUxzTIx0fmnhHjQQGBr1jJ6Yy-vS57nbQ&usqp=CAU","Coronita","Cerveza clara gpo. modelo"),
        Drink("https://pbs.twimg.com/media/CLMNUpSXAAQlkEp.jpg","Chabela","Pulque blanco con tutifruti"),
        Drink("https://cdn.kiwilimon.com/recetaimagen/36264/th5-640x426-44759.jpg","Michelada","Cerveza con chile")
    ))*/

}