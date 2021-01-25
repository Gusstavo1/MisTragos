package com.gcr.mistragos.domain

import com.gcr.mistragos.domain.data.model.DrinkList
import retrofit2.http.GET
import retrofit2.http.Query

interface WebService {

    @GET("search.php")
    suspend fun getDrinkbyName(
        @Query(value = "s")drinkName:String
    ):DrinkList
}