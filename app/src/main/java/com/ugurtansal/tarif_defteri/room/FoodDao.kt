package com.ugurtansal.tarif_defteri.room;

import androidx.room.Dao;
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.ugurtansal.tarif_defteri.data.Food

@Dao
interface FoodDao {

    @Query("SELECT * FROM Food") // Get all food items
    suspend fun getAllFoods(): List<Food>

    @Query("SELECT * FROM Food WHERE id = :foodId")
    suspend fun getFoodById(foodId: Int): Food?

    @Query("SELECT * FROM Food WHERE categoryId = :categoryId")
    suspend fun getFoodsByCategoryId(categoryId: Int): List<Food>

    @Query("SELECT COUNT(*) FROM Food WHERE LOWER(name) = LOWER(:name) AND id != :id")
    suspend fun countFoodsWithName(name: String, id: Int): Int


    @Insert
    suspend fun insertFood(food: Food)

    @Delete
    suspend fun deleteFood(food: Food)

    @Update
    suspend fun updateFood(food: Food)


    @Query("SELECT * FROM Food WHERE name = :foodname")
    suspend fun getFoodByName(foodname: String): Food

}
