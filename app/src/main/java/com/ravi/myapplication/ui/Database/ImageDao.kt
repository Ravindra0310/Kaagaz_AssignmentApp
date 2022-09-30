package com.ravi.myapplication.ui.Database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface ImageDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(imageEntity: ImageEntity)

    @Query("select * from kaagaz_table")
    fun getTask(): LiveData<List<ImageEntity>>
}