package com.example.dailynewsshots

import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify
import org.mockito.Mockito.`when`

class NewsRepositoryTest {

    private val api = mock(NewsApiService::class.java)
    private val dao = mock(BookmarkDao::class.java)
    private val repository = NewsRepository(api, dao)

    @Test
    fun `getNews calls api with correct parameters`() = runBlocking {
        // Given
        val country = "us"
        val category = "tech"
        val expectedResponse = NewsResponse("ok", 0, emptyList())
        `when`(api.getTopHeadlines(country, category, 1)).thenReturn(expectedResponse)

        // When
        val result = repository.getNews(country, category)

        // Then
        assertEquals(expectedResponse, result)
        verify(api).getTopHeadlines(country, category, 1)
    }
}
