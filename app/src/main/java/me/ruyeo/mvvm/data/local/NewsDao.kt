package me.ruyeo.mvvm.data.local

import androidx.room.Insert
import me.ruyeo.mvvm.data.local.entity.News

interface NewsDao {

    @Insert
    suspend fun addNews(news: News)
}