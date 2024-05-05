package com.mobile.ap_viewer.data.model

data class AccessPoint(
    val bssid: String,
    val ssid: String,
    val capabilities: String,
    val channelWidth: Int,
    val frequency: Int,
    val signalStrength: Int,
    val lastSeen: Long
)