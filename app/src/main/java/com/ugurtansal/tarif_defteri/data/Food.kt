package com.ugurtansal.tarif_defteri.data

import android.os.Parcelable
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize
import org.jetbrains.annotations.NotNull
import java.io.Serializable

@Parcelize
@Entity(tableName = "Food",
    foreignKeys = [
        ForeignKey(
            entity = Category::class,
            parentColumns = ["id"],
            childColumns = ["categoryId"],
            onDelete = ForeignKey.CASCADE,
            onUpdate = ForeignKey.CASCADE
        )
    ]
    )
data class Food (@PrimaryKey(autoGenerate = true) @NotNull  var id: Int,
                 @NotNull var name: String,
                 @NotNull var categoryId: Int,
                 @NotNull var ingredients: String,
                 var description: String?=null) : Parcelable {


}