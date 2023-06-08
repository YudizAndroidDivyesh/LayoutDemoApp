package com.example.demooflayouts.koinDI

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.example.demooflayouts.R
import org.koin.android.ext.android.inject

class UserKoinActivity : AppCompatActivity() {
    private val viewModel : UserKoinViewModel by inject()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_koin)

    }
}