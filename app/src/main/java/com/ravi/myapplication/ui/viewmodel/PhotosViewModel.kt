package com.ravi.myapplication.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.ravi.myapplication.ui.Database.ImageEntity
import com.ravi.myapplication.ui.repository.PhotosRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PhotosViewModel @Inject constructor(
    val repository: PhotosRepository
) : ViewModel() {

    fun insertImageData(name: String, timestamp: String, albums: String) {
        CoroutineScope(Dispatchers.Default).launch {
            repository.insertData(name, timestamp, albums)
        }
    }

    fun getImageData(): LiveData<List<ImageEntity>> {
        return repository.getImageList()
    }
}