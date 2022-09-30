package com.ravi.myapplication.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ravi.myapplication.R
import kotlinx.android.synthetic.main.album_item.view.*
import java.io.File

class AlbumAdapter(private var dataList: ArrayList<File>,val onClickListener: OnClickListener) :
    RecyclerView.Adapter<AlbumAdapter.AlbumViewHolder>() {

    class AlbumViewHolder(itemView: View,val onClickListener: OnClickListener) : RecyclerView.ViewHolder(itemView) {
        fun setData(file: File) {
            itemView.tvAlbumList.text = file.name
            itemView.albumCard.setOnClickListener {
                onClickListener.ShowImage(file,adapterPosition)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlbumViewHolder {
        var view = LayoutInflater.from(parent.context).inflate(R.layout.album_item, parent, false)
        return AlbumViewHolder(view,onClickListener)
    }

    override fun onBindViewHolder(holder: AlbumViewHolder, position: Int) {
        holder.setData(dataList[position])
    }

    override fun getItemCount(): Int {
        return dataList.size
    }
}