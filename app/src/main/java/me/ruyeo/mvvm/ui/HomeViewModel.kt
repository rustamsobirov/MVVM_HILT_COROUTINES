package me.ruyeo.mvvm.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import me.ruyeo.mvvm.data.local.entity.News
import me.ruyeo.mvvm.repository.NewsRepository
import me.ruyeo.mvvm.utils.UiStateObject
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: NewsRepository
): ViewModel() {

    private val _newsState = MutableStateFlow<UiStateObject<News>>(UiStateObject.EMPTY)
    val newsState = _newsState

    fun addNews(addNews: News) = viewModelScope.launch {
        _newsState.value = UiStateObject.LOADING
        try {
            val response = repository.addNews(addNews)
            _newsState.value = UiStateObject.SUCCESS(response)
        }catch (e: Exception){
            _newsState.value = UiStateObject.ERROR(e.localizedMessage ?: "No Connection")
        }
    }
}