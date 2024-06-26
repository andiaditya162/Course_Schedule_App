package com.dityapra.courseschedule.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dityapra.courseschedule.data.Course
import com.dityapra.courseschedule.data.DataRepository
import com.dityapra.courseschedule.util.QueryType
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class HomeViewModel(private val repository: DataRepository): ViewModel() {

    private val _queryType = MutableLiveData<QueryType>()

    init {
        _queryType.value = QueryType.CURRENT_DAY
    }

    fun setQueryType(queryType: QueryType) {
        _queryType.value = queryType
    }

    fun getNearestSchedule(): LiveData<Course?> {
        return Transformations.switchMap(_queryType) { queryType ->
            repository.getNearestSchedule(queryType)
        }
    }
}