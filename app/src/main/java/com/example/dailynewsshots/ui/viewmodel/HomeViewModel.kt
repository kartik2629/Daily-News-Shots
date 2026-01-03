package com.example.dailynewsshots.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.dailynewsshots.data.model.Article
import com.example.dailynewsshots.data.repository.NewsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: NewsRepository
) : ViewModel() {

    private val _news = MutableLiveData<List<Article>>()
    val news: LiveData<List<Article>> = _news

    fun loadNews(country: String, category: String) {
        viewModelScope.launch {
            try {
                val response = repository.getNews(country, category)
                _news.value = response.articles
                Timber.d("Articles loaded: ${response.articles.size}")
            } catch (e: Exception) {
                Timber.e(e)
            }
        }
    }

    fun pagedNews(
        country: String,
        category: String
    ): Flow<PagingData<Article>> {
        return repository
            .getPagedNews(country, category)
            .cachedIn(viewModelScope)
    }
}
