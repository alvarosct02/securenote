package com.asct94.securenote.presentation.login

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.asct94.securenote.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AuthActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auth)
    }
}