package com.ugurtansal.tarif_defteri.di;


import android.content.Context;

import androidx.room.Room;
import com.ugurtansal.tarif_defteri.datasource.CategoryDataSource
import com.ugurtansal.tarif_defteri.datasource.FoodDataSource
import com.ugurtansal.tarif_defteri.repo.CategoryRepository
import com.ugurtansal.tarif_defteri.repo.FoodRepository
import com.ugurtansal.tarif_defteri.room.CategoryDao
import com.ugurtansal.tarif_defteri.room.FoodDao
import com.ugurtansal.tarif_defteri.room.MyDatabase

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.android.qualifiers.ApplicationContext;
import dagger.hilt.components.SingletonComponent;

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    @Singleton
    fun provideCategoryRepository(categoryDataSource: CategoryDataSource): CategoryRepository {
        return CategoryRepository(categoryDataSource);
    }

    @Provides
    @Singleton
    fun provideCategoryDataSource(categoryDao: CategoryDao): CategoryDataSource {
        return CategoryDataSource(categoryDao);
    }

    @Provides
    @Singleton
    fun provideFoodRepository(foodDataSource: FoodDataSource): FoodRepository {
        return FoodRepository(foodDataSource);
    }

    @Provides
    @Singleton
    fun provideFoodDataSource(foodDao: FoodDao): FoodDataSource {
        return FoodDataSource(foodDao);
    }

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): MyDatabase {
        return Room.databaseBuilder(context, MyDatabase::class.java, "tarif_def_db.db")
            .createFromAsset("tarif_def_db.db")
            .build()
    }

    @Provides
    @Singleton
    fun provideCategoryDao(database: MyDatabase): CategoryDao = database.getCategoryDao()

    @Provides
    @Singleton
    fun provideFoodDao(database: MyDatabase): FoodDao = database.getFoodDao()


}
