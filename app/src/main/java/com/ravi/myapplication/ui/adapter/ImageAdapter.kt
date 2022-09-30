package com.ravi.myapplication.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.ravi.myapplication.R
import kotlinx.android.synthetic.main.album_item.view.*
import kotlinx.android.synthetic.main.image_item.view.*
import java.io.File

class ImageAdapter(private var dataList: ArrayList<File>) :
    RecyclerView.Adapter<ImageAdapter.ImageViewHolder>() {

        class ImageViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
        fun setData(file: File){
            Glide.with(itemView.ivImageItem).load(file.path).into(itemView.ivImageItem)
        }
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
        var view = LayoutInflater.from(parent.context).inflate(R.layout.image_item, parent, false)
        return ImageViewHolder(view)
    }

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        holder.setData(dataList[position])
    }

    override fun getItemCount(): Int {
        return dataList.size
    }
}