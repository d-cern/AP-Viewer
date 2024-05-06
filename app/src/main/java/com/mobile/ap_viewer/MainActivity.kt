package com.mobile.ap_viewer

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.content.ContextCompat
import com.mobile.ap_viewer.data.ScanRecordRepository
import com.mobile.ap_viewer.data.WiFiManagerDataSource
import com.mobile.ap_viewer.ui.theme.APViewerTheme

class MainActivity : ComponentActivity() {
    private lateinit var permissionLauncher : ActivityResultLauncher<Array<String>>
    private var locationPermission = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)

        permissionLauncher = registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) { permissions ->
            locationPermission = permissions[Manifest.permission.ACCESS_FINE_LOCATION] ?: locationPermission
        }

        requestPermission()

        val scanButton = findViewById<Button>(R.id.ScanButton)
        scanButton.setOnClickListener {
            Toast.makeText(this, "Scanning...", Toast.LENGTH_SHORT).show()
            val wifi = WiFiManagerDataSource(this)
            /*if (wifi.scanState == 0) {
                Toast.makeText(this, "Scan successful!", Toast.LENGTH_SHORT).show()
                val scanIntent = Intent(this, ScanResult::class.java)
                startActivity(scanIntent)
            }
            else {
                Toast.makeText(this, "Scan failed...", Toast.LENGTH_SHORT).show()
            }*/

        }

        val optionsButton = findViewById<Button>(R.id.OptionsButton)
    }

    private fun requestPermission() {
        locationPermission = ContextCompat.checkSelfPermission(
            this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED

        val requests : MutableList<String> = ArrayList()

        if (!locationPermission) {
            requests.add(Manifest.permission.ACCESS_FINE_LOCATION)
        }

        if (requests.isNotEmpty()) {
            permissionLauncher.launch(requests.toTypedArray())
        }
    }
}