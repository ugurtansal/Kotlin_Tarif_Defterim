package com.ugurtansal.tarif_defteri.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ugurtansal.tarif_defteri.data.Category
import com.ugurtansal.tarif_defteri.repo.CategoryRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainPageViewModel @Inject constructor(var categoryRepository: CategoryRepository) : ViewModel() {

    val categoryList= MutableLiveData<List<Category>>()

    init {
        loadCategories()
    }

    fun loadCategories() {
        CoroutineScope(Dispatchers.Main).launch {
            categoryList.value = categoryRepository.getAllCategory()
        }

    }

    fun deleteCategory(category: Category) {

        CoroutineScope(Dispatchers.Main).launch {
            categoryRepository.deleteCategory(category.id)
            loadCategories()
        }

    }

    fun updateCategoryName(category: Category, newName: String) {
        CoroutineScope(Dispatchers.Main).launch {
            categoryRepository.updateCategory(category,newName)
            loadCategories()
        }

    }

    fun addCategory(categoryName: String) {
        CoroutineScope(Dispatchers.Main).launch {
            if (categoryName.isNotEmpty()) {
                categoryRepository.insertCategory(categoryName)
                loadCategories()
            }
        }

    }
}