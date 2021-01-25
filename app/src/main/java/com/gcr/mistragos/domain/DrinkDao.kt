package com.gcr.mistragos.domain

import androidx.room.*
import com.gcr.mistragos.domain.data.model.Drink
import com.gcr.mistragos.domain.data.model.DrinkEntity

@Dao
interface DrinkDao {

    @Query("SELECT * FROM drink_table")
    suspend fun getAllFavoriteDrink():List<DrinkEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDrink(drinkEntity: DrinkEntity)

    @Delete
    suspend fun deleteDrink(drink:DrinkEntity)
}