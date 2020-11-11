package com.example.my_pattern_in_android.ui.main_ui.view.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.my_pattern_in_android.R
import com.example.my_pattern_in_android.ui.main_ui.model.data_class.MyPictures
import kotlinx.android.synthetic.main.item_pictures.view.*

class PictureAdapter(private val pictures: List<MyPictures>, private val context: Context) :
    RecyclerView.Adapter<PictureAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_pictures, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.itemView.tvAuthorName.text = pictures[position].name
        Glide.with(context).load(pictures[position].photo).into(holder.itemView.ivPhoto)

    }


    override fun getItemCount(): Int {
        return pictures.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
}