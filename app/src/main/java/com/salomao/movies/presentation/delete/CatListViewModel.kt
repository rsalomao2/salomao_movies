package com.salomao.movies.presentation.delete

import androidx.lifecycle.ViewModel
import androidx.paging.PagingData
import androidx.paging.map
import com.salomao.movies.data.repository.CatRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class CatListViewModel(
    private val repository: CatRepository
): ViewModel() {
    val listLiveData: Flow<PagingData<String>> = repository.getSearchResults().map { it.map { it.name } }
}