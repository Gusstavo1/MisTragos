package com.gcr.mistragos

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.gcr.mistragos.domain.DrinkDao
import com.gcr.mistragos.domain.data.model.DrinkEntity

@Database(entities = [DrinkEntity::class],version = 1)
abstract class AppDataBase:RoomDatabase() {

    abstract fun drinkDao(): DrinkDao

    companion object{

        private var INTANCE: AppDataBase? = null
        fun getDataBase(context: Context):AppDataBase{
            // si instancia es nula
            INTANCE = INTANCE?: Room.databaseBuilder(
                context.applicationContext,
                AppDataBase::class.java,
                "drink_table").build()
            return INTANCE!!
        }

        fun detroyIntance(){
            INTANCE = null
        }
    }
}