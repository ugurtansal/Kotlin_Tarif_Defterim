package com.ugurtansal.tarif_defteri.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import org.jetbrains.annotations.NotNull
import java.io.Serializable

@Entity(tableName = "Category")
data class  Category (@PrimaryKey(autoGenerate = true) @NotNull var id: Int,
                     @NotNull var name: String) : Serializable {



}