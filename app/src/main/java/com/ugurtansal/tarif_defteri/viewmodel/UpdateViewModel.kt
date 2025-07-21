package com.ugurtansal.tarif_defteri.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.ugurtansal.tarif_defteri.data.Food
import com.ugurtansal.tarif_defteri.repo.CategoryRepository
import com.ugurtansal.tarif_defteri.repo.FoodRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UpdateViewModel  @Inject constructor(private val foodRepository: FoodRepository, private var categoryRepository: CategoryRepository) : ViewModel() {

    fun updateFood(food: Food) {
        CoroutineScope(Dispatchers.Main).launch {
                foodRepository.updateFood(food)

        }
    }

    fun addFood(food: Food) {
        CoroutineScope(Dispatchers.Main).launch {
            foodRepository.addFood(food)
        }
    }

    fun isDuplicateName(name: String, id: Int): LiveData<Boolean> = liveData {
        val count = foodRepository.countFoodsWithName(name, id)
        emit(count > 0)
    }

}