package com.ugurtansal.tarif_defteri.datasource

import com.ugurtansal.tarif_defteri.data.Category
import com.ugurtansal.tarif_defteri.room.CategoryDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class CategoryDataSource(var categoryDao: CategoryDao)  {

    suspend fun getAllCategory(): List<Category> = withContext(Dispatchers.IO) {
        return@withContext categoryDao.getAllCategory();

    }


   suspend fun deleteCategory(id: Int) = withContext(Dispatchers.IO) {
       val deletedCategory = categoryDao.getCategoryById(id)
         if (deletedCategory != null) {
              categoryDao.deleteCategory(deletedCategory)
         }
   }


    suspend fun updateCategory(category: Category,name: String) = withContext(Dispatchers.IO) {

        val updatedCategory = categoryDao.getCategoryById(category.id)
        if (updatedCategory != null) {
            updatedCategory.name = name
            categoryDao.updateCategory(updatedCategory)
        }
    }

    suspend fun insertCategory(name: String) = withContext(Dispatchers.IO) {
        val newCategory = Category(0, name)
        categoryDao.insertCategory(newCategory)
    }

}