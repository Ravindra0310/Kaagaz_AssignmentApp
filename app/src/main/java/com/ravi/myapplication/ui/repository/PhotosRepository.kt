package com.ravi.myapplication.ui.repository

import androidx.lifecycle.LiveData
import com.ravi.myapplication.ui.Database.ImageDatabase
import com.ravi.myapplication.ui.Database.ImageEntity
import java.sql.Timestamp
import javax.inject.Inject

class PhotosRepository @Inject constructor(
    val database: ImageDatabase
) {

    private val dao = database.getImageDao()

    suspend fun insertData(name: String, timestamp: String, albums: String) {
        val imageEntity = ImageEntity(name, timestamp, albums)
        dao.insert(imageEntity)
    }

    fun getImageList(): LiveData<List<ImageEntity>> {
        return dao.getTask()
    }

}