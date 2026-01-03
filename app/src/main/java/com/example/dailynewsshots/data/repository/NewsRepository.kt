package com.example.dailynewsshots.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.dailynewsshots.data.local.BookmarkDao
import com.example.dailynewsshots.data.local.BookmarkEntity
import com.example.dailynewsshots.data.model.Article
import com.example.dailynewsshots.data.remote.NewsApiService
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class NewsRepository @Inject constructor(
    private val api: NewsApiService,
    private val dao: BookmarkDao
) {

    suspend fun getNews(country: String, category: String) = api.getTopHeadlines(country)

    suspend fun saveBookmark(article: Article) {
        dao.insert(
            BookmarkEntity(
                url = article.url,
                title = article.title,
                image = article.urlToImage,
                source = article.source.name
            )
        )
    }

    fun getBookmarks() = dao.getBookmarks()

    fun getPagedNews(
        country: String,
        category: String
    ): Flow<PagingData<Article>> {
        return Pager(
            config = PagingConfig(
                pageSize = 20,
                enablePlaceholders = false
            ),
            pagingSourceFactory = {
                NewsPagingSource(api, country, category)
            }
        ).flow
    }

}
