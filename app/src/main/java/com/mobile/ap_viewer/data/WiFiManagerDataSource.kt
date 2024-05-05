package com.mobile.ap_viewer.data

import android.annotation.SuppressLint
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.net.wifi.ScanResult
import android.net.wifi.WifiManager
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

@SuppressLint("MissingPermission")
@Singleton
class WiFiManagerDataSource @Inject constructor(@ApplicationContext val context: Context) {
    private val wifiManager = context.getSystemService(Context.WIFI_SERVICE) as WifiManager
    private val wifiScanReceiver: BroadcastReceiver
    private val intentFilter = IntentFilter()
    private var latestResults: MutableList<ScanResult> = mutableListOf()

    init {
        wifiScanReceiver = object : BroadcastReceiver() {
            override fun onReceive(context: Context, intent: Intent) {
                val success = intent.getBooleanExtra(WifiManager.EXTRA_RESULTS_UPDATED, false)
                if (success) {
                    scanSuccess()
                } else {
                    scanFailure()
                }
            }
        }

        intentFilter.addAction(WifiManager.SCAN_RESULTS_AVAILABLE_ACTION)
        context.registerReceiver(wifiScanReceiver, intentFilter)
    }

    fun scanSuccess() {
        println("Scan Success")
    }

    fun scanFailure() {
        println("Scan Failure")
    }

    fun getScanResults(): List<ScanResult> {
        val success = wifiManager.startScan()
        if(success) {
            scanSuccess()
        } else {
            scanFailure()
        }
        latestResults.clear()
        latestResults = wifiManager.scanResults
        return latestResults
    }
}