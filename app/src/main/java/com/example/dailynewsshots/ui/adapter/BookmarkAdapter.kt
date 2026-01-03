package com.example.dailynewsshots.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.dailynewsshots.R
import com.example.dailynewsshots.data.local.BookmarkEntity

class BookmarkAdapter :
    RecyclerView.Adapter<BookmarkAdapter.ViewHolder>() {

    private val list = mutableListOf<BookmarkEntity>()

    fun submit(data: List<BookmarkEntity>) {
        list.clear()
        list.addAll(data)
        notifyDataSetChanged()
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_bookmark, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, pos: Int) {
        val item = list[pos]
        holder.itemView.findViewById<TextView>(R.id.title).text = item.title
        Glide.with(holder.itemView)
            .load(item.image)
            .into(holder.itemView.findViewById(R.id.image))
    }

    override fun getItemCount() = list.size
}
