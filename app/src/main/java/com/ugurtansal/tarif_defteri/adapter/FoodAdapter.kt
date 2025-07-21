package com.ugurtansal.tarif_defteri.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.ugurtansal.tarif_defteri.data.Category
import com.ugurtansal.tarif_defteri.data.Food
import com.ugurtansal.tarif_defteri.databinding.CardDesignBinding
import com.ugurtansal.tarif_defteri.fragments.FoodNamesFragmentDirections
import com.ugurtansal.tarif_defteri.viewmodel.FoodNamesViewModel

class FoodAdapter (var mContext: Context, var foodList: List<Food>, val category: Category, var viewModel: FoodNamesViewModel): RecyclerView.Adapter<FoodAdapter.FoodHolder>() {

    inner class FoodHolder(var design: CardDesignBinding) : RecyclerView.ViewHolder(design.root) {
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): FoodHolder {
        val binding= CardDesignBinding.inflate(
            LayoutInflater.from(mContext), parent, false
        )

        return FoodHolder(binding)
    }

    override fun onBindViewHolder(
        holder: FoodHolder,
        position: Int
    ) {
       val food= foodList[position]
        val t= holder.design
        t.categoryNameTv.setText(food.name)

        t.cardView.setOnClickListener {
            val pass= FoodNamesFragmentDirections.foodNamesToDetail(foodObject=food)
            Navigation.findNavController(it).navigate(pass)
        }

        t.constEditIcon.setOnClickListener{
            val pass= FoodNamesFragmentDirections.foodNamesToUpdate(foodObject=food, categoryId = category.id)
            Navigation.findNavController(it).navigate(pass)
        }

        t.constDeleteIcon.setOnClickListener {
            viewModel.deleteFood(food)
        }



    }

    fun updateList(newList: List<Food>) {
        this.foodList = newList
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return foodList.size;
    }


}