package com.ugurtansal.tarif_defteri.datasource

import com.ugurtansal.tarif_defteri.data.Food
import com.ugurtansal.tarif_defteri.room.FoodDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class FoodDataSource (var foodDao: FoodDao){

    suspend fun getAllFoods(): List<Food> = withContext(Dispatchers.IO){
        return@withContext foodDao.getAllFoods();
    }

    suspend fun getFoodsByCategoryId(categoryId: Int): List<Food> = withContext(Dispatchers.IO){
        return@withContext foodDao.getFoodsByCategoryId(categoryId);
    }

    suspend fun getFoodsByName(foodName: String): Food = withContext(Dispatchers.IO){
        return@withContext foodDao.getFoodByName(foodName)
    }

    suspend fun getFoodById(id: Int): Food? = withContext(Dispatchers.IO){
        return@withContext foodDao.getFoodById(id);
    }

    suspend fun addFood(food: Food) = foodDao.insertFood(food)

    suspend fun updateFood(food: Food) = foodDao.updateFood(food)

    suspend fun deleteFood(food: Food) = foodDao.deleteFood(food)

    suspend fun countFoodsWithName(foodName: String,id: Int): Int = withContext(Dispatchers.IO) {
        return@withContext foodDao.countFoodsWithName(foodName, id)
    }




}