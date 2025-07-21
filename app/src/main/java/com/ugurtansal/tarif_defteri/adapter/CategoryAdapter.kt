package com.ugurtansal.tarif_defteri.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.snackbar.Snackbar
import com.ugurtansal.tarif_defteri.data.Category
import com.ugurtansal.tarif_defteri.R
import com.ugurtansal.tarif_defteri.databinding.CardDesignBinding
import com.ugurtansal.tarif_defteri.fragments.MainPageDirections
import com.ugurtansal.tarif_defteri.viewmodel.MainPageViewModel
import dagger.hilt.android.qualifiers.ActivityContext

class CategoryAdapter (var mContext: Context, var categoryList: List<Category>,val viewModel: MainPageViewModel) : RecyclerView.Adapter<CategoryAdapter.CategoryHolder>() {

    inner class CategoryHolder(var design: CardDesignBinding) : RecyclerView.ViewHolder(design.root) {
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CategoryHolder {
        var binding= CardDesignBinding.inflate(LayoutInflater.from(mContext), parent, false)

        return CategoryHolder(binding)
    }

    override fun onBindViewHolder(
        holder: CategoryHolder,
        position: Int
    ) {

        val category = categoryList[position]
        val t = holder.design
        t.categoryNameTv.setText(category.name)
        t.cardView.setOnClickListener {
            val pass= MainPageDirections.mainToFoodsScreen(category=category)
            Navigation.findNavController(it).navigate(pass)
        }

        t.constEditIcon.setOnClickListener {
            t.categoryNameTv.isEnabled = true;
            t.categoryNameTv.requestFocus()
            t.constDeleteIcon.visibility= View.INVISIBLE;
            t.constEditIcon.setImageResource(R.drawable.check_icon)

            t.constEditIcon.setOnClickListener {
                val newName = t.categoryNameTv.text.toString()
                if (newName.isNotEmpty() && viewModel.categoryList.value?.any { it.name == newName } != true) {
                   viewModel.updateCategoryName(category, newName);
                    t.categoryNameTv.isEnabled = false;
                    t.constDeleteIcon.visibility= View.VISIBLE;
                    t.constEditIcon.setImageResource(R.drawable.edit_icon)
                    // Here you would typically update the category in your database
                }
                else{
                    MaterialAlertDialogBuilder(mContext).setTitle("Hata")
                        .setMessage("Boş veya aynı isimde bir kategori eklenemez").setPositiveButton("Tamam") { dialog, which ->
                            dialog.dismiss()
                        }.show()
                }
            }
        }

        t.constDeleteIcon.setOnClickListener {

            Snackbar.make(it,"${category.name} silinsin?", Snackbar.LENGTH_LONG)
                .setAction("Evet") {

                    viewModel.deleteCategory(category);
                    // Snackbar.make(it,"${person.kisi_ad} silindi", Snackbar.LENGTH_SHORT).show()
                }.show()

        }
    }

    override fun getItemCount(): Int {
        return categoryList.size;
    }


}