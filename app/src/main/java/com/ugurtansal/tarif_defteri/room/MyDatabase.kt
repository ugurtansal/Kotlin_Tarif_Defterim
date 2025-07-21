package com.ugurtansal.tarif_defteri.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.ugurtansal.tarif_defteri.data.Category
import com.ugurtansal.tarif_defteri.data.Food

@Database(entities = [Food::class, Category::class], version = 1)
abstract class MyDatabase : RoomDatabase() {

    abstract fun getFoodDao(): FoodDao

    abstract fun getCategoryDao(): CategoryDao

}