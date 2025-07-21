package com.ugurtansal.tarif_defteri.viewmodel

import androidx.lifecycle.ViewModel
import com.ugurtansal.tarif_defteri.repo.CategoryRepository
import com.ugurtansal.tarif_defteri.repo.FoodRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor( var foodRepository: FoodRepository, var categoryRepository: CategoryRepository) : ViewModel() {
}