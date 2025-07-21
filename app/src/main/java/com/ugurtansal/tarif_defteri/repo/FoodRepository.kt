package com.ugurtansal.tarif_defteri.repo

import com.ugurtansal.tarif_defteri.data.Food
import com.ugurtansal.tarif_defteri.datasource.FoodDataSource

class FoodRepository (var foodDataSource: FoodDataSource) {

    suspend fun getAllFoods(): List<Food> = foodDataSource.getAllFoods();

    suspend fun getFoodById(foodId: Int): Food? = foodDataSource.getFoodById(foodId)

    suspend fun addFood(food: Food) = foodDataSource.addFood(food)

    suspend fun getFoodsByCategoryId(categoryId: Int): List<Food> = foodDataSource.getFoodsByCategoryId(categoryId)

   suspend fun getFoodsByName(foodName: String): Food = foodDataSource.getFoodsByName(foodName)

    suspend fun updateFood(food: Food) = foodDataSource.updateFood(food)

    suspend fun deleteFood(food: Food) = foodDataSource.deleteFood(food)

    suspend fun countFoodsWithName(foodName: String,id: Int): Int {
        return foodDataSource.countFoodsWithName(foodName,id)
    }



}