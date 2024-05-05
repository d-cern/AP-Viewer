package com.mobile.ap_viewer.data.model

import android.location.Location

data class ScanRecord(
    val accessPointList: List<AccessPoint>,
    val userLocation: Location?
)