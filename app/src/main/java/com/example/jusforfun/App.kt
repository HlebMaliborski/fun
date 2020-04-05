package com.example.jusforfun

import android.app.Application
import com.example.core_db_impl.di.dbModule
import com.example.feature_fun.di.funFeatureModule
import com.example.jusforfun.di.navigationModule
import com.example.jusforfun.di.utilModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.loadKoinModules
import org.koin.core.context.startKoin

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@App)
            loadKoinModules(listOf(utilModule, dbModule, funFeatureModule, navigationModule))
        }
    }
}