package com.example.shoppingcart3.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.shoppingcart3.R

class MainActivity : AppCompatActivity() {
    private val TAG = MainActivity::class.java.name

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_layout)
    }
}