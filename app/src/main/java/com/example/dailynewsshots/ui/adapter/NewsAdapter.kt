package com.example.dailynewsshots.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.dailynewsshots.R
import com.example.dailynewsshots.data.model.Article

class NewsAdapter(
    private val onClick: (Article) -> Unit
) : RecyclerView.Adapter<NewsAdapter.ViewHolder>() {

    private val list = mutableListOf<Article>()

    fun submit(data: List<Article>) {
        list.clear()
        list.addAll(data)
        notifyDataSetChanged()
    }

    inner class ViewHolder(val view: View) : RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_news, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, pos: Int) {
        val article = list[pos]
        holder.itemView.apply {
            findViewById<TextView>(R.id.title).text = article.title
            findViewById<TextView>(R.id.source).text = article.source.name
            Glide.with(this)
                .load(article.urlToImage)
                .into(findViewById(R.id.image))
            setOnClickListener { onClick(article) }
        }
    }

    override fun getItemCount() = list.size
}