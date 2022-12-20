package com.example.yubikoandroid13

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.yubico.yubikit.android.ui.OtpActivity

class MainActivity : AppCompatActivity() {

    private val scanYubikeyResult = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) {
        if (it.resultCode == Activity.RESULT_OK) {
            val otp = it.data?.getStringExtra(OtpActivity.EXTRA_OTP)
            Log.d("OTP", "Scan succeeded: %s".format(otp))
        } else {
            Log.d("OTP", "Scan cancelled")
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<Button>(R.id.button).setOnClickListener {
            scanYubikeyResult.launch(
                Intent(this, OtpActivity::class.java)
            )
        }
    }
}
