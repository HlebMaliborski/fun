package com.example.jusforfun

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import com.example.jusforfun.navigation.Navigator
import org.koin.android.ext.android.getKoin

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        getKoin().declare(Navigator(findNavController(R.id.nav_host_fragment)))
    }
}
