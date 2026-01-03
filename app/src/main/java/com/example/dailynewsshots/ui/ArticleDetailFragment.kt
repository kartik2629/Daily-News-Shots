package com.example.dailynewsshots.ui

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.example.dailynewsshots.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ArticleDetailFragment :
    Fragment(R.layout.fragment_article_detail) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val title = view.findViewById<TextView>(R.id.title)
        val image = view.findViewById<ImageView>(R.id.image)

        val articleTitle = arguments?.getString("title")
        val imageUrl = arguments?.getString("image")

        title.text = articleTitle
        Glide.with(this).load(imageUrl).into(image)
    }
}
