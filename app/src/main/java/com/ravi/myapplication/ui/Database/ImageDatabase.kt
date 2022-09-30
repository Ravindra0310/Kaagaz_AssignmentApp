package com.ravi.myapplication.ui.Database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [ImageEntity::class], version = 1)
abstract class ImageDatabase :RoomDatabase(){
    abstract fun getImageDao():ImageDao

    companion object{

        private var INSTANCE: ImageDatabase? = null

        fun getDatabase(context: Context): ImageDatabase{
            if (INSTANCE == null){
                val builder = Room.databaseBuilder(
                    context.applicationContext,
                    ImageDatabase::class.java,
                    "image_database"
                )

                builder.fallbackToDestructiveMigration()
                INSTANCE =  builder.build()

                return INSTANCE!!
            }else{
                return INSTANCE!!
            }
        }

    }

}