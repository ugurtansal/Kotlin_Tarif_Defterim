package com.ugurtansal.tarif_defteri.repo

import android.R
import com.ugurtansal.tarif_defteri.data.Category
import com.ugurtansal.tarif_defteri.datasource.CategoryDataSource

class CategoryRepository (var categoryDataSource: CategoryDataSource)  {

    suspend fun getAllCategory() = categoryDataSource.getAllCategory()
    suspend fun deleteCategory(id: Int)= categoryDataSource.deleteCategory(id)
    suspend fun updateCategory(category: Category,name: String)= categoryDataSource.updateCategory(category,name)
    suspend fun insertCategory(name: String)= categoryDataSource.insertCategory(name)
}