package com.ravi.myapplication.ui.di

import android.app.Application
import androidx.room.Room
import com.ravi.myapplication.ui.Database.ImageDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    @Singleton
    fun provideImageDatabase(app: Application): ImageDatabase {
        return Room.databaseBuilder(app, ImageDatabase::class.java, "image_database")
            .fallbackToDestructiveMigration().build()
    }


}