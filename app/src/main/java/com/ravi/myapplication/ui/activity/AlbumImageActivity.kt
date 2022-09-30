package com.ravi.myapplication.ui.activity

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.ravi.myapplication.R
import com.ravi.myapplication.ui.adapter.AlbumAdapter
import com.ravi.myapplication.ui.adapter.ImageAdapter
import kotlinx.android.synthetic.main.activity_album_image.*
import kotlinx.android.synthetic.main.activity_home.*
import java.io.File

class AlbumImageActivity : AppCompatActivity() {

    lateinit var imageAdapter: ImageAdapter
    private var ImageList = ArrayList<File>()
    private lateinit var file:File
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_album_image)
        getFilePath()
        setRecyclerView()
        back_buttonFromImage.setOnClickListener {
            finish()
        }

    }

    private fun setRecyclerView() {
        AlbumImageRecyclerView.layoutManager= GridLayoutManager(this,2)
        ImageList.addAll(file.listFiles())
        imageAdapter= ImageAdapter(ImageList)
        AlbumImageRecyclerView.adapter=imageAdapter
    }

    private fun getFilePath() {
         file=File(intent.getStringExtra("file"))
    }

}