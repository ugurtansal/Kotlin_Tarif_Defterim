package com.ugurtansal.tarif_defteri.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ugurtansal.tarif_defteri.data.Food
import com.ugurtansal.tarif_defteri.repo.CategoryRepository
import com.ugurtansal.tarif_defteri.repo.FoodRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FoodNamesViewModel @Inject constructor(var foodRepository: FoodRepository, private var categoryRepository: CategoryRepository) : ViewModel() {

    val foodList = MutableStateFlow<List<Food>>(emptyList())
     var categoryId: Int?= null

    fun setCategoryId(id: Int) {
        categoryId = id
        loadFoods(id)
    }

    fun loadFoods(categoryId:Int) {
        CoroutineScope(Dispatchers.Main).launch {
            foodList.value = foodRepository.getFoodsByCategoryId(categoryId)
        }
    }

    fun deleteFood(food: Food) {
        CoroutineScope(Dispatchers.Main).launch {
            foodRepository.deleteFood(food)
            loadFoods(categoryId ?: 0)
        }
    }

    fun updateFood(food: Food, newName: String) {
        CoroutineScope(Dispatchers.Main).launch {
            if (newName.isNotEmpty() && foodList.value?.any { it.name == newName } != true){
                foodRepository.updateFood(food)
                loadFoods(categoryId ?: 0)
            }

        }
    }

    fun addFood(food: Food) {
        CoroutineScope(Dispatchers.Main).launch {
                foodRepository.addFood(food)
                loadFoods(categoryId ?: 0)
        }
    }
}