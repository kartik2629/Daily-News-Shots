package com.example.dailynewsshots.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.dailynewsshots.R
import com.example.dailynewsshots.data.model.Article
import com.example.dailynewsshots.utils.NativeAdBinder
import com.example.dailynewsshots.utils.NativeAdLoader
import com.google.android.gms.ads.nativead.NativeAdView

class NewsPagingAdapter(
    private val onClick: (Article) -> Unit
) : PagingDataAdapter<Article, NewsPagingAdapter.BaseViewHolder>(DIFF) {

    sealed class BaseViewHolder(view: View) : RecyclerView.ViewHolder(view)

    inner class ArticleViewHolder(view: View) : BaseViewHolder(view)
    inner class AdViewHolder(view: View) : BaseViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        return if (viewType == TYPE_AD) {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_native_ad, parent, false)
            AdViewHolder(view)
        } else {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_news, parent, false)
            ArticleViewHolder(view)
        }
    }

    override fun onBindViewHolder(holder: BaseViewHolder, pos: Int) {
        if (holder is ArticleViewHolder) {
            getItem(pos)?.let { article ->
                holder.itemView.apply {
                    findViewById<TextView>(R.id.title).text = article.title
                    findViewById<TextView>(R.id.source).text = article.source.name
                    Glide.with(this)
                        .load(article.urlToImage)
                        .into(findViewById(R.id.image))
                    setOnClickListener { onClick(article) }
                }
            }
        } else if (holder is AdViewHolder) {
            NativeAdLoader(holder.itemView.context).load { ad ->
                ad?.let {
                    val adView = holder.itemView as NativeAdView
                    NativeAdBinder.bind(it, adView)
                }
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (position % 6 == 0 && position != 0) TYPE_AD else TYPE_ARTICLE
    }


    companion object {
        private val DIFF = object : DiffUtil.ItemCallback<Article>() {
            override fun areItemsTheSame(old: Article, new: Article) =
                old.url == new.url

            override fun areContentsTheSame(old: Article, new: Article) =
                old == new
        }
        private const val TYPE_ARTICLE = 0
        private const val TYPE_AD = 1

    }
}
