package com.mobile.ap_viewer.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mobile.ap_viewer.data.ScanRecordRepository
import com.mobile.ap_viewer.data.model.AccessPoint
import com.mobile.ap_viewer.data.model.ScanRecord
import com.mobile.ap_viewer.di.DefaultDispatcher
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class ScanRecordViewModel @Inject internal constructor(
    private val scanRecordRepository: ScanRecordRepository,
    @DefaultDispatcher private val defaultDispatcher: CoroutineDispatcher
) : ViewModel() {

    private val _accessPoints = MutableLiveData<List<AccessPoint>>()
    val accessPoints: LiveData<List<AccessPoint>>
        get() = _accessPoints

    init {
        _accessPoints.value = scanRecordRepository.getAccessPoints()
    }

    fun updateAccessPoints() {
        // coroutine - update access point list on another thread
        viewModelScope.launch {
            val newAccessPoints = withContext(defaultDispatcher) {
                scanRecordRepository.getAccessPoints()
            }
            _accessPoints.value = newAccessPoints
        }
    }
}