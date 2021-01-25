package com.gcr.mistragos.domain.data.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Drink(
    @SerializedName("idDrink")
    val idDrink:String,
    @SerializedName("strDrinkThumb")
    val image: String,
    @SerializedName("strDrink")
    val name: String,
    @SerializedName("strInstructions")
    val description: String,
    @SerializedName("strAlcoholic")
    val hasAlcohol:String
):Parcelable

data class DrinkList(
    @SerializedName("drinks")
    val drinkList:List<Drink>)

@Entity(tableName = "drink_table")
data class DrinkEntity(
    @PrimaryKey
    val idDrink:String,
    @ColumnInfo(name= "img_drink")
    val image: String,
    @ColumnInfo(name= "name_drink")
    val name: String,
    @ColumnInfo(name= "description_drink")
    val description: String,
    @ColumnInfo(name= "hasAlcohol_drink")
    val hasAlcohol:String
)