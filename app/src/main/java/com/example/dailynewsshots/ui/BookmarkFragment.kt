package com.example.dailynewsshots.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.dailynewsshots.R
import com.example.dailynewsshots.ui.adapter.BookmarkAdapter
import com.example.dailynewsshots.ui.viewmodel.BookmarkViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BookmarkFragment : Fragment(R.layout.fragment_bookmark) {

    private val viewModel: BookmarkViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val recycler = view.findViewById<RecyclerView>(R.id.rvBookmarks)
        recycler.layoutManager = LinearLayoutManager(requireContext())

        val adapter = BookmarkAdapter()
        recycler.adapter = adapter

        viewModel.bookmarks.observe(viewLifecycleOwner) {
            adapter.submit(it)
        }
    }
}
