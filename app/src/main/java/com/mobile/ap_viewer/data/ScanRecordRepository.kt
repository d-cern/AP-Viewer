package com.mobile.ap_viewer.data

import android.location.Location
import com.mobile.ap_viewer.data.model.AccessPoint
import com.mobile.ap_viewer.data.model.ScanRecord
import javax.inject.Inject

class ScanRecordRepository @Inject constructor(
    private val wifi: WiFiManagerDataSource,
    private val location: LocationManagerDataSource
) {
    private var accessPointList: MutableList<AccessPoint> = mutableListOf<AccessPoint>()
    private var userLocation: Location? = null
    private lateinit var scanRecord: ScanRecord

    init {
        updateAccessPoints()
    }

    private fun updateAccessPoints() {
        val results = wifi.getScanResults()
        accessPointList.clear()
        results.forEach {
            accessPointList
                .add(
                    AccessPoint(
                        it.BSSID,
                        it.SSID,
                        it.capabilities,
                        it.channelWidth,
                        it.frequency,
                        it.level,
                        it.timestamp
                    )
                )
        }
        scanRecord = ScanRecord(accessPointList, userLocation)
    }

    fun getScanRecord(): ScanRecord {
        updateAccessPoints()
        return scanRecord
    }

    fun getAccessPoints(): List<AccessPoint> {
        return getScanRecord().accessPointList
    }
}