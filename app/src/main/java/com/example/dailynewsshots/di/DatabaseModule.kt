package com.example.dailynewsshots.di

import android.content.Context
import androidx.room.Room
import com.example.dailynewsshots.data.local.AppDatabase
import com.example.dailynewsshots.data.local.BookmarkDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(
        @ApplicationContext context: Context
    ): AppDatabase =
        Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "news_db"
        ).build()

    @Provides
    fun provideDao(db: AppDatabase): BookmarkDao =
        db.bookmarkDao()
}
