package me.ruyeo.mvvm.repository

import me.ruyeo.mvvm.data.local.NewsDao
import me.ruyeo.mvvm.data.local.entity.News
import me.ruyeo.mvvm.data.remote.ApiService
import javax.inject.Inject

class NewsRepository @Inject constructor(
    private val apiService: ApiService,
    private val newsDao: NewsDao
) {

    suspend fun addNews(addNews: News) = apiService.addNews(addNews)

    suspend fun addNewsLocal(addNews: News) = newsDao.addNews(addNews)
}