package com.example.dailynewsshots.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.dailynewsshots.data.repository.NewsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class BookmarkViewModel @Inject constructor(
    repository: NewsRepository
) : ViewModel() {

    val bookmarks = repository.getBookmarks().asLiveData()
}
