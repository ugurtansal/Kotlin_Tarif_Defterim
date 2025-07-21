package com.ugurtansal.tarif_defteri.room;

import androidx.room.Dao;
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query;
import androidx.room.Update

import com.ugurtansal.tarif_defteri.data.Category;



@Dao
 interface CategoryDao {

    @Query("SELECT * FROM Category")
    suspend fun getAllCategory(): List<Category>

    @Query("SELECT * FROM Category WHERE id = :id")
    suspend fun getCategoryById(id: Int): Category

    @Query("SELECT * FROM Category WHERE name = :name")
    suspend fun getCategoryByName(name: String): Category

    @Update
    suspend fun updateCategory(category: Category)

    @Insert
    suspend fun insertCategory(category: Category)

    @Delete
    suspend fun deleteCategory(category: Category)


}
