package com.example.jusforfun.di

import com.example.core_common.network.NetworkManager
import com.example.core_platform_utils.network.NetworkManagerImpl
import org.koin.dsl.module

val utilModule = module {
    single<NetworkManager> { NetworkManagerImpl(context = get()) }
}